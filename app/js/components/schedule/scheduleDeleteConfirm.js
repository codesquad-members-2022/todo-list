import {
    DELETE_CONFIRM_CANCLE_BTN,
    DELETE_CONFIRM_DELETE_BTN,
    DIM_LAYER,
} from "../../utils/styleNames.js";

let $deleteConfirm;

export function deleteConfirmInit({ $scheduleCard, passedEventHandler }) {
    passedEventHandler.toggleScheduleCardActiveRed();

    render();
    setDOMElement();
    setEvent($scheduleCard, passedEventHandler);
}

function render() {
    const scheduleDeleteConfirmTemplate = template();
    document.body.insertAdjacentHTML(
        "beforeend",
        scheduleDeleteConfirmTemplate
    );
}

function setDOMElement() {
    $deleteConfirm = document.querySelector(`.${DIM_LAYER}`);
}

function setEvent($scheduleCard, passedEventHandler) {
    $deleteConfirm.addEventListener("click", (e) =>
        confirmClickEventHandler(e, $scheduleCard, passedEventHandler)
    );
}

function confirmClickEventHandler(event, $scheduleCard, passedEventHandler) {
    if (event.target.classList.contains(DELETE_CONFIRM_CANCLE_BTN)) {
        cancelBtnClickEventHandler(passedEventHandler);
    }
    if (event.target.classList.contains(DELETE_CONFIRM_DELETE_BTN)) {
        deleteBtnClickEventHandler($scheduleCard, passedEventHandler);
    }
}

function deleteBtnClickEventHandler($scheduleCard, passedEventHandler) {
    removeDeleteConfirm();
    passedEventHandler.removeCard($scheduleCard);
}

function cancelBtnClickEventHandler(passedEventHandler) {
    passedEventHandler.toggleScheduleCardActiveRed();
    removeDeleteConfirm();
}

function removeDeleteConfirm() {
    $deleteConfirm.remove();
}

function template() {
    return `<div class="${DIM_LAYER}">
        <div class="schedule-delete-confirm">
            <p>선택한 카드를 삭제할까요?</p>
            <div class="schedule-delete-confirm__btns-container">
                <button class="${DELETE_CONFIRM_CANCLE_BTN}">
                    취소
                </button>
                <button class="${DELETE_CONFIRM_DELETE_BTN}">
                    삭제
                </button>
            </div>
        </div>
    </div>`;
}
