package com.example.backend.controller.card;

import com.example.backend.controller.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.backend.controller.ApiResult.OK;

@RestController
@RequestMapping("api/read/cards")
public class CardReadController {

    private final CardReadService cardReadService;

    public CardReadController(CardReadService cardReadService) {
        this.cardReadService = cardReadService;
    }

    @GetMapping("")
    public ApiResult<DailyPlan> getDailyTodoItems() {
        return OK(cardReadService.getDailyPlan());
    }
}
