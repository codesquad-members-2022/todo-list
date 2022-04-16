import {
    EDIT_CARD,
    EDIT_CARD_TITLE,
    EDIT_CARD_BODY,
    EDIT_BTN,
    EDIT_CANCEL_BTN,
    SCHEDULE_CARD_BODY,
    SCHEDULE_CARD_TITLE,
    SCHEDULE_COLUMN,
    ACTIVE,
    INACTIVE,
    DEFAULT_AUTHOR,
} from "../../utils/styleNames.js";
import { TEXT_LENGTH_LIMIT } from "../../utils/utils.js";

export class ScheduleEditCard {
    constructor({ original, passedEventHandler }) {
        this.$originalCard = original;
        this.$originalCardTitle = this.$originalCard.querySelector(
            `.${SCHEDULE_CARD_TITLE}`
        );
        this.$originalCardBody = this.$originalCard.querySelector(
            `.${SCHEDULE_CARD_BODY}`
        );
        this.passedEventHandler = passedEventHandler;
        this.$editCard;
        this.init();
    }

    init() {
        this.createEditCard();
        this.render();
        this.setEvent();
    }

    setDOMElement() {
        this.$editCardTitle = this.$editCard.querySelector(
            `.${EDIT_CARD_TITLE}`
        );
        this.$editCardBody = this.$editCard.querySelector(`.${EDIT_CARD_BODY}`);
        this.$editCardEditBtn = this.$editCard.querySelector(`.${EDIT_BTN}`);
        this.$editCardCancleBtn = this.$editCard.querySelector(
            `.${EDIT_CANCEL_BTN}`
        );
    }

    render() {
        const parentNode = this.$originalCard.parentNode;
        parentNode.replaceChild(this.$editCard, this.$originalCard);
    }

    setEvent() {
        this.$editCard.addEventListener(
            "click",
            this.clickEventHandler.bind(this)
        );
        this.$editCard.addEventListener(
            "input",
            this.inputEventHandler.bind(this)
        );
    }

    inputEventHandler({ target }) {
        if (target === this.$editCardTitle || target === this.$editCardBody) {
            const cardTitle = this.$editCardTitle.value;

            this.toggleEditBtn(cardTitle, this.$editCardEditBtn);
            this.adjustInputHeight(this.$editCardTitle);
            this.adjustInputHeight(this.$editCardBody);
        }
    }

    toggleEditBtn(booleanValue, $editBtn) {
        if (booleanValue) {
            $editBtn.classList.replace(INACTIVE, ACTIVE);
        } else {
            $editBtn.classList.replace(ACTIVE, INACTIVE);
        }
    }

    adjustInputHeight($input) {
        $input.style.height = `auto`;
        $input.style.height = `${$input.scrollHeight}px`;
    }

    clickEventHandler({ target }) {
        if (target === this.$editCardCancleBtn) {
            this.cancelEdit();
        }
        if (target === this.$editCardEditBtn) {
            this.editBtnClickEventHandler(target);
        }
    }

    editBtnClickEventHandler(target) {
        if (target.classList.contains(INACTIVE)) {
            return;
        }

        const cardId = this.$editCard.dataset.cardId;
        const cardData = {
            title: this.$editCardTitle.value,
            body: this.$editCardBody.value,
            caption: DEFAULT_AUTHOR,
            id: cardId,
        };
        this.passedEventHandler.updateCard(cardData);

        this.$originalCardTitle.innerText = cardData.title;
        this.$originalCardBody.innerText = cardData.body;

        const parentNode = this.$editCard.parentNode;
        parentNode.replaceChild(this.$originalCard, this.$editCard);
    }

    cancelEdit() {
        const parentNode = this.$editCard.parentNode;
        parentNode.replaceChild(this.$originalCard, this.$editCard);
    }

    createEditCard() {
        const cardId = this.$originalCard.dataset.cardId;
        const columnId = this.$originalCard.closest(`.${SCHEDULE_COLUMN}`)
            .dataset.id;
        const cardData = this.passedEventHandler.getCardData(columnId, cardId);

        this.$editCard = document.createElement("div");
        this.$editCard.classList.add(EDIT_CARD);
        this.$editCard.dataset.cardId = cardId;
        this.$editCard.innerHTML = this.template(cardData.title, cardData.body);

        this.setDOMElement();
    }

    template(cardTitle, cardBody) {
        return `<form class="schedule-edit-card__text-container">
                    <textarea 
                        class="${EDIT_CARD_TITLE}"  
                        placeholder="제목을 입력하세요"
                        rows="1"
                        maxLength="${TEXT_LENGTH_LIMIT}"
                    >${cardTitle}</textarea>
                    <textarea 
                        class="${EDIT_CARD_BODY}" 
                        placeholder="내용을 입력하세요"
                        rows="1"
                        maxLength="${TEXT_LENGTH_LIMIT}"
                    >${cardBody}</textarea>
                </form>
                <div class="schedule-edit-card__btns-container">
                    <button class="${EDIT_CANCEL_BTN}">
                        취소
                    </button>
                    <button class="${EDIT_BTN} ${ACTIVE}">
                        수정
                    </button>
                </div>`;
    }
}
