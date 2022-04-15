package team03.todoapp.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team03.todoapp.repository.HistoryRepository;
import team03.todoapp.repository.domain.History;

@Service
public class HistoryService {

    private final Logger log = LoggerFactory.getLogger(HistoryService.class);
    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    public void add(History history) {

        historyRepository.insert(history);
    }

}
