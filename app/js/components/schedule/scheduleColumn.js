import { ScheduleCard } from "./scheduleCard.js";
import { ScheduleRegisterCard } from "./scheduleRegisterCard.js";
import * as scheduleModel from "../../model/scheduleModel.js";
import { changeCardNumber } from "./scheduleCardCount.js";
import { recordPostEvent, recordDeleteEvent } from "../../model/history.js";
import {
    REGISTER_CARD,
    SCHEDULE_ADD_BTN,
    SCHEDULE_CARDS,
    SCHEDULE_CARDS_CONTAINER,
    SCHEDULE_COLUMN,
    SCHEDULE_COUNT_NUM,
} from "../../utils/styleNames.js";

export class ScheduleColumn {
    constructor(target, columnId) {
        this.$target = target;
        this.$scheduleColumn;
        this.$cards;
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
        changeCardNumber(this.columnId);
    }

    setDOMElement() {
        this.$scheduleColumn = this.$target.querySelector(
            `[data-id="${this.columnId}"]`
        );
        this.$cards = this.$scheduleColumn.querySelector(`.${SCHEDULE_CARDS}`);
    }

    renderCards() {
        const cards = scheduleModel.getScheduleCards(this.columnId);
        cards.forEach((cardData) => {
            if (!cardData.id) {
                return;
            }
            const scheduleCardParams = {
                target: this.$cards,
                cardData: cardData,
                passedEventHandler: {
                    removeCard: this.removeCard.bind(this),
                    updateCard: this.updateCard.bind(this),
                    getCardData: this.getCardData.bind(this),
                },
                registerState: false,
            };
            new ScheduleCard(scheduleCardParams);
        });
    }

    setEvent() {
        const $addBtn = this.$scheduleColumn.querySelector(
            `.${SCHEDULE_ADD_BTN}`
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
        const $registerCard = this.$cards.querySelector(`.${REGISTER_CARD}`);
        $registerCard.remove();
        this.registerCard.changeState();
    }

    showRegisterCard() {
        const scheduleRegisterCardParams = {
            $target: this.$cards,
            passedEventHandler: {
                removeRegisterCard: this.removeRegisterCard.bind(this),
                addCard: this.addCard.bind(this),
            },
        };
        this.registerCard.init(scheduleRegisterCardParams);
        this.registerCard.changeState();
    }

    render() {
        const scheduleColumnTemplate = this.template();
        this.$target.insertAdjacentHTML("beforeend", scheduleColumnTemplate);
    }

    addCard(cardData) {
        scheduleModel.addScheduleCard(this.columnId, cardData);
        recordPostEvent(this.columnId, this.columnTitle, cardData);
        const scheduleCardParams = {
            target: this.$cards,
            cardData: cardData,
            passedEventHandler: {
                removeCard: this.removeCard.bind(this),
                updateCard: this.updateCard.bind(this),
                getCardData: this.getCardData.bind(this),
            },
            registerState: true,
        };
        new ScheduleCard(scheduleCardParams);
        changeCardNumber(this.columnId);
    }

    removeCard($target) {
        const cardId = $target.dataset.cardId;
        const columnId = $target.closest(`.${SCHEDULE_COLUMN}`).dataset.id;

        scheduleModel.removeScheduleCard(columnId, cardId);
        recordDeleteEvent(cardId);
        $target.remove();
        changeCardNumber(columnId);
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
        return `<div class="${SCHEDULE_COLUMN}" data-id="${this.columnId}" data-title="${this.columnTitle}">
            <div class="schedule-column__header">
                <h2 class="schedule-column__title">${this.columnTitle}</h2>
                <div class="schedule-column__count-box">
                    <span class="${SCHEDULE_COUNT_NUM}"></span>
                </div>
                <svg
                    class="${SCHEDULE_ADD_BTN} column-btn"
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
            <div class="${SCHEDULE_CARDS_CONTAINER}">
                <div class="${SCHEDULE_CARDS}">
                </div>
            </div>
        </div>`;
    }
}
