package codesquad.be.todoserver.service;

import codesquad.be.todoserver.domain.History;
import codesquad.be.todoserver.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HistoryService {

	private final HistoryRepository historyRepository;

	@Autowired
	public HistoryService(HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}

	public List<History> getAllHistory() {

		if (historyRepository.findAllHistory().isEmpty()) {
			throw new NoSuchElementException();
		}
		return historyRepository.findAllHistory();
	}
}
