package team03.todoapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.todoapp.controller.dto.CardResponse;
import team03.todoapp.controller.dto.CardsResponse;

@RestController
public class MockController {

    @GetMapping("/cards")
    public CardsResponse getCards() {
        CardsResponse cardsResponse = new CardsResponse();
        CardResponse cardResponse1 = new CardResponse(1L, "코딩하기", "알고리즘도 풀자", "짱맨", 2L,
            "2022-04-06 14:55:01");
        CardResponse cardResponse2 = new CardResponse(2L, "청소하기", "청소 쓱쓱", "노리", null,
            "2022-04-06 14:55:01");
        CardResponse cardResponse3 = new CardResponse(3L, "저금하기", "티클모아", "노리", 4L,
            "2022-04-06 14:55:01");
        CardResponse cardResponse4 = new CardResponse(4L, "놀러가기", "놀러", "짱맨", null,
            "2022-04-06 14:55:01");
        CardResponse cardResponse5 = new CardResponse(5L, "쉬기", "침대에서", "노리", 6L,
            "2022-04-06 14:55:01");
        CardResponse cardResponse6 = new CardResponse(6L, "운동하기", "헬스", "짱맨", null,
            "2022-04-06 14:55:01");

        cardsResponse.putCards("todo", List.of(cardResponse1, cardResponse2));
        cardsResponse.putCards("ing", List.of(cardResponse3, cardResponse4));
        cardsResponse.putCards("done", List.of(cardResponse5, cardResponse6));

        return cardsResponse;
    }

}
