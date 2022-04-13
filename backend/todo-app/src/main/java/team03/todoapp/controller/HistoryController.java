package team03.todoapp.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.todoapp.Service.HistoryService;
import team03.todoapp.repository.domain.History;

@RestController
public class HistoryController {

    private Logger log = LoggerFactory.getLogger(HistoryController.class);
    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/histories")
    public List<History> getHistories() {
        return historyService.getAllHistories();
    }

}
