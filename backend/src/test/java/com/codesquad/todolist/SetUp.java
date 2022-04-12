package com.codesquad.todolist;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.column.ColumnRepository;
import com.codesquad.todolist.history.HistoryRepository;
import com.codesquad.todolist.history.ModifiedFieldRepository;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
import com.codesquad.todolist.user.User;
import com.codesquad.todolist.user.UserRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SetUp {

    private final UserRepository userRepository;
    private final ColumnRepository columnRepository;
    private final CardRepository cardRepository;
    private final HistoryRepository historyRepository;
    private final ModifiedFieldRepository modifiedFieldRepository;

    public SetUp(UserRepository userRepository, ColumnRepository columnRepository,
        CardRepository cardRepository, HistoryRepository historyRepository,
        ModifiedFieldRepository modifiedFieldRepository) {
        this.userRepository = userRepository;
        this.columnRepository = columnRepository;
        this.cardRepository = cardRepository;
        this.historyRepository = historyRepository;
        this.modifiedFieldRepository = modifiedFieldRepository;
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

    public History createHistory(History history) {
        return historyRepository.create(history);
    }

    public void createModifiedFields(List<ModifiedField> modifiedFields) {
        modifiedFieldRepository.createAll(modifiedFields);
    }
}
