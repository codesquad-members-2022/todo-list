package com.todolist.service;

import com.todolist.domain.Category;
import com.todolist.domain.Work;
import com.todolist.domain.UserLog;
import com.todolist.dto.ColumnListDto;
import com.todolist.dto.ModifiedWorkDto;
import com.todolist.dto.WorkDeletionDto;
import com.todolist.dto.WorkDto;
import com.todolist.dto.WorkListDto;
import com.todolist.dto.WorkCreationDto;
import com.todolist.dto.WorkModificationDto;
import com.todolist.dto.WorkMovementDto;
import com.todolist.repository.CategoryRepository;
import com.todolist.repository.UserLogRepository;
import com.todolist.repository.WorkRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkService {

    private final CategoryRepository categoryRepository;
    private final WorkRepository workRepository;
    private final UserLogRepository userLogRepository;

    public WorkService(CategoryRepository categoryRepository, WorkRepository workRepository, UserLogRepository userLogRepository) {
        this.categoryRepository = categoryRepository;
        this.workRepository = workRepository;
        this.userLogRepository = userLogRepository;
    }

    public ColumnListDto getColumnList(String userId) {
        List<WorkListDto> workLists = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll(userId);

        for (Category category : categoryList) {
            int categoryId = category.getId();
            String categoryName = category.getName();
            List<WorkDto> works = workRepository.findAllByCategoryId(categoryId)
                .stream().map(Work::convertToDto).collect(Collectors.toList());

            workLists.add(new WorkListDto(categoryId, categoryName, works));
        }

        return new ColumnListDto(userId, workLists);
    }

    @Transactional
    public WorkDto create(WorkCreationDto workCreationDto) {
        Work work = workCreationDto.convertToWorkDomain();
        WorkDto workDto = workRepository.save(work);

        String categoryName = categoryRepository.findNameById(work.getCategoryId());
        UserLog userLog = workCreationDto.convertToUserLogDomain(categoryName);
        userLogRepository.saveLogOfCreationByUser(userLog);

        return workDto;
    }

    @Transactional
    public void move(WorkMovementDto workMovementDto) {
        Work work = workMovementDto.convertToWorkDomain();
        workRepository.updateCategory(work);

        String previousCategoryName = categoryRepository.findNameById(workMovementDto.getPreviousCategoryId());
        String currentCategoryName = categoryRepository.findNameById(workMovementDto.getCurrentCategoryId());
        userLogRepository.saveLogOfMovementByUser(workMovementDto.convertToUserLogDomain(previousCategoryName, currentCategoryName));
    }

    @Transactional
    public void remove(Integer workId, WorkDeletionDto workDeletionDto) {
        workRepository.delete(workId);

        String currentCategoryName = categoryRepository.findNameById(workDeletionDto.getCurrentCategoryId());
        userLogRepository.saveLogOfDeletionByUser(workDeletionDto.convertToUserLogDomain(currentCategoryName));
    }

    @Transactional
    public ModifiedWorkDto modify(Integer workId, WorkModificationDto workModificationDto) {
        String title = workRepository.findTitleById(workId);
        ModifiedWorkDto modifiedWorkDto = workRepository.updateCard(workModificationDto.convertToWorkDomain(workId));

        String currentCategoryName = categoryRepository.findNameById(workModificationDto.getCurrentCategoryId());
        userLogRepository.saveLogOfModificationByUser(workModificationDto.convertToUserLogDomain(title, currentCategoryName));

        return modifiedWorkDto;
    }
}
