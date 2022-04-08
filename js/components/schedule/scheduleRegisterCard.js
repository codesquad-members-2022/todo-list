import { TEXT_LENGTH_LIMIT } from "../../utils.js";

export class ScheduleRegisterCard {
    constructor({ target, id, passedEventHandler }) {
        this.$target = target;
        this.id = id;
        this.passedEventHandler = passedEventHandler;
        this.init();
    }

    init() {
        this.render();
        this.setEvent();
    }

    render() {
        const registerCardTemplate = this.template();
        this.$target.insertAdjacentHTML("afterbegin", registerCardTemplate);
    }

    setEvent() {
        const $registerCard = this.$target.querySelector(
            ".schedule-register-card"
        );
        $registerCard.addEventListener(
            "click",
            this.registerCardClickEventHandler.bind(this)
        );
        $registerCard.addEventListener(
            "input",
            this.registerCardInputEventHandler.bind(this)
        );
    }

    registerCardClickEventHandler({ target }) {
        const $cancelBtn = this.$target.querySelector(
            ".schedule-register-card__cancel-btn"
        );
        const $registerBtn = this.$target.querySelector(
            ".schedule-register-card__register-btn"
        );

        if (target === $cancelBtn) {
            this.passedEventHandler.removeRegisterCard();
        }
        if (target === $registerBtn) {
            this.registerBtnClickEventHandler(target);
        }
    }

    registerCardInputEventHandler({ target }) {
        const $cardTitle = this.$target.querySelector(
            ".schedule-register-card__title"
        );
        const $cardBody = this.$target.querySelector(
            ".schedule-register-card__body"
        );

        if (target === $cardTitle) {
            this.cardTitleInputEventHandler(target);
        }
        if (target === $cardBody) {
            this.cardBodyInputEventHandler(target);
        }
    }

    adjustInputHeight($input) {
        $input.style.height = `auto`;
        $input.style.height = `${$input.scrollHeight}px`;
    }

    cardBodyInputEventHandler($cardBody) {
        this.adjustInputHeight($cardBody);
    }

    registerBtnClickEventHandler(target) {
        if (target.classList.contains("inactive")) {
            return;
        }

        const $cardTitle = this.$target.querySelector(
            ".schedule-register-card__title"
        );
        const $cardBody = this.$target.querySelector(
            ".schedule-register-card__body"
        );

        const cardData = {
            title: $cardTitle.value.replace(/\n/g, "<br>"),
            body: $cardBody.value.replace(/\n/g, "<br>"),
            caption: "author by web",
        };
        this.passedEventHandler.addCard(cardData);
        this.passedEventHandler.removeRegisterCard();
    }

    toggleRegisterBtn(booleanValue, $registerBtn) {
        if (booleanValue) {
            $registerBtn.classList.replace("inactive", "active");
        } else {
            $registerBtn.classList.replace("active", "inactive");
        }
    }

    cardTitleInputEventHandler($cardTitle) {
        const cardTitle = $cardTitle.value;
        const $registerBtn = this.$target.querySelector(
            ".schedule-register-card__register-btn"
        );

        this.toggleRegisterBtn(cardTitle, $registerBtn);
        this.adjustInputHeight($cardTitle);
    }

    template() {
        return `<div class="schedule-register-card" data-id="${this.id}">
                <form class="schedule-register-card__text-container">
                    <textarea 
                        class="schedule-register-card__title"  
                        placeholder="제목을 입력하세요"
                        rows="1"
                        maxLength="${TEXT_LENGTH_LIMIT}"
                    ></textarea>
                    <textarea 
                        class="schedule-register-card__body" 
                        placeholder="내용을 입력하세요"
                        rows="1"
                        maxLength="${TEXT_LENGTH_LIMIT}"
                    ></textarea>
                </form>
                <div class="schedule-register-card__btns-container">
                    <button class="schedule-register-card__cancel-btn">
                        취소
                    </button>
                    <button class="schedule-register-card__register-btn inactive">
                        등록
                    </button>
                </div>
            </div>`;
    }
}
