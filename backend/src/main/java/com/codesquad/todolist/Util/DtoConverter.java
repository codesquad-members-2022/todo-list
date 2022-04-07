package com.codesquad.todolist.Util;

import java.time.LocalDateTime;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardCreateRequest;

public class DtoConverter {

    public static Card dtoToEntity(CardCreateRequest createRequest) {
        return new Card.Builder(createRequest.getColumnId(), createRequest.getTitle(), createRequest.getAuthor())
            .content(createRequest.getContent().orElse(""))
            .date(LocalDateTime.now())
            .build();
    }
}
