package com.mobile.bank.service;

import com.mobile.bank.model.Card;
import com.mobile.bank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        if (card.getCardId() == null || !cardRepository.existsById(card.getCardId())) {
            if (cardRepository.findByCardNumber(card.getCardNumber()) != null) {
                throw new IllegalArgumentException("Card number already exists");
            }
        }
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

    @Override
    @Transactional
    public boolean transferFunds(Long fromCardId, Long toCardId, BigDecimal amount) {
        Optional<Card> fromCardOptional = cardRepository.findById(fromCardId);
        Optional<Card> toCardOptional = cardRepository.findById(toCardId);

        if (fromCardOptional.isPresent() && toCardOptional.isPresent()) {
            Card fromCard = fromCardOptional.get();
            Card toCard = toCardOptional.get();

            if (fromCard.getCardBalance().compareTo(amount) >= 0) {
                fromCard.setCardBalance(fromCard.getCardBalance().subtract(amount));
                toCard.setCardBalance(toCard.getCardBalance().add(amount));

                cardRepository.save(fromCard);
                cardRepository.save(toCard);

                return true;
            }
        }
        return false;
    }

    @Override
    public Card findCardByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }
}
