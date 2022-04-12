import { ScheduleCard } from "./scheduleCard.js";
import { ScheduleRegisterCard } from "./scheduleRegisterCard.js";
import { ScheduleModel } from "../model/scheduleModel.js";
import { getId } from "../../utils.js";

export class ScheduleColumn {
    constructor(target, scheduleColumnData) {
        this.$target = target;
        this.$scheduleColumn;
        this.$cardsContainer;
        this.scheduleModel = new ScheduleModel(scheduleColumnData);
        this.title = this.scheduleModel.getScheduleColumnTitle();
        this.id = getId();
        this.registerCard = new ScheduleRegisterCard();
        this.dragCard;
        this.move = this.mouseMoveOnDraggingEventHandler.bind(this);
        this.up = this.mouseUpOnDraggingEventHandler.bind(this);
        this.enter = this.mouseEnterOnDraggingEventHandler.bind(this);

        this.init();
    }

    init() {
        this.render();
        this.setDOMElement();
        this.renderCards();
        this.setEvent();
    }

    setDOMElement() {
        this.$scheduleColumn = this.$target.querySelector(
            `[data-id="${this.id}"]`
        );
        this.$cardsContainer = this.$scheduleColumn.querySelector(
            ".schedule-column__cards-container"
        );
    }

    renderCards() {
        const cards = this.scheduleModel.getScheduleCards();
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

        this.$scheduleColumn.addEventListener(
            "mousedown",
            this.mouseDownEventHandler.bind(this)
        );
    }

    isValid2Drag(target, selectedCard) {
        const dragCard = target.closest(".schedule-drag-card");

        return selectedCard && selectedCard !== dragCard;
    }

    relocateDragCard(pageX, pageY) {
        this.dragCard.style.left = `${pageX - this.dragCard.offsetWidth / 2}px`;
        this.dragCard.style.top = `${pageY - this.dragCard.offsetHeight / 2}px`;
    }

    addMouseEventOnDragCard() {
        this.$target.addEventListener("mousemove", this.move);
        this.$target.addEventListener("mouseup", this.up);

        this.$scheduleColumn.addEventListener("mouseenter", this.enter)
    }

    mouseEnterOnDraggingEventHandler(event) {
        const CARD = ".schedule-card";
        const $scheduleCard = event.target.closest(CARD)

        if($scheduleCard) {
            if(this.getLocation($scheduleCard, event.pageY) === 'top') {

            }
        }
    }

    getLocation(element, y) {
        const box = element.getBoundingClientRect()
        const offset = y - (box.top + box.height / 2)
        return offset < 0 ? "top" : "bottom"
    }

    mouseUpOnDraggingEventHandler() {
        this.$target.removeEventListener("mousemove", this.move);
        this.removeMouseUpEvent();
    }

    removeMouseUpEvent() {
        this.$target.removeEventListener("mouseup", this.up);
    }

    mouseMoveOnDraggingEventHandler(event) {
        this.relocateDragCard(event.pageX, event.pageY);
    }

    mouseDownEventHandler(event) {
        const target = event.target;
        const selectedCard = target.closest(".schedule-card");
        if (!this.isValid2Drag(target, selectedCard)) {
            return;
        }

        const CARD = "schedule-card";
        const CARD_AFTER_IMAGE = "schedule-card--afterimage";
        const DRAG_CARD = "schedule-drag-card";
        this.dragCard = selectedCard.cloneNode(true);

        selectedCard.classList.replace(CARD, CARD_AFTER_IMAGE);
        this.dragCard.classList.replace(CARD, DRAG_CARD);
        this.$cardsContainer.appendChild(this.dragCard);

        this.relocateDragCard(event.pageX, event.pageY);
        this.addMouseEventOnDragCard();
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
        this.scheduleModel.addScheduleCard(cardData);
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
    }

    removeCard($target) {
        const cardId = $target.dataset.cardId;
        this.scheduleModel.removeScheduleCard(cardId);
        $target.remove();
    }

    updateCard(cardData) {
        this.scheduleModel.updateScheduleCard(cardData);
    }

    getCardData(cardId) {
        const cardData = this.scheduleModel.getScheduleCardDataById(cardId);
        return cardData;
    }

    template() {
        return `<div class="schedule-column" data-id="${this.id}">
            <div class="schedule-column__header">
                <h2 class="schedule-column__title">${this.title}</h2>
                <div class="schedule-column__count-box">
                    <span class="schedule-column__count-number">0</span>
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
