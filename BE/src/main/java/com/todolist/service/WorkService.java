package com.todolist.service;

import com.todolist.domain.Category;
import com.todolist.domain.Work;
import com.todolist.dto.ColumnListDto;
import com.todolist.dto.WorkDto;
import com.todolist.dto.WorkListDto;
import com.todolist.repository.CategoryRepository;
import com.todolist.repository.WorkRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

    private final CategoryRepository categoryRepository;
    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository, CategoryRepository categoryRepository) {
        this.workRepository = workRepository;
        this.categoryRepository = categoryRepository;
    }

    public ColumnListDto getColumnList(String userId) {
        List<WorkListDto> workLists = new ArrayList<>();

        List<Category> categoryList = categoryRepository.findAllCategories();
        for (Category category : categoryList) {

            int categoryId = category.getId();
            String categoryName = category.getName();
            List<WorkDto> workDtoList = workRepository.findAllWorksByCategoryId(categoryId, userId)
                .stream().map(Work::convertToDto).collect(Collectors.toList());

            workLists.add(
                WorkListDto.builder()
                    .categoryName(categoryName)
                    .works(workDtoList)
                    .build()
            );
        }

        return ColumnListDto.builder().userId(userId).workList(workLists).build();
    }
}
