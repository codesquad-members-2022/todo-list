package com.example.backend.web.controller;

import com.example.backend.domain.Column;
import com.example.backend.web.dto.CardSaveRequestDto;
import com.example.backend.web.dto.CardsMoveRequestDto;
import com.example.backend.web.dto.CardsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsController {
    @GetMapping("/cards")
    public Column cardList() {
        // 서비스에서 Column 받아와서 return
        return null;
    }

    @PostMapping("/cards")
    public Long save(@RequestBody CardSaveRequestDto dto) {
        // 서비스에 저장 요청 보내기, 서비스에서 return id;
        return null;
    }

    @PutMapping("/cards/{id}")
    public Long update(@PathVariable Long id, @RequestBody CardsUpdateRequestDto dto) {
        // 서비스에 수정 요청 보내고, id값 리턴받아오기
        return null;
    }

    @DeleteMapping("/cards/{id}")
    public Long delete(@PathVariable Long id) {
        // 서비스에 삭제 요청 보내고, id값 리턴받아오기
        return null;
    }

    @PutMapping("/cards")
    public Long move(@RequestBody CardsMoveRequestDto dto) {
        // 서비스에서 dto를 통해 이동로직 수행 요청 후 id 값 리턴받아오기
        return null;
    }
}
