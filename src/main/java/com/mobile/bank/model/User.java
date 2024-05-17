package com.mobile.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long user_id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_username")
    private String user_username;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "user_balance")
    private String user_balance;
}
