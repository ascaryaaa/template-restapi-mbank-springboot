package com.mobile.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", updatable = false, nullable = false)
    private Long cardId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_balance")
    private BigDecimal cardBalance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
