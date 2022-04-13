import { TEXT_LENGTH_LIMIT } from "../../utils.js";
import {
    getScheduleCardDataById,
    updateScheduleCard,
} from "../model/scheduleModel.js";

let $originalCard;
let $originalCardTitle;
let $originalCardBody;

let cardId;
let columnId;

let $editCard;
let $editCardTitle;
let $editCardBody;
let $editCardEditBtn;
let $editCardCancleBtn;

let isShow = false;

export const scheduleEditCardInit = ({ target }) => {
    if (isShow) {
        return;
    }

    $originalCard = target.closest(".schedule-card");
    if (!$originalCard) {
        return;
    }

    setOrigianlCardValue();

    $editCard = createEditCard();
    setEditCardValue();

    render();
    setEvent();
};

const setOrigianlCardValue = () => {
    $originalCardTitle = $originalCard.querySelector(".schedule-card__title");
    $originalCardBody = $originalCard.querySelector(".schedule-card__body");

    cardId = $originalCard.dataset.cardId;
    columnId = $originalCard.closest(".schedule-column").dataset.id;
};

const setEditCardValue = () => {
    $editCardTitle = $editCard.querySelector(".schedule-edit-card__title");
    $editCardBody = $editCard.querySelector(".schedule-edit-card__body");
    $editCardEditBtn = $editCard.querySelector(".schedule-edit-card__edit-btn");
    $editCardCancleBtn = $editCard.querySelector(
        ".schedule-edit-card__cancel-btn"
    );
};

const render = () => {
    const parentNode = $originalCard.parentNode;
    parentNode.replaceChild($editCard, $originalCard);
    isShow = true;
};

const setEvent = () => {
    $editCard.addEventListener("click", clickEventHandler);
    $editCard.addEventListener("input", inputEventHandler);
};

const toggleEditBtn = (booleanValue) => {
    if (booleanValue) {
        $editCardEditBtn.classList.replace("inactive", "active");
    } else {
        $editCardEditBtn.classList.replace("active", "inactive");
    }
};

const adjustInputHeight = ($input) => {
    $input.style.height = `auto`;
    $input.style.height = `${$input.scrollHeight}px`;
};

const inputEventHandler = ({ target }) => {
    if (target === $editCardTitle || target === $editCardBody) {
        const cardTitle = $editCardTitle.value;

        toggleEditBtn(cardTitle);
        adjustInputHeight($editCardTitle);
        adjustInputHeight($editCardBody);
    }
};

const cancelEdit = () => {
    const parentNode = $editCard.parentNode;
    parentNode.replaceChild($originalCard, $editCard);
    isShow = false;
};

const editBtnClickEventHandler = (target) => {
    if (target.classList.contains("inactive")) {
        return;
    }

    const cardData = {
        title: $editCardTitle.value,
        body: $editCardBody.value,
        caption: "author by web",
        id: cardId,
    };
    updateScheduleCard(columnId, cardData);

    $originalCardTitle.innerText = cardData.title;
    $originalCardBody.innerText = cardData.body;

    const parentNode = $editCard.parentNode;
    parentNode.replaceChild($originalCard, $editCard);
    isShow = false;
};

const clickEventHandler = ({ target }) => {
    if (target === $editCardCancleBtn) {
        cancelEdit();
    }
    if (target === $editCardEditBtn) {
        editBtnClickEventHandler(target);
    }
};

const createEditCard = () => {
    const cardData = getScheduleCardDataById(columnId, cardId);

    $editCard = document.createElement("div");
    $editCard.classList.add("schedule-edit-card");
    $editCard.dataset.cardId = cardId;
    $editCard.innerHTML = template(cardData.title, cardData.body);

    return $editCard;
};

const template = (cardTitle, cardBody) => {
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
};
