package com.team26.todolist.service;

import com.team26.todolist.domain.Column;
import com.team26.todolist.dto.request.ColumnMoveRequest;
import com.team26.todolist.dto.request.ColumnRegistrationRequest;
import com.team26.todolist.dto.request.ColumnUpdateRequest;
import com.team26.todolist.dto.response.ColumnResponse;
import java.util.List;

public interface ColumnService {

    List<ColumnResponse> findAll();
    ColumnResponse addColumn(ColumnRegistrationRequest columnRegistrationRequest);
    ColumnResponse changeColumnOrder(ColumnMoveRequest columnMoveRequest);
    ColumnResponse modifyColumn(ColumnUpdateRequest columnUpdateRequest);
    void deleteColumn(Long id);

    Column findById(Long columnId);
}
