package codesquad.be.todoserver.service;

import codesquad.be.todoserver.domain.History;
import codesquad.be.todoserver.repository.HistoryRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

	private final HistoryRepository historyRepository;

	@Autowired
	public HistoryService(HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}

	public List<History> getAllHistory() {
		List<History> histories = historyRepository.findAllHistory();

		if (histories.isEmpty()) {
			throw new NoSuchElementException("Empty History");
		}
		return histories;
	}
}
