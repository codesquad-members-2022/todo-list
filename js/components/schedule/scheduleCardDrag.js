const $main = document.querySelector("#main");
let dragCard;
let afterimageCard;
let $scheduleCardBelowDragPointer;
let selectedCard;

const TOP = "top";
const BOTTOM = "bottom";
const CARD = "schedule-card";
const CARD_AFTERIMAGE = "schedule-card--afterimage";
const DRAG_CARD = "schedule-drag-card";

const getDragDirection = (element, y) => {
    const box = element.getBoundingClientRect();
    const offset = y - (box.top + box.height / 2);
    return offset < 0 ? TOP : BOTTOM;
};

const getElementBelowDragPointer = (event) => {
    dragCard.style.display = "none";
    const $elementBelowDragPointer = document.elementFromPoint(
        event.clientX,
        event.clientY
    );
    dragCard.style.display = "flex";

    return $elementBelowDragPointer;
};

const appendAfterImageCardInEmptyCardContainer = ($cardContainer) => {
    if ($cardContainer.children.length) {
        return;
    }
    const tempAfterImageCard = afterimageCard;
    afterimageCard.remove();
    $cardContainer.appendChild(tempAfterImageCard);
    afterimageCard = tempAfterImageCard;
};

const moveAfterImageCard2DragPoint = (
    $scheduleCardBelowDragPointer,
    location
) => {
    const tempAfterImageCard = afterimageCard;
    switch (location) {
        case TOP: {
            afterimageCard.remove();
            $scheduleCardBelowDragPointer.before(tempAfterImageCard);
            afterimageCard = tempAfterImageCard;
            break;
        }
        case BOTTOM: {
            afterimageCard.remove();
            $scheduleCardBelowDragPointer.after(tempAfterImageCard);
            afterimageCard = tempAfterImageCard;
            break;
        }
    }
};

const mouseMoveOnDraggingEventHandler = (event) => {
    $main.appendChild(dragCard);
    relocateDragCard(event.pageX, event.pageY);
    const $elementBelowDragPointer = getElementBelowDragPointer(event);

    if (!$elementBelowDragPointer) {
        return;
    }

    if (
        $elementBelowDragPointer.classList.contains(
            "schedule-column__cards-container"
        )
    ) {
        appendAfterImageCardInEmptyCardContainer($elementBelowDragPointer);
        return;
    }

    if (!$elementBelowDragPointer.classList.contains(CARD)) {
        return;
    }

    $scheduleCardBelowDragPointer = $elementBelowDragPointer;
    const dragDirection = getDragDirection(
        $scheduleCardBelowDragPointer,
        event.pageY
    );
    moveAfterImageCard2DragPoint($scheduleCardBelowDragPointer, dragDirection);
};

const removeMouseUpEvent = () => {
    $main.removeEventListener("mouseup", mouseUpOnDraggingEventHandler);
};

const removeDragCard = () => {
    dragCard.remove();
    dragCard = null;
};

const mouseUpOnDraggingEventHandler = () => {
    $main.removeEventListener("mousemove", mouseMoveOnDraggingEventHandler);
    removeMouseUpEvent();

    afterimageCard.classList.replace(CARD_AFTERIMAGE, CARD);
    removeDragCard();
};

const addMouseEventOnDragCard = () => {
    $main.addEventListener("mousemove", mouseMoveOnDraggingEventHandler);
    $main.addEventListener("mouseup", mouseUpOnDraggingEventHandler);
};

const isValid2Drag = (target) => {
    const isDeleteBtn = target.classList.contains("schedule-card__delete-btn");
    return selectedCard && !isDeleteBtn;
};

const relocateDragCard = (pageX, pageY) => {
    dragCard.style.left = `${pageX - dragCard.offsetWidth / 2}px`;
    dragCard.style.top = `${pageY - dragCard.offsetHeight / 2}px`;
};

export const mouseDownEventHandler = (event) => {
    const target = event.target;
    selectedCard = target.closest(".schedule-card");

    if (!isValid2Drag(target)) {
        clickCount = 0;
        return;
    }

    dragCard = selectedCard.cloneNode(true);

    selectedCard.classList.replace(CARD, CARD_AFTERIMAGE);
    dragCard.classList.replace(CARD, DRAG_CARD);
    afterimageCard = selectedCard;

    addMouseEventOnDragCard();
};
