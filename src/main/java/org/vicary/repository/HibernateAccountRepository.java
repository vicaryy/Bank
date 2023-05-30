package org.vicary.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.vicary.model.Account;
import org.vicary.model.User;
import org.vicary.repository.annotation.HibAccountRepository;

import java.util.List;
@Repository
@HibAccountRepository
public class HibernateAccountRepository implements AccountRepository {

    @Override
    public void addAccount(Account account) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Account> query = session.createQuery("FROM Account WHERE user = :user AND currency = :currency");
            query.setParameter("user", account.getUser());
            query.setParameter("currency", account.getCurrency());
            if (query.uniqueResult() != null) throw new IllegalArgumentException("Account already exists.");
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
            System.out.println("Successfully added.");
        }
    }

    @Override
    public List<Account> showAccounts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Account> query = session.createQuery("FROM Account");
            return query.getResultList();
        }
    }

    @Override
    public Account findAccountByCurrencyAndEmail(String currency, User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Account> query = session.createQuery("FROM Account WHERE currency = :currency AND user = :user");
            query.setParameter("currency", currency);
            query.setParameter("user", user);
            if (query.uniqueResult() == null) throw new IllegalArgumentException("Account does not exist.");
            return query.uniqueResult();
        }
    }

    @Override
    public void deleteAccount(Account account) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(account);
            session.getTransaction().commit();
            System.out.println("Successfully deleted.");
        }
    }

    @Override
    public void transferFunds(Account accFrom, Account accTo, double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Amount has to be more than 0");
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Double> query = session.createQuery("SELECT amount FROM Account WHERE account_id = :idFrom");
            query.setParameter("idFrom", accFrom.getAccount_id());
            if((query.uniqueResult() - amount) < 0) throw new IllegalArgumentException("Not enough funds.");

            session.beginTransaction();
            Query queryFrom = session.createQuery("UPDATE Account SET amount = :new_amount WHERE account_id = :idFrom");
            queryFrom.setParameter("new_amount", accFrom.getAmount() - amount);
            queryFrom.setParameter("idFrom", accFrom.getAccount_id());

            Query queryTo = session.createQuery("UPDATE Account SET amount = :new_amount WHERE account_id = :idTo");
            queryTo.setParameter("new_amount", accTo.getAmount() + amount);
            queryTo.setParameter("idTo", accTo.getAccount_id());

            queryFrom.executeUpdate();
            queryTo.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Successfully transferred.");
        }
    }
}








