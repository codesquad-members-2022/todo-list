package com.codesquad.todolist.history.domain;


public class ModifiedField {

    private Integer modifiedFieldId;
    private Integer historyId;
    private Field field;
    private String oldValue;
    private String newValue;

    public ModifiedField(Field field, String oldValue, String newValue) {
        this(null, null, field, oldValue, newValue);
    }

    public ModifiedField(Integer modifiedFieldId, Integer historyId, Field field, String oldValue,
        String newValue) {
        this.modifiedFieldId = modifiedFieldId;
        this.historyId = historyId;
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModifiedField modifiedField = (ModifiedField) o;
        return modifiedFieldId.equals(modifiedField.modifiedFieldId);
    }

    @Override
    public int hashCode() {
        return modifiedFieldId.hashCode();
    }

    public Integer getModifiedFieldId() {
        return modifiedFieldId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
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
        return "ModifiedField{" +
            "historyId=" + historyId +
            ", field=" + field +
            ", oldValue='" + oldValue + '\'' +
            ", newValue='" + newValue + '\'' +
            '}';
    }

}
