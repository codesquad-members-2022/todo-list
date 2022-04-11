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
    $deleteConfirm = document.querySelector(".dim-layer");
}

function setEvent($scheduleCard, passedEventHandler) {
    $deleteConfirm.addEventListener("click", (e) =>
        confirmClickEventHandler(e, $scheduleCard, passedEventHandler)
    );
}

function confirmClickEventHandler(event, $scheduleCard, passedEventHandler) {
    if (
        event.target.classList.contains("schedule-delete-confirm__cancel-btn")
    ) {
        cancelBtnClickEventHandler(passedEventHandler);
    }
    if (
        event.target.classList.contains("schedule-delete-confirm__delete-btn")
    ) {
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
