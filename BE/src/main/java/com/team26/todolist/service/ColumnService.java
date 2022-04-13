package com.team26.todolist.service;

import com.team26.todolist.domain.Column;
import com.team26.todolist.dto.request.ColumnMoveRequest;
import com.team26.todolist.dto.request.ColumnRegistrationRequest;
import com.team26.todolist.dto.request.ColumnUpdateRequest;
import com.team26.todolist.dto.response.ColumnResponse;

public interface ColumnService {

    ColumnResponse addColumn(ColumnRegistrationRequest columnRegistrationRequest);
    ColumnResponse changeColumnOrder(ColumnMoveRequest columnMoveRequest);
    ColumnResponse modifyColumn(ColumnUpdateRequest columnUpdateRequest);
    boolean deleteCard(Long id);

}
