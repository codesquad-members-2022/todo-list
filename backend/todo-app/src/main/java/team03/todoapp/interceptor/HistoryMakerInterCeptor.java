package team03.todoapp.interceptor;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import team03.todoapp.Service.HistoryService;
import team03.todoapp.repository.domain.History;

@Component
public class HistoryMakerInterCeptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(HistoryMakerInterCeptor.class);
    private final HistoryService historyService;

    @Autowired
    public HistoryMakerInterCeptor(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {

        History history = new History(
            request.getAttribute("actionType").toString(),
            request.getAttribute("cardTitle").toString(),
            Optional.ofNullable((String)request.getAttribute("pastLocation")),
            request.getAttribute("nowLocation").toString(),
            LocalDateTime.now()
            );

        historyService.add(history);
        log.debug("history Added : {}", history);
    }

}
