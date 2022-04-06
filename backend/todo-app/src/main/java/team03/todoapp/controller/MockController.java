package team03.todoapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @GetMapping("/cards")
    public Map<String, List<CardsResponse>> getCards() {
        Map<String, List<CardsResponse>> cardsClassifiedByLocation = new HashMap<>();
        CardsResponse cardsResponse1 = new CardsResponse(1L, "코딩하기", "알고리즘도 풀자", "짱맨", 1L, 2L,
            "2022-04-06 14:55:01");
        CardsResponse cardsResponse2 = new CardsResponse(2L, "청소하기", "청소 쓱쓱", "노리", 2L, null,
            "2022-04-06 14:55:01");
        CardsResponse cardsResponse3 = new CardsResponse(3L, "저금하기", "티클모아", "노리", 3L, 4L,
            "2022-04-06 14:55:01");
        CardsResponse cardsResponse4 = new CardsResponse(4L, "놀러가기", "놀러", "짱맨", 4L, null,
            "2022-04-06 14:55:01");
        CardsResponse cardsResponse5 = new CardsResponse(5L, "쉬기", "침대에서", "노리", 5L, 6L,
            "2022-04-06 14:55:01");
        CardsResponse cardsResponse6 = new CardsResponse(6L, "운동하기", "헬스", "짱맨", 6L, null,
            "2022-04-06 14:55:01");

        cardsClassifiedByLocation.put("todo", List.of(cardsResponse1, cardsResponse2));
        cardsClassifiedByLocation.put("ing", List.of(cardsResponse3, cardsResponse4));
        cardsClassifiedByLocation.put("done", List.of(cardsResponse5, cardsResponse6));

        return cardsClassifiedByLocation;
    }

}
