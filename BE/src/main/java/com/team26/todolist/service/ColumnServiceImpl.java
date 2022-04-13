package com.team26.todolist.service;

import com.team26.todolist.domain.Column;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.request.ColumnMoveRequest;
import com.team26.todolist.dto.request.ColumnRegistrationRequest;
import com.team26.todolist.dto.request.ColumnUpdateRequest;
import com.team26.todolist.dto.response.ColumnResponse;
import com.team26.todolist.repository.ColumnRepository;
import org.springframework.stereotype.Service;

@Service
public class ColumnServiceImpl implements ColumnService{

    private final double DIFFERENCE = 1000.0;

    private final ColumnRepository columnRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
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
        Double orderLeft = columnMoveRequest.getOrderOfLeftColumn();
        Double orderRight = columnMoveRequest.getOrderOfRightColumn();

        if (orderLeft == null) {
            return orderRight - DIFFERENCE;
        }

        if(orderRight == null) {
            return orderLeft + DIFFERENCE;
        }

        return (orderLeft + orderRight) / 2;
    }

    public ColumnResponse modifyColumn(ColumnUpdateRequest columnUpdateRequest) {
        Column columnBefore = columnRepository.findById(columnUpdateRequest.getId());
        Double order = columnBefore.getOrder();

        Column updatedColumn = columnRepository.updateTitle(columnUpdateRequest.toEntity());

        return ColumnResponse.of(updatedColumn);
    }

    public boolean deleteCard(Long id) {
        return columnRepository.delete(id);
    }

}
