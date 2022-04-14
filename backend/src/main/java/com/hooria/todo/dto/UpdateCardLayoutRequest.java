package com.hooria.todo.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardLayoutRequest {

    private long id;
    private int rowPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateCardLayoutRequest that = (UpdateCardLayoutRequest) o;
        return id == that.id && rowPosition == that.rowPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rowPosition);
    }
}
