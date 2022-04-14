package com.team26.todolist.service;

import com.team26.todolist.domain.Column;
import com.team26.todolist.dto.request.ColumnChangeOrderRequest;
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
    private final CardService cardService;

    public ColumnServiceImpl(ColumnRepository columnRepository,
            CardService cardService) {
        this.columnRepository = columnRepository;
        this.cardService = cardService;
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

    @Override
    public ColumnResponse addColumn(ColumnRegistrationRequest columnRegistrationRequest) {
        Column saveColumn = columnRepository.saveNewColumn(columnRegistrationRequest.toEntity());
        return ColumnResponse.of(saveColumn);
    }

    @Override
    public ColumnResponse changeColumnOrder(ColumnChangeOrderRequest columnChangeOrderRequest) {
        Column column = columnChangeOrderRequest.toEntity();
        Column leftColumn = columnRepository.findById(columnChangeOrderRequest.getLeftColumnId());
        Column rightColumn = columnRepository.findById(columnChangeOrderRequest.getRightColumnId());
        column.setNewOrder(leftColumn, rightColumn);

        Column updatedColumn = columnRepository.updateOrder(column);
        return ColumnResponse.of(updatedColumn);
    }

    @Override
    public ColumnResponse modifyColumn(ColumnUpdateRequest columnUpdateRequest) {
        Column updatedColumn = columnRepository.updateTitle(columnUpdateRequest.toEntity());

        return ColumnResponse.of(updatedColumn);
    }

    @Override
    public void deleteColumn(Long id) {
        cardService.deleteCardByColumnId(id);
        columnRepository.delete(id);
    }

}
