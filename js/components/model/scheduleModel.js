import { DEFAULT_AUTHOR } from "../../utils/styleNames.js";

const findScheduleColumn = (columnId) => {
    return scheduleModel.find(
        (scheduleColumnData) => scheduleColumnData.columnId === columnId
    );
};

const getScheduleColumnTitle = (columnId) => {
    return findScheduleColumn(columnId).title;
};

const getScheduleCards = (columnId) => {
    return findScheduleColumn(columnId).cards;
};

const addScheduleCard = (columnId, cardData) => {
    const cardsInScheduleColumn = findScheduleColumn(columnId).cards;
    cardsInScheduleColumn.push(cardData);
};

const removeScheduleCard = (columnId, cardId) => {
    const cardsInScheduleColumn = findScheduleColumn(columnId).cards;
    findScheduleColumn(columnId).cards = cardsInScheduleColumn.filter(
        (card) => card.id !== cardId
    );
};

const updateScheduleCard = (columnId, cardData) => {
    const cardsInScheduleColumn = findScheduleColumn(columnId).cards;
    const index = cardsInScheduleColumn.findIndex(
        (card) => card.id === cardData.id
    );
    cardsInScheduleColumn[index] = cardData;
};

const insertScheduleCard = (columnId, cardData, index) => {
    const cardsInScheduleColumn = findScheduleColumn(columnId).cards;
    cardsInScheduleColumn.splice(index, 0, cardData);
};

const getScheduleCardDataById = (columnId, cardId) => {
    const cardsInScheduleColumn = findScheduleColumn(columnId).cards;
    const cardData = cardsInScheduleColumn.find((card) => card.id === cardId);

    return cardData;
};

const getScheduleCardNumberInColumn = (columnId) => {
    const cardsInScheduleColumn = findScheduleColumn(columnId).cards;
    return cardsInScheduleColumn.length;
};

const scheduleModel = [
    {
        columnId: "0",
        title: "해야할 일",
        cards: [
            {
                title: "제목",
                body: "내용",
                caption: DEFAULT_AUTHOR,
                id: "0",
            },
            {
                title: "제목2",
                body: "내용2",
                caption: DEFAULT_AUTHOR,
                id: "1",
            },
        ],
    },
    {
        columnId: "1",
        title: "하고 있는 일",
        cards: [
            {
                title: "제목",
                body: "내용",
                caption: DEFAULT_AUTHOR,
                id: "3",
            },
        ],
    },
];

export {
    scheduleModel,
    getScheduleColumnTitle,
    getScheduleCards,
    addScheduleCard,
    removeScheduleCard,
    updateScheduleCard,
    getScheduleCardDataById,
    insertScheduleCard,
    getScheduleCardNumberInColumn,
};
