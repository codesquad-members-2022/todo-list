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
public class ColumnServiceImpl implements ColumnService{

    private final double DIFFERENCE = 1000.0;

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
        Double lastOrder = columnRepository.getLastOrder();
        Double order = lastOrder + DIFFERENCE;
        Column saveColumn = columnRepository.save(columnRegistrationRequest.toEntity(), order);
        return ColumnResponse.of(saveColumn);
    }

    public ColumnResponse changeColumnOrder(ColumnMoveRequest columnMoveRequest) {
        Double newOrder = getNewOrder(columnMoveRequest);
        Column updatedColumn = columnRepository.updateOrder(columnMoveRequest.toEntity(), newOrder);
        return ColumnResponse.of(updatedColumn);
    }

    private double getNewOrder(ColumnMoveRequest columnMoveRequest) {
        Column leftColumn = columnRepository.findById(columnMoveRequest.getLeftColumnId());
        Column rightColumn = columnRepository.findById(columnMoveRequest.getRightColumnId());

        Double orderLeft = leftColumn.getOrder();
        Double orderRight = rightColumn.getOrder();

        if (orderLeft == null) {
            return orderRight - DIFFERENCE;
        }

        if(orderRight == null) {
            return orderLeft + DIFFERENCE;
        }

        return (orderLeft + orderRight) / 2;
    }

    public ColumnResponse modifyColumn(ColumnUpdateRequest columnUpdateRequest) {
        Column updatedColumn = columnRepository.updateTitle(columnUpdateRequest.toEntity());

        return ColumnResponse.of(updatedColumn);
    }

    public boolean deleteCard(Long id) {
        return columnRepository.delete(id);
    }

}
