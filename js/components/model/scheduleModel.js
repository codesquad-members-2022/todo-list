import { getId } from "../../utils.js";

export class ScheduleModel {
    constructor(scheduleColumnData) {
        this.scheduleColumnData = scheduleColumnData;
    }

    getScheduleColumnTitle() {
        return this.scheduleColumnData.title;
    }

    getScheduleCards() {
        return this.scheduleColumnData.cards;
    }

    addScheduleCard(cardData) {
        cardData.id = getId();
        this.scheduleColumnData.cards.push(cardData);
    }

    removeScheduleCard(cardId) {
        const index = this.scheduleColumnData.cards.findIndex(
            (card) => card.id === cardId
        );

        this.scheduleColumnData.cards.splice(index, 1);
    }

    updateScheduleCard(cardData) {
        const curCardIdx = this.scheduleColumnData.cards.findIndex(
            (card) => card.id === cardData.id
        );

        this.scheduleColumnData.cards[curCardIdx] = cardData;
    }
}
