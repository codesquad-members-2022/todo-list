package com.todolist.controller;

import static com.todolist.exception.ExceptionType.*;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.domain.dto.CardAllShowDto;
import com.todolist.domain.dto.CardCreateDto;
import com.todolist.domain.dto.CardInformationDto;
import com.todolist.domain.dto.CardMoveDto;
import com.todolist.domain.dto.CardPatchDto;
import com.todolist.domain.dto.CardResponseDto;
import com.todolist.exception.GlobalException;
import com.todolist.service.CardService;

@RequestMapping("/todolist")
@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public CardAllShowDto read(HttpServletRequest request) {
        Cookie[] cookies = Optional.ofNullable(request.getCookies())
            .orElseThrow(() -> new GlobalException(NO_COOKIE));

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                return new CardAllShowDto(cardService.findAllCards(Integer.parseInt(cookie.getValue())));
            }
        }
        throw new GlobalException(NO_USER_ID_IN_COOKIE);
    }

    @PostMapping
    public ResponseEntity<CardResponseDto> save(@Validated @RequestBody CardCreateDto cardCreateDto) {
        Integer cardId = cardService.save(cardCreateDto);
        return new ResponseEntity<>(new CardResponseDto(cardId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<CardResponseDto> delete(@PathVariable Integer cardId) {
        cardService.delete(cardId);
        return ResponseEntity.ok(new CardResponseDto(cardId));
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardInformationDto> findCard(@PathVariable Integer cardId) {
        CardInformationDto cardInformation = cardService.findCardInformation(cardId);
        return ResponseEntity.ok(cardInformation);
    }

    @PatchMapping("/{cardId}")
    public ResponseEntity<CardResponseDto> patch(@PathVariable Integer cardId, @Validated @RequestBody CardPatchDto cardPatchDto) {
        cardService.patch(cardId, cardPatchDto);
        return ResponseEntity.ok(new CardResponseDto(cardId));
    }

    @PatchMapping("/move")
    public ResponseEntity<CardResponseDto> move(@RequestBody CardMoveDto cardMoveDto) {
        Integer cardId = cardService.move(cardMoveDto);
        return ResponseEntity.ok(new CardResponseDto(cardId));
    }
}
