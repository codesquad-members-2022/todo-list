package com.codesquad.todolist.card.integration;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.column.ColumnRepository;
import com.codesquad.todolist.user.User;
import com.codesquad.todolist.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CardSetUp {

    private final UserRepository userRepository;
    private final ColumnRepository columnRepository;
    private final CardRepository cardRepository;

    public CardSetUp(UserRepository userRepository,
        ColumnRepository columnRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.columnRepository = columnRepository;
        this.cardRepository = cardRepository;
    }

    public User createUser(User user) {
        return userRepository.create(user);
    }

    public Column createColumn(Column column) {
        return columnRepository.create(column);
    }

    public Card createCard(Card card) {
        return cardRepository.create(card);
    }

}
