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
        cardData.id = new Date().getTime();
        this.scheduleColumnData.cards.push(cardData);
    }

    removeScheduleCard(cardId) {
        const index = this.scheduleColumnData.cards.findIndex(
            (card) => card.id === Number(cardId)
        );
        this.scheduleColumnData.cards.splice(index, 1);
    }
}
