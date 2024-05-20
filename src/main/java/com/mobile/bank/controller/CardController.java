package com.mobile.bank.controller;

import com.mobile.bank.model.Card;
import com.mobile.bank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.findAll();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Card card = cardService.getCardById(id);
        if (card != null) {
            return ResponseEntity.ok(card);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card createdCard = cardService.saveCard(card);
        return ResponseEntity.ok(createdCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card card) {
        Card updatedCard = cardService.updateCardById(id, card);
        if (updatedCard != null) {
            return ResponseEntity.ok(updatedCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCardById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferFunds(@RequestBody Map<String, String> transferRequest) {
        Long fromCardId = Long.valueOf(transferRequest.get("fromCardId"));
        Long toCardId = Long.valueOf(transferRequest.get("toCardId"));
        BigDecimal amount = new BigDecimal(transferRequest.get("amount"));

        boolean success = cardService.transferFunds(fromCardId, toCardId, amount);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
