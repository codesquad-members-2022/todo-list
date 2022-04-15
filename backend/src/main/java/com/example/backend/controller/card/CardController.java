package com.example.backend.controller.card;

import com.example.backend.controller.ApiResult;
import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.controller.card.dto.CardSaveResponse;
import com.example.backend.controller.history.dto.HistorySaveRequest;
import com.example.backend.domain.card.Card;
import com.example.backend.domain.history.Action;
import com.example.backend.service.card.CardReadResponse;
import com.example.backend.service.card.CardService;
import com.example.backend.service.card.CardPositionChangeRequest;
import com.example.backend.service.history.HistoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cards")
public class CardController {

    private final CardService cardService;
    private final HistoryService historyService;

    public CardController(CardService cardService, HistoryService historyService) {
        this.cardService = cardService;
        this.historyService = historyService;
    }

    @PostMapping
    public ApiResult<CardSaveResponse> writeCard(@RequestBody @Valid CardDto cardDto) {
        Card card = cardService.writeCard(cardDto);
        HistorySaveRequest request = new HistorySaveRequest(card.getContent(), card.getWriter(), Action.CREATE, card.getMemberId(), card.getId());
        historyService.saveHistory(request);
        return ApiResult.OK(new CardSaveResponse(card));
    }

    @GetMapping("{id}")
    public ApiResult<CardDto> cardDetailInquiry(@PathVariable Long id) {
        Card card = cardService.findById(id);
        return ApiResult.OK(new CardDto(card));
    }

    @GetMapping
    public ApiResult<Map<String, List<CardReadResponse>>> getCards() {
        return ApiResult.OK(cardService.findAll());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Card card = cardService.findById(id);
        HistorySaveRequest request = new HistorySaveRequest(card.getContent(), card.getWriter(), Action.DELETE, card.getMemberId(), card.getId());
        historyService.saveHistory(request);
        cardService.delete(id);
    }

    @PatchMapping({"{id}"})
    public ApiResult<CardSaveResponse> updateCard(@PathVariable Long id, @RequestBody CardDto cardDto) {
        Card card = cardService.updateCard(id, cardDto);
        HistorySaveRequest request = new HistorySaveRequest(card.getContent(), card.getWriter(), Action.MODIFY, card.getMemberId(), card.getId());
        historyService.saveHistory(request);
        return ApiResult.OK(new CardSaveResponse(card));
    }

    @PatchMapping("move/{id}")
    public String dragAndDrop(@PathVariable Long id, @RequestBody CardPositionChangeRequest request) {
        cardService.changePosition(id, request);
        return "SUCCESS";
    }

//    @PostMapping
//    public ApiResult<?> dragAndDrop(@RequestBody CardDto cardDto){
//
//    }
}
