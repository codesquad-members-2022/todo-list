package com.team26.todolist.service;

import com.team26.todolist.domain.Column;
import com.team26.todolist.dto.request.ColumnMoveRequest;
import com.team26.todolist.dto.request.ColumnRegistrationRequest;
import com.team26.todolist.dto.request.ColumnUpdateRequest;
import com.team26.todolist.dto.response.ColumnResponse;
import com.team26.todolist.repository.ColumnRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ColumnServiceImpl implements ColumnService {

    private final ColumnRepository columnRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @Override
    public Column findById(Long id) {
        return columnRepository.findById(id);
    }

    @Override
    public List<ColumnResponse> findAll() {
        List<Column> columns = columnRepository.findAll();
        Collections.sort(columns);
        return columns.stream().map(ColumnResponse::of).collect(Collectors.toList());
    }

    public ColumnResponse addColumn(ColumnRegistrationRequest columnRegistrationRequest) {
        Column saveColumn = columnRepository.saveNewColumn(columnRegistrationRequest.toEntity());
        return ColumnResponse.of(saveColumn);
    }

    public ColumnResponse changeColumnOrder(ColumnMoveRequest columnMoveRequest) {
        Column column = columnMoveRequest.toEntity();
        Column leftColumn = columnRepository.findById(columnMoveRequest.getLeftColumnId());
        Column rightColumn = columnRepository.findById(columnMoveRequest.getRightColumnId());
        column.setNewOrder(leftColumn, rightColumn);

        Column updatedColumn = columnRepository.updateOrder(column);
        return ColumnResponse.of(updatedColumn);
    }

    public ColumnResponse modifyColumn(ColumnUpdateRequest columnUpdateRequest) {
        Column updatedColumn = columnRepository.updateTitle(columnUpdateRequest.toEntity());

        return ColumnResponse.of(updatedColumn);
    }

    public boolean deleteCard(Long id) {
        return columnRepository.delete(id);
    }

}
