package com.team15.todoapi.repository;

import com.team15.todoapi.domain.History;
import java.util.List;

public interface HistoryRepository {
	List<History> findAll(Long memberId);
}
