package org.vicary.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.vicary.model.User;
import org.vicary.repository.annotation.HibUserRepository;

import java.util.List;

@Repository
@HibUserRepository         // Nadawanie nazwy do wstrzykiwania
public class HibernateUserRepository implements UserRepository {
    @Override
    public void addUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("Successfully added.");
        }
    }

    public List<User> showUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User");
            return query.getResultList();
        }
    }

    @Override
    public User findUserByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE user_email = :email");
            query.setParameter("email", email);
            if (query.uniqueResult() == null) throw new IllegalArgumentException("User does not exist.");
            return query.uniqueResult();
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
            System.out.println("Successfully deleted.");
        }
    }
}
