export class ScheduleDeleteConfirm {
    constructor({ target, passedEventHandler }) {
        this.$target = target;
        this.passedEventHandler = passedEventHandler;
        this.$deleteConfirm;
        this.init();
    }

    init() {
        this.render();
        this.setDOMElement();
        this.setEvent();
    }

    render() {
        this.$target.classList.toggle("schedule-card--active-red");

        const scheduleDeleteConfirmTemplate = this.template();
        document.body.insertAdjacentHTML(
            "beforeend",
            scheduleDeleteConfirmTemplate
        );
    }

    setDOMElement() {
        this.$deleteConfirm = document.querySelector(".dim-layer");
    }

    setEvent() {
        const $confirmCancelBtn = document.querySelector(
            ".schedule-delete-confirm__cancel-btn"
        );
        const $confirmDeleteBtn = document.querySelector(
            ".schedule-delete-confirm__delete-btn"
        );

        $confirmCancelBtn.addEventListener(
            "click",
            this.confirmCancelBtnClickEventHandler.bind(this)
        );
        $confirmDeleteBtn.addEventListener(
            "click",
            this.confirmDeleteBtnClickEventHandler.bind(this)
        );
    }

    confirmDeleteBtnClickEventHandler() {
        this.$target.classList.toggle("schedule-card--active-red");
        this.removeDeleteConfirm();
        this.passedEventHandler.removeCard(this.$target);
    }

    confirmCancelBtnClickEventHandler() {
        this.$target.classList.toggle("schedule-card--active-red");
        this.removeDeleteConfirm();
    }

    removeDeleteConfirm() {
        this.$deleteConfirm.remove();
    }

    template() {
        return `<div class="dim-layer">
        <div class="schedule-delete-confirm">
            <p>선택한 카드를 삭제할까요?</p>
            <div class="schedule-delete-confirm__btns-container">
                <button class="schedule-delete-confirm__cancel-btn">
                    취소
                </button>
                <button class="schedule-delete-confirm__delete-btn">
                    삭제
                </button>
            </div>
        </div>
    </div>`;
    }
}
