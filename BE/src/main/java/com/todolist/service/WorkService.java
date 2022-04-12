package com.todolist.service;

import com.todolist.domain.Category;
import com.todolist.domain.Work;
import com.todolist.domain.WorkLog;
import com.todolist.dto.ColumnListDto;
import com.todolist.dto.WorkDto;
import com.todolist.dto.WorkListDto;
import com.todolist.dto.WorkRequestFormDto;
import com.todolist.repository.CategoryRepository;
import com.todolist.repository.WorkLogRepository;
import com.todolist.repository.WorkRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkService {

    private static final String CREATION = "등록";

    private final CategoryRepository categoryRepository;
    private final WorkRepository workRepository;
    private final WorkLogRepository workLogRepository;

    public WorkService(CategoryRepository categoryRepository, WorkRepository workRepository, WorkLogRepository workLogRepository) {
        this.categoryRepository = categoryRepository;
        this.workRepository = workRepository;
        this.workLogRepository = workLogRepository;
    }

    public ColumnListDto getColumnList(String userId) {
        List<WorkListDto> workLists = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();

        for (Category category : categoryList) {
            int categoryId = category.getId();
            String categoryName = category.getName();

            List<WorkDto> works = workRepository.findAllByCategoryId(categoryId, userId)
                .stream().map(Work::convertToDto).collect(Collectors.toList());

            workLists.add(new WorkListDto(categoryId, categoryName, works));
        }

        return new ColumnListDto(userId, workLists);
    }

    @Transactional
    public WorkDto create(WorkRequestFormDto workRequestFormDto) {
        // 카드 저장
        Work work = workRequestFormDto.convertToDomain();
        WorkDto workDto = workRepository.save(work);

        // 활동 기록 저장
        String userId = work.getUserId();
        String title = work.getTitle();
        String categoryName = categoryRepository.findNameById(work.getCategoryId());
        WorkLog workLog = new WorkLog(userId, title, CREATION, categoryName);
        workLogRepository.saveCreationLog(workLog);

        return workDto;
    }
}
