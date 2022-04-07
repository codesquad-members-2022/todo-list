package com.example.backend.controller.card;

import com.example.backend.controller.ApiResult;
import com.example.backend.controller.card.dto.DailyPlan;
import com.example.backend.controller.card.dto.Author;
import com.example.backend.service.card.CardReadService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.backend.controller.ApiResult.OK;
import static com.example.backend.controller.card.CardReadController.CARD_READ_CONTROLLER;

@Api(CARD_READ_CONTROLLER)
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
        Author author = new Author(request.getHeader("user-agent"));
        DailyPlan dailyPlan = cardReadService.getDailyPlan();
        dailyPlan.acquire(author);
        return OK(dailyPlan);
    }
}
