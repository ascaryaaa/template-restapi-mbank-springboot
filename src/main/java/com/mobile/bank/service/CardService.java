package com.mobile.bank.service;

import com.mobile.bank.model.Card;

import java.util.List;

public interface CardService {
    Card getCardById(Long id);

    Card saveCard(Card card);

    void deleteCardById(Long id);

    Card updateCardById(Long id, Card card);

    List<Card> findAll();
}
