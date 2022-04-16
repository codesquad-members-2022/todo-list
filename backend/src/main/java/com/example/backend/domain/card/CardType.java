package com.example.backend.domain.card;

public enum CardType {
    TODO("todoItems"),
    COMPLETED("completedItems"),
    PROGRESSING("progressingItems");

    private final String name;

    CardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEqualTo(CardType cardType){
        return cardType.equals(this);
    }
}
