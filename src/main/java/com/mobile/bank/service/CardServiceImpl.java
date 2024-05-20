package com.mobile.bank.service;

import com.mobile.bank.model.Card;
import com.mobile.bank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public Card updateCardById(Long id, Card card) {
        Optional<Card> existingCard = cardRepository.findById(id);
        if (existingCard.isPresent()) {
            Card cardToUpdate = existingCard.get();
            cardToUpdate.setCardNumber(card.getCardNumber());
            cardToUpdate.setCardBalance(card.getCardBalance());
            cardToUpdate.setUser(card.getUser());
            return cardRepository.save(cardToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }
}
