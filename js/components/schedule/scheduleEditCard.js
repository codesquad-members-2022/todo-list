import { TEXT_LENGTH_LIMIT } from "../../utils.js";

export class ScheduleEditCard {
    constructor({ original, passedEventHandler }) {
        this.$originalCard = original;
        this.passedEventHandler = passedEventHandler;
        this.$editCard;
        this.init();
    }

    init() {
        this.render();
        this.setEvent();
    }

    render() {
        this.createEditCard();
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
        const $cardTitle = this.$editCard.querySelector(
            ".schedule-edit-card__title"
        );
        const $cardBody = this.$editCard.querySelector(
            ".schedule-edit-card__body"
        );

        if (target === $cardTitle || target === $cardBody) {
            const cardTitle = $cardTitle.value;
            const $editBtn = this.$editCard.querySelector(
                ".schedule-edit-card__edit-btn"
            );

            this.toggleEditBtn(cardTitle, $editBtn);
            this.adjustInputHeight($cardTitle);
            this.adjustInputHeight($cardBody);
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
        const $cancelBtn = this.$editCard.querySelector(
            ".schedule-edit-card__cancel-btn"
        );
        const $editBtn = this.$editCard.querySelector(
            ".schedule-edit-card__edit-btn"
        );

        if (target === $cancelBtn) {
            this.cancelEdit();
        }
        if (target === $editBtn) {
            this.editBtnClickEventHandler(target);
        }
    }

    editBtnClickEventHandler(target) {
        if (target.classList.contains("inactive")) {
            return;
        }

        const $cardTitle = this.$editCard.querySelector(
            ".schedule-edit-card__title"
        );
        const $cardBody = this.$editCard.querySelector(
            ".schedule-edit-card__body"
        );

        const cardId = this.$editCard.dataset.cardId;

        const cardData = {
            title: $cardTitle.value,
            body: $cardBody.value,
            caption: "author by web",
            id: cardId,
        };

        const $cardTitleOfCardOnEditing = this.$originalCard.querySelector(
            ".schedule-card__title"
        );
        const $cardBodyOfCardOnEditing = this.$originalCard.querySelector(
            ".schedule-card__body"
        );
        $cardTitleOfCardOnEditing.innerText = cardData.title;
        $cardBodyOfCardOnEditing.innerText = cardData.body;

        const parentNode = this.$editCard.parentNode;
        parentNode.replaceChild(this.$originalCard, this.$editCard);
        this.passedEventHandler.updateCard(cardData);
    }

    cancelEdit() {
        const parentNode = this.$editCard.parentNode;
        parentNode.replaceChild(this.$originalCard, this.$editCard);
    }

    createEditCard() {
        const cardTitle = this.$originalCard.querySelector(
            ".schedule-card__title"
        ).innerText;
        const cardBody = this.$originalCard.querySelector(
            ".schedule-card__body"
        ).innerText;

        this.$editCard = document.createElement("div");
        this.$editCard.classList.add("schedule-edit-card");
        this.$editCard.dataset.cardId = this.$originalCard.dataset.cardId;
        this.$editCard.innerHTML = this.template(cardTitle, cardBody);
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
