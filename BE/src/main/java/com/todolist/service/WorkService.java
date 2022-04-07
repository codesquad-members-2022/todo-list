package com.todolist.service;

import com.todolist.domain.Category;
import com.todolist.domain.Work;
import com.todolist.dto.ColumnListDto;
import com.todolist.dto.WorkDto;
import com.todolist.dto.WorkListDto;
import com.todolist.repository.WorkRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public ColumnListDto getColumnList(String userId) {
        List<WorkListDto> workLists = new ArrayList<>();

        List<Category> categoryList = workRepository.findAllCategories();
        for (Category category : categoryList) {

            int categoryId = category.getId();
            String categoryName = category.getName();
            List<WorkDto> workDtoList = workRepository.findAllWorksByCategoryId(categoryId, userId)
                .stream().map(Work::convertToDto).collect(Collectors.toList());

            workLists.add(new WorkListDto(categoryName, workDtoList));
        }

        return new ColumnListDto(userId, workLists);
    }
}
