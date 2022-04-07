export class ScheduleRegisterCard {
    LIMIT = 500;
    constructor({ target, id, delegatedEventHandler }) {
        this.$target = target;
        this.id = id;
        this.passedEventHandler = delegatedEventHandler;
        this.init();
    }

    init() {
        this.render();
        this.setEvent();
    }

    render() {
        const $registerCard = this.template();
        this.$target.insertAdjacentHTML("afterbegin", $registerCard);
    }

    setEvent() {
        const $cancelBtn = this.$target.querySelector(
            ".schedule-register-card__cancel-btn"
        );
        $cancelBtn.addEventListener("click", () =>
            this.passedEventHandler.removeRegisterCard()
        );

        const $cardTitle = this.$target.querySelector(
            ".schedule-register-card__title"
        );
        $cardTitle.addEventListener("input", ({ target }) =>
            this.cardTitleInputEventHandler(target)
        );

        const $registerBtn = this.$target.querySelector(
            ".schedule-register-card__register-btn"
        );
        $registerBtn.addEventListener("click", ({ target }) =>
            this.registerBtnClickEventHandler(target)
        );

        const $cardBody = this.$target.querySelector(
            ".schedule-register-card__body"
        );
        $cardBody.addEventListener("input", ({ target }) =>
            this.cardBodyInputEventHandler(target)
        );
    }

    adjustInputHeight($input) {
        $input.style.height = $input.scrollHeight + "px";
    }

    cardBodyInputEventHandler($cardBody) {
        this.adjustInputHeight($cardBody);
    }

    registerBtnClickEventHandler(target) {
        if (target.classList.contains("inactive")) {
            return;
        }
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
                        maxLength="${this.LIMIT}"
                    ></textarea>
                    <textarea 
                        class="schedule-register-card__body" 
                        placeholder="내용을 입력하세요"
                        rows="1"
                        maxLength="${this.LIMIT}"
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
