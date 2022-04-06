export class ScheduleRegisterCard {
    constructor(target, id) {
        this.$target = target;
        this.id = id;
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
        $cancelBtn.addEventListener("click", () => this.removeRegisterCard());

        const $cardTitle = this.$target.querySelector(
            ".schedule-register-card__title"
        );
        $cardTitle.addEventListener("input", ({ target }) =>
            this.activateRegisterBtn(target)
        );

        const $registerBtn = this.$target.querySelector(
            ".schedule-register-card__register-btn"
        );
        $registerBtn.addEventListener("click", ({ target }) =>
            this.registerBtnClickEventHandler(target)
        );
    }

    registerBtnClickEventHandler(target) {
        if (target.classList.contains("inactive")) {
            return;
        }

        console.log("register click");
    }

    activateRegisterBtn(target) {
        const cardTitle = target.value;
        const $registerBtn = this.$target.querySelector(
            ".schedule-register-card__register-btn"
        );

        if (cardTitle.length) {
            $registerBtn.classList.replace("inactive", "active");
        } else {
            $registerBtn.classList.replace("active", "inactive");
        }
    }

    template() {
        return `<div class="schedule-register-card" data-id="${this.id}">
                <form class="schedule-register-card__text-container">
                    <input 
                        class="schedule-register-card__title" 
                        type="text" 
                        placeholder="제목을 입력하세요"
                    >
                    </input>
                    <input 
                        class="schedule-register-card__body" 
                        type="text" 
                        placeholder="내용을 입력하세요"
                    >
                    </input>
                </form>
                <div class=schedule-register-card__btns-container>
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
