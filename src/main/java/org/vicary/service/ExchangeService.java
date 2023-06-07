package org.vicary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vicary.service.dto.CurrencyRateResponse;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final RestTemplate restTemplate;

    public CurrencyRateResponse getCurrencyRate(String currency) {
        String url = "https://v6.exchangerate-api.com/v6/fd33ed05986ddbed0d6642cb/latest/";
        url += currency;
        return restTemplate.getForEntity(url, CurrencyRateResponse.class).getBody();
    }
}
