export class ScheduleRegisterCard {
    constructor(target, id) {
        this.$target = target;
        this.id = id;
        this.init();
    }

    init() {
        this.render();
    }

    render() {
        const $registerCard = this.template();
        this.$target.insertAdjacentHTML("afterbegin", $registerCard);
    }

    template() {
        return `<div class="schedule-register-card">
                <form class="schedule-register-card__text-container">
                    <input 
                        class="schedule-register-card__title" 
                        type="text" 
                        placeholder="제목을 입력하세요"
                        data-id="${this.id}"
                    >
                    </input>
                    <input 
                        class="schedule-register-card__body" 
                        type="text" 
                        placeholder="내용을 입력하세요"
                        data-id="${this.id}"
                    >
                    </input>
                </form>
                <div class=schedule-register-card__btns-container>
                    <button class="schedule-register-card__cancel-btn">
                        취소
                    </button>
                    <button class="schedule-register-card__register-btn">
                        등록
                    </button>
                </div>
            </div>`;
    }
}
