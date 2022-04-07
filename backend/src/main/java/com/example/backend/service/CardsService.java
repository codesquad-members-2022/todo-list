package com.example.backend.service;

import com.example.backend.domain.Column;
import com.example.backend.web.dto.CardSaveRequestDto;
import com.example.backend.web.dto.CardsMoveRequestDto;
import com.example.backend.web.dto.CardsUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public class CardsService {

    public Column findAll() {
        // repository에서 Column 받아오는 부분
        return null;
    }

    public Long save(CardSaveRequestDto dto) {
        // repository에 저장 요청하는 부분
        return null;
    }

    public Long update(CardsUpdateRequestDto dto) {
        // repository에 수정 요청하는 부분
        return null;
    }

    public Long delete(Long id) {
        // repository에 삭제 요청하는 부분
        return null;
    }

    public Long move(CardsMoveRequestDto dto) {
        // repository에 수정 반영하는 부분
        return null;
    }
}
