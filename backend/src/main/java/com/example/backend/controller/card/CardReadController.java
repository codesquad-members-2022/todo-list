package com.example.backend.controller.card;

import com.example.backend.controller.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.backend.controller.ApiResult.OK;

@Api("Card 조회 컨트롤러")
@RestController
@RequestMapping("api/read/cards")
public class CardReadController {

    private final CardReadService cardReadService;

    public CardReadController(CardReadService cardReadService) {
        this.cardReadService = cardReadService;
    }

    @GetMapping
    public ApiResult<DailyPlanResponse> getDailyTodoItems(HttpServletRequest request) {
        Author author = new Author(request.getHeader("user-agent"));
        DailyPlanResponse dailyPlanResponse = cardReadService.getDailyPlan();
        dailyPlanResponse.add(author);
        return OK(dailyPlanResponse);
    }
}
