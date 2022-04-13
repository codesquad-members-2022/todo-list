import { ScheduleCard } from "./scheduleCard.js";
import { ScheduleRegisterCard } from "./scheduleRegisterCard.js";
import * as scheduleModel from "../model/scheduleModel.js";
import { changeCardNumber } from "./scheduleCardCount.js";

export class ScheduleColumn {
    constructor(target, columnId) {
        this.$target = target;
        this.$scheduleColumn;
        this.$cardsContainer;
        this.columnId = columnId;
        this.columnTitle = scheduleModel.getScheduleColumnTitle(this.columnId);
        this.registerCard = new ScheduleRegisterCard();

        this.init();
    }

    init() {
        this.render();
        this.setDOMElement();
        this.renderCards();
        this.setEvent();
        changeCardNumber(this.columnId)
    }

    setDOMElement() {
        this.$scheduleColumn = this.$target.querySelector(
            `[data-id="${this.columnId}"]`
        );
        this.$cardsContainer = this.$scheduleColumn.querySelector(
            ".schedule-column__cards-container"
        );
    }

    renderCards() {
        const cards = scheduleModel.getScheduleCards(this.columnId);
        cards.forEach((cardData) => {
            const scheduleCardParams = {
                target: this.$cardsContainer,
                cardData: cardData,
                passedEventHandler: {
                    removeCard: this.removeCard.bind(this),
                    updateCard: this.updateCard.bind(this),
                    getCardData: this.getCardData.bind(this),
                },
            };
            new ScheduleCard(scheduleCardParams);
        });
    }

    setEvent() {
        const $addBtn = this.$scheduleColumn.querySelector(
            ".schedule-column__add-btn"
        );
        $addBtn.addEventListener(
            "click",
            this.cardAddBtnClickEventHandler.bind(this)
        );
    }

    cardAddBtnClickEventHandler() {
        if (this.registerCard.getState()) {
            this.removeRegisterCard();
        } else {
            this.showRegisterCard();
        }
    }

    removeRegisterCard() {
        this.registerCard.changeState();
        const $registerCard = this.$cardsContainer.querySelector(
            ".schedule-register-card"
        );
        $registerCard.remove();
    }

    showRegisterCard() {
        this.registerCard.changeState();
        const scheduleRegisterCardParams = {
            $target: this.$cardsContainer,
            passedEventHandler: {
                removeRegisterCard: this.removeRegisterCard.bind(this),
                addCard: this.addCard.bind(this),
            },
        };
        this.registerCard.init(scheduleRegisterCardParams);
    }

    render() {
        const scheduleColumnTemplate = this.template();
        this.$target.insertAdjacentHTML("beforeend", scheduleColumnTemplate);
    }

    addCard(cardData) {
        scheduleModel.addScheduleCard(this.columnId, cardData);
        const scheduleCardParams = {
            target: this.$cardsContainer,
            cardData: cardData,
            passedEventHandler: {
                removeCard: this.removeCard.bind(this),
                updateCard: this.updateCard.bind(this),
                getCardData: this.getCardData.bind(this),
            },
        };
        new ScheduleCard(scheduleCardParams);
        changeCardNumber(this.columnId)
    }

    removeCard($target) {
        const cardId = $target.dataset.cardId;
        scheduleModel.removeScheduleCard(this.columnId, cardId);
        $target.remove();
        changeCardNumber(this.columnId)
    }

    updateCard(cardData) {
        scheduleModel.updateScheduleCard(this.columnId, cardData);
    }

    getCardData(columnId, cardId) {
        const cardData = scheduleModel.getScheduleCardDataById(
            columnId,
            cardId
        );
        return cardData;
    }

    template() {
        return `<div class="schedule-column" data-id="${this.columnId}">
            <div class="schedule-column__header">
                <h2 class="schedule-column__title">${this.columnTitle}</h2>
                <div class="schedule-column__count-box">
                    <span class="schedule-column__count-number"></span>
                </div>
                <svg
                    class="schedule-column__add-btn column-btn"
                    width="14"
                    height="14"
                    viewBox="0 0 14 14"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                >
                    <path
                        d="M0.105713 7.53033L0.105713 6.46967H6.46967V0.105713H7.53033V6.46967H13.8943V7.53033H7.53033V13.8943H6.46967V7.53033H0.105713Z"
                        fill="#BDBDBD"
                    />
                </svg>
                <svg
                    class="schedule-column__delete-btn column-btn"
                    width="12"
                    height="12"
                    viewBox="0 0 12 12"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                >
                    <path
                        d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
                        fill="#BDBDBD"
                    />
                </svg>
            </div>
            <div class="schedule-column__cards-container"></div>
        </div>`;
    }
}
