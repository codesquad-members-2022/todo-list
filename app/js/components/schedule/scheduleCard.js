import {
    SCHEDULE_CARD,
    SCHEDULE_CARD_BODY,
    SCHEDULE_CARD_DELETE_BTN,
    SCHEDULE_CARD_TITLE,
} from "../../utils/styleNames.js";
import { deleteConfirmInit } from "./scheduleDeleteConfirm.js";
import { ScheduleEditCard } from "./scheduleEditCard.js";

export class ScheduleCard {
    constructor({ target, cardData, passedEventHandler, registerState }) {
        this.$target = target;
        this.$scheduleCard;
        this.cardData = cardData;
        this.passedEventHandler = passedEventHandler;
        this.editCard;
        this.registerState = registerState;
        this.init();
    }

    init() {
        this.render();
        this.setDOMElement();
        this.setEvent();
    }

    render() {
        const scheduleCardTemplate = this.template();
        if (this.registerState) {
            this.$target.insertAdjacentHTML("afterbegin", scheduleCardTemplate);
        } else {
            this.$target.insertAdjacentHTML("beforeend", scheduleCardTemplate);
        }
    }

    setDOMElement() {
        this.$scheduleCard = this.$target.querySelector(
            `[data-card-id="${this.cardData.id}"]`
        );
    }

    setEvent() {
        this.setDeleteCardEvent();

        this.$scheduleCard.addEventListener(
            "dblclick",
            this.createEditCard.bind(this)
        );
    }

    setDeleteCardEvent() {
        const $scheduleCardDeleteBtn = this.$scheduleCard.querySelector(
            `.${SCHEDULE_CARD_DELETE_BTN}`
        );
        $scheduleCardDeleteBtn.addEventListener(
            "mouseenter",
            this.toggleScheduleCardActiveRed.bind(this)
        );
        $scheduleCardDeleteBtn.addEventListener(
            "mouseleave",
            this.toggleScheduleCardActiveRed.bind(this)
        );
        $scheduleCardDeleteBtn.addEventListener(
            "click",
            this.cardDeleteBtnClickEventHandler.bind(this)
        );
    }

    cardDeleteBtnClickEventHandler(event) {
        event.preventDefault();
        const scheduleDeleteConfirmParams = {
            $scheduleCard: this.$scheduleCard,
            passedEventHandler: {
                removeCard: this.passedEventHandler.removeCard,
                toggleScheduleCardActiveRed:
                    this.toggleScheduleCardActiveRed.bind(this),
            },
        };
        deleteConfirmInit(scheduleDeleteConfirmParams);
    }

    toggleScheduleCardActiveRed() {
        this.$scheduleCard.classList.toggle("schedule-card--active-red");
    }

    createEditCard({ target }) {
        if (!this.editCard) {
            const $selectedCard = target.closest(`.${SCHEDULE_CARD}`);
            const scheduleEditCardParams = {
                original: $selectedCard,
                passedEventHandler: {
                    updateCard: this.passedEventHandler.updateCard,
                    getCardData: this.passedEventHandler.getCardData,
                },
            };

            this.editCard = new ScheduleEditCard(scheduleEditCardParams);
            return;
        }

        this.editCard.render();
    }

    template() {
        return `<div class="${SCHEDULE_CARD}" data-card-id="${this.cardData.id}">
                    <div class="schedule-card__text-container">
                        <h3 class="${SCHEDULE_CARD_TITLE}">
                            ${this.cardData.title}
                        </h3>
                        <p class="${SCHEDULE_CARD_BODY}">${this.cardData.body}</p>
                        <p class="schedule-card__caption">${this.cardData.caption}</p>
                    </div>
                    <svg
                        class="${SCHEDULE_CARD_DELETE_BTN}"
                        width="12"
                        height="12"
                        viewBox="0 0 12 12"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
                        />
                    </svg>
                </div>`;
    }
}
