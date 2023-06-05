package org.vicary.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateResponse {
    @JsonProperty("base_code")
    private String currency;

    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates;
}
