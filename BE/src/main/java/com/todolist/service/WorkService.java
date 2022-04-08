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
import java.time.LocalDateTime;
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
            List<WorkDto> workDtoList = workRepository.findAllByCategoryId(categoryId, userId)
                .stream().map(Work::convertToDto).collect(Collectors.toList());

            workLists.add(
                WorkListDto.builder()
                    .categoryId(categoryId)
                    .categoryName(categoryName)
                    .works(workDtoList)
                    .build()
            );
        }

        return ColumnListDto.builder().userId(userId).workList(workLists).build();
    }

    @Transactional
    public WorkDto create(WorkRequestFormDto workRequestFormDto, String userId, Integer categoryId) {
        // 카드 저장
        Work work = workRequestFormDto.convertToDomain(categoryId, userId);
        WorkDto workDto = workRepository.save(work);

        // 활동 기록 저장
        Integer workId = workDto.getId();
        String categoryName = categoryRepository.findNameById(categoryId);

        WorkLog workLog = new WorkLog(workId, CREATION, categoryName, LocalDateTime.now());
        workLogRepository.save(workLog);

        return workDto;
    }
}
