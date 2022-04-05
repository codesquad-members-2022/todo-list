package com.todolist.service;

import com.todolist.domain.Category;
import com.todolist.dto.ColumnList;
import com.todolist.dto.WorkList;
import com.todolist.repository.WorkRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public ColumnList getColumnList() {

        List<WorkList> workLists = new ArrayList<>();

        List<Category> categoryList = workRepository.findAllCategories();
        for (Category category : categoryList) {
            workLists.add(new WorkList(
                category.getName(), workRepository.findAllWorksByCategoryId(category.getId())
                )
            );
        }

        return new ColumnList(workLists);
    }
}
