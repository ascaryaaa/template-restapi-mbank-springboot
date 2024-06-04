package com.mobile.bank.service;

import com.mobile.bank.model.Card;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface CardService {
    Card getCardById(Long id);

    Card saveCard(Card card);

    void deleteCardById(Long id);

    Card updateCardById(Long id, Card card);

    List<Card> findAll();

    boolean transferFunds(Long fromCardId, Long toCardId, BigDecimal amount);

    @Transactional
    boolean transferFundsCardNumber(String fromCardNumber, String toCardNumber, BigDecimal amount);

    Card findCardByCardNumber(String cardNumber); // New method declaration
}
