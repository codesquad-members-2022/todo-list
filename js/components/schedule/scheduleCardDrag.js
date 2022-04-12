const $main = document.querySelector("#main");

let dragCard;
let afterImageCard;
let $scheduleCardBelow;

const isValid2Drag = (target, selectedCard) => {
    const dragCard = target.closest(".schedule-drag-card");

    return selectedCard && selectedCard !== dragCard;
};

const relocateDragCard = (pageX, pageY) => {
    dragCard.style.left = `${pageX - dragCard.offsetWidth / 2}px`;
    dragCard.style.top = `${pageY - dragCard.offsetHeight / 2}px`;
};

const addMouseEventOnDragCard = () => {
    $main.addEventListener("mousemove", mouseMoveOnDraggingEventHandler);
    $main.addEventListener("mouseup", mouseUpOnDraggingEventHandler);
};

const mouseOverOnDraggingEventHandler = (event) => {
    const CARD = ".schedule-card";
    dragCard.style.display = "none";
    const $scheduleCard = e;
    dragCard.style.display = "block";
    console.log(event.target);

    if ($scheduleCard) {
        console.log("mouse enter schedulecard");
        const location = getLocation($scheduleCard, event.pageY);
        switch (location) {
            case "top": {
                $scheduleCard.insertAdjacentHTML("beforeBegin", afterImageCard);
                break;
            }
            case "bottom": {
            }
        }
    }
};

const getLocation = (element, y) => {
    const box = element.getBoundingClientRect();
    const offset = y - (box.top + box.height / 2);
    return offset < 0 ? "top" : "bottom";
};

const mouseUpOnDraggingEventHandler = () => {
    $main.removeEventListener("mousemove", mouseMoveOnDraggingEventHandler);
    removeMouseUpEvent();
};

const removeMouseUpEvent = () => {
    $main.removeEventListener("mouseup", mouseUpOnDraggingEventHandler);
};

const mouseMoveOnDraggingEventHandler = (event) => {
    relocateDragCard(event.pageX, event.pageY);

    dragCard.style.display = "none";
    const $newScheduleCardBelow = document.elementFromPoint(
        event.clientX,
        event.clientY
    );
    dragCard.style.display = "flex";

    if (
        !$newScheduleCardBelow ||
        !$newScheduleCardBelow.classList.contains("schedule-card") ||
        ($scheduleCardBelow && $newScheduleCardBelow === $scheduleCardBelow)
    ) {
        return;
    }

    $scheduleCardBelow = $newScheduleCardBelow;
    const location = getLocation($scheduleCardBelow, event.pageY);
    switch (location) {
        case "top": {
            $scheduleCardBelow.insertAdjacentHTML(
                "beforeBegin",
                afterImageCard
            );
            break;
        }
        case "bottom": {
        }
    }
};

export const mouseDownEventHandler = (event) => {
    const target = event.target;
    const selectedCard = target.closest(".schedule-card");
    if (!isValid2Drag(target, selectedCard)) {
        return;
    }

    const CARD = "schedule-card";
    const CARD_AFTER_IMAGE = "schedule-card--afterimage";
    const DRAG_CARD = "schedule-drag-card";
    dragCard = selectedCard.cloneNode(true);
    const $cardsContainer = target.closest(".schedule-column__cards-container");

    selectedCard.classList.replace(CARD, CARD_AFTER_IMAGE);
    dragCard.classList.replace(CARD, DRAG_CARD);
    $cardsContainer.appendChild(dragCard);
    afterImageCard = selectedCard;

    relocateDragCard(event.pageX, event.pageY);
    addMouseEventOnDragCard();
};
