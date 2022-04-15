import {
    REGISTER_CANCLE_BTN,
    REGISTER_BTN,
    REGISTER_CARD_TITLE,
    REGISTER_CARD_BODY,
    INACTIVE,
    ACTIVE,
    REGISTER_CARD,
    DEFAULT_AUTHOR,
} from "../../utils/styleNames.js";
import { getId, TEXT_LENGTH_LIMIT } from "../../utils/utils.js";

export class ScheduleRegisterCard {
    constructor() {
        this.$target;
        this.passedEventHandler;
        this.state = false;
        this.$registerCard;
        this.$cardTitle;
        this.$cardBody;
        this.$registerBtn;
    }

    init({ $target, passedEventHandler }) {
        this.render($target);
        this.setDOMElement($target);
        this.passedEventHandler = passedEventHandler;
        this.setEvent();
    }

    render($target) {
        const registerCardTemplate = this.template();
        $target.insertAdjacentHTML("afterbegin", registerCardTemplate);
    }

    setDOMElement($target) {
        this.$registerCard = $target.querySelector(`.${REGISTER_CARD}`);
        this.$cardTitle = this.$registerCard.querySelector(
            `.${REGISTER_CARD_TITLE}`
        );
        this.$cardBody = this.$registerCard.querySelector(
            `.${REGISTER_CARD_BODY}`
        );
        this.$registerBtn = this.$registerCard.querySelector(
            `.${REGISTER_BTN}`
        );
    }

    setEvent() {
        this.$registerCard.addEventListener(
            "click",
            this.registerCardClickEventHandler.bind(this)
        );
        this.$registerCard.addEventListener(
            "input",
            this.registerCardInputEventHandler.bind(this)
        );
    }

    getState() {
        return this.state;
    }

    changeState() {
        this.state = !this.state;
    }

    registerCardClickEventHandler(event) {
        event.preventDefault();

        const target = event.target;
        if (target.classList.contains(REGISTER_CANCLE_BTN)) {
            this.passedEventHandler.removeRegisterCard();
        }
        if (target.classList.contains(REGISTER_BTN)) {
            this.registerBtnClickEventHandler(target);
        }
    }

    registerCardInputEventHandler({ target }) {
        if (target.classList.contains(REGISTER_CARD_TITLE)) {
            this.cardTitleInputEventHandler(target);
        }
        if (target.classList.contains(REGISTER_CARD_BODY)) {
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
        if (target.classList.contains(INACTIVE)) {
            return;
        }

        const cardData = {
            title: this.$cardTitle.value.replace(/\n/g, "<br>"),
            body: this.$cardBody.value.replace(/\n/g, "<br>"),
            caption: DEFAULT_AUTHOR,
            id: getId(),
        };

        this.passedEventHandler.addCard(cardData);
        this.passedEventHandler.removeRegisterCard();
    }

    toggleRegisterBtn(booleanValue) {
        if (booleanValue) {
            this.$registerBtn.classList.replace(INACTIVE, ACTIVE);
        } else {
            this.$registerBtn.classList.replace(ACTIVE, INACTIVE);
        }
    }

    cardTitleInputEventHandler($cardTitle) {
        const cardTitle = $cardTitle.value;
        this.toggleRegisterBtn(cardTitle);
        this.adjustInputHeight($cardTitle);
    }

    template() {
        return `<div class="${REGISTER_CARD}">
                <form class="schedule-register-card__text-container">
                    <textarea 
                        class="${REGISTER_CARD_TITLE}"  
                        placeholder="제목을 입력하세요"
                        rows="1"
                        maxLength="${TEXT_LENGTH_LIMIT}"
                    ></textarea>
                    <textarea 
                        class="${REGISTER_CARD_BODY}" 
                        placeholder="내용을 입력하세요"
                        rows="1"
                        maxLength="${TEXT_LENGTH_LIMIT}"
                    ></textarea>
                </form>
                <div class="schedule-register-card__btns-container">
                    <button class="${REGISTER_CANCLE_BTN}">
                        취소
                    </button>
                    <button class="${REGISTER_BTN} ${INACTIVE}">
                        등록
                    </button>
                </div>
            </div>`;
    }
}
