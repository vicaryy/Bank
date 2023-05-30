package org.vicary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String user_name;

    private String user_lastname;
    @Column(name = "user_email")
    private String email;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Account> accounts;

    public User(String user_name, String user_lastname, String email) {
        this.user_name = user_name;
        this.user_lastname = user_lastname;
        this.email = email;
    }
}
