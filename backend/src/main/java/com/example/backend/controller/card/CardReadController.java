package com.example.backend.controller.card;

import com.example.backend.controller.ApiResult;
import com.example.backend.controller.card.dto.DailyPlan;
import com.example.backend.controller.card.dto.UserAgent;
import com.example.backend.service.card.CardReadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.backend.controller.ApiResult.OK;

@RestController
@RequestMapping("api/read/cards")
public class CardReadController {

    public static final String CARD_READ_CONTROLLER = "Card 조회 컨트롤러";
    private final CardReadService cardReadService;

    public CardReadController(CardReadService cardReadService) {
        this.cardReadService = cardReadService;
    }

    @GetMapping("")
    public ApiResult<DailyPlan> getDailyTodoItems(HttpServletRequest request) {
        UserAgent userAgent = new UserAgent(request.getHeader("user-agent"));
        DailyPlan dailyPlan = cardReadService.getDailyPlan();
        dailyPlan.setUserAgent(userAgent);
        return OK(dailyPlan);
    }
}
