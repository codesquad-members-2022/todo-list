import { TEXT_LENGTH_LIMIT } from "../../utils.js";

export class ScheduleEditCard {
    constructor({ original, passedEventHandler }) {
        this.$originalCard = original;
        this.$originalCardTitle = this.$originalCard.querySelector(
            ".schedule-card__title"
        );
        this.$originalCardBody = this.$originalCard.querySelector(
            ".schedule-card__body"
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
            ".schedule-edit-card__title"
        );
        this.$editCardBody = this.$editCard.querySelector(
            ".schedule-edit-card__body"
        );
        this.$editCardEditBtn = this.$editCard.querySelector(
            ".schedule-edit-card__edit-btn"
        );
        this.$editCardCancleBtn = this.$editCard.querySelector(
            ".schedule-edit-card__cancel-btn"
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
            $editBtn.classList.replace("inactive", "active");
        } else {
            $editBtn.classList.replace("active", "inactive");
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
        if (target.classList.contains("inactive")) {
            return;
        }

        const cardId = this.$editCard.dataset.cardId;
        const cardData = {
            title: this.$editCardTitle.value,
            body: this.$editCardBody.value,
            caption: "author by web",
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
        const cardData = this.passedEventHandler.getCardData(cardId);

        this.$editCard = document.createElement("div");
        this.$editCard.classList.add("schedule-edit-card");
        this.$editCard.dataset.cardId = cardId;
        this.$editCard.innerHTML = this.template(cardData.title, cardData.body);

        this.setDOMElement();
    }

    template(cardTitle, cardBody) {
        return `<form class="schedule-edit-card__text-container">
                    <textarea 
                        class="schedule-edit-card__title"  
                        placeholder="제목을 입력하세요"
                        rows="1"
                        maxLength="${TEXT_LENGTH_LIMIT}"
                    >${cardTitle}</textarea>
                    <textarea 
                        class="schedule-edit-card__body" 
                        placeholder="내용을 입력하세요"
                        rows="1"
                        maxLength="${TEXT_LENGTH_LIMIT}"
                    >${cardBody}</textarea>
                </form>
                <div class="schedule-edit-card__btns-container">
                    <button class="schedule-edit-card__cancel-btn">
                        취소
                    </button>
                    <button class="schedule-edit-card__edit-btn active">
                        수정
                    </button>
                </div>`;
    }
}
