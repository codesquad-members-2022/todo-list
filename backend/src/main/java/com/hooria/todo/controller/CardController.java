package com.hooria.todo.controller;

import com.hooria.todo.domain.Card;
import com.hooria.todo.dto.AddCardParam;
import com.hooria.todo.dto.CardResponse;
import com.hooria.todo.repository.CardRepository;
import com.hooria.todo.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Card(타스크) Controller")
@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardRepository cardRepository;
    private final CardService cardService;

    @ApiOperation(
        value = "새로운 타스크 등록",
        notes = "새로운 타스크를 등록한다.",
        produces = "application/json",
        response = CardResponse.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "addCardParam",
            value = "새로운 타스크"
        )
    })
    @ApiResponses({
        @ApiResponse(code = 201, message = "등록 성공"),
        @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping
    public CardResponse addCard(@RequestBody AddCardParam addCardParam) {
        return cardService.add(addCardParam);
    }

    @ApiOperation(
        value = "모든 타스크 목록 조회",
        notes = "모든 타스크 목록을 조회한다.",
        produces = "application/json",
        response = List.class
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "조회 성공"),
        @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping
    public List<CardResponse> getCards() {
        return cardService.selectAll();
    }

    @ApiOperation(
        value = "타스크 수정",
        notes = "타스크를 수정한다.",
        produces = "application/json",
        response = Card.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "card",
            value = "새로운 할 일"
        )
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "수정 성공"),
        @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PatchMapping
    public Card updateCard(@RequestBody Card card) {
        return cardRepository.update(card);
    }

    @ApiOperation(
        value = "id 에 해당하는 할 일 삭제",
        notes = "id 에 해당하는 할 일을 삭제한다.",
        produces = "application/json",
        response = Long.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "id",
            value = "타스크 아이디"
        )
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "삭제 성공"),
        @ApiResponse(code = 500, message = "서버 에러"),
    })
    @DeleteMapping("{id}")
    public long delete(@PathVariable long id) {
        return cardRepository.delete(id);
    }
}
