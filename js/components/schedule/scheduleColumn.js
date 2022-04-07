//- [ ] 유저가 스케줄 칼럼의 +버튼을 클릭하면 새로운 카드를 등록할 수 있다.
//     - [x] +버튼에 마우스가 올라가면 +버튼의 색이 변한다.
//     - [x] +버튼을 클릭하면 새 카드 등록 박스가 생성된다.
//         - [x] 카드 등록 박스에 제목을 입력하세요, 내용을 입력하세요가 적혀있다.
//     - [x] +버튼을 다시 클릭하면 카드 등록 박스가 사라진다.
//     - [x] 취소 버튼을 누르면 카드 등록 박스가 사라진다.
//     - [x] 내용 입력이 없는 상태에서 등록 버튼은 누를 수 없다. (비활성화)
//     - [x] 내용을 입력하면 등록 버튼을 누를 수 있다.
//         - [x] 내용 입력 글자수는 500자로 제한한다.
//         - [x] 글의 길이에 맞춰 박스 크기가 늘어난다.
//     - [ ] 등록 버튼을 누르면 새로운 카드가 등록된다.
//     - [ ] 카드 등록 박스는 사라진다.
import { ScheduleCard } from "./scheduleCard.js";
import { ScheduleRegisterCard } from "./scheduleRegisterCard.js";

export class ScheduleColumn {
    constructor(target, title) {
        this.$target = target;
        this.$cardsContainer;
        this.title = title;
        this.id = new Date().getTime();
        this.registerCardState = false;
        this.init();
    }

    init() {
        this.render();
        this.$cardsContainer = this.$target.querySelector(
            `[data-id="${this.id}"]`
        );
        this.addCard();
        this.setEvent();
    }

    setEvent() {
        const $addBtn = this.$target.querySelector(".schedule-column__add-btn");
        $addBtn.addEventListener("click", () =>
            this.cardAddBtnClickEventHandler()
        );
    }

    cardAddBtnClickEventHandler() {
        if (this.registerCardState) {
            this.removeRegisterCard();
        } else {
            this.showRegisterCard();
        }
    }

    removeRegisterCard() {
        this.registerCardState = false;
        const $registerCard = this.$cardsContainer.querySelector(
            ".schedule-register-card"
        );
        $registerCard.remove();
    }

    showRegisterCard() {
        this.registerCardState = true;
        const scheduleRegisterCardParams = {
            target: this.$cardsContainer,
            id: this.id,
            passedEventHandler: {
                removeRegisterCard: this.removeRegisterCard.bind(this),
            },
        };
        new ScheduleRegisterCard(scheduleRegisterCardParams);
    }

    render() {
        const $scheduleColumn = this.template();
        this.$target.insertAdjacentHTML("beforeend", $scheduleColumn);
    }

    addCard() {
        const cardData = {
            title: "git hub 공부",
            body: "add commit",
            caption: "author by web",
        };
        new ScheduleCard(this.$cardsContainer, cardData);
    }

    template() {
        return `<div class="schedule-column">
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
            <div class="schedule-column__cards-container" data-id="${this.id}"></div>
        </div>`;
    }
}
