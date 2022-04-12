package com.codesquad.todolist.history.dto;

import com.codesquad.todolist.history.domain.Field;
import com.codesquad.todolist.history.domain.ModifiedField;

public class ModifiedFieldResponse {

    private Integer modifiedFieldId;
    private Field field;
    private String oldValue;
    private String newValue;

    public ModifiedFieldResponse(Integer modifiedFieldId, Field field, String oldValue,
        String newValue) {
        this.modifiedFieldId = modifiedFieldId;
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public static ModifiedFieldResponse from(ModifiedField modifiedField) {
        return new ModifiedFieldResponse(
            modifiedField.getModifiedFieldId(),
            modifiedField.getField(),
            modifiedField.getOldValue(),
            modifiedField.getNewValue()
        );
    }

    public Integer getModifiedFieldId() {
        return modifiedFieldId;
    }

    public Field getField() {
        return field;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    @Override
    public String toString() {
        return "ModifiedFieldResponse{" +
            "modifiedFieldId=" + modifiedFieldId +
            ", field=" + field +
            ", oldValue='" + oldValue + '\'' +
            ", newValue='" + newValue + '\'' +
            '}';
    }
}
