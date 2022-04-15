import { request2Server, getUrl, parseScheduleModel } from "../utils/utils.js";

let scheduleModel;
const URL = getUrl();

const init = async () => {
    const fetchedData = await request2Server(URL);
    scheduleModel = parseScheduleModel(fetchedData);
};

const getScheduleModel = () => {
    return scheduleModel;
};

const findScheduleColumn = (columnId) => {
    return scheduleModel.find(
        (scheduleColumnData) => scheduleColumnData.id === columnId
    );
};

const getScheduleColumnTitle = (columnId) => {
    return findScheduleColumn(columnId).title;
};

const getScheduleCards = (columnId) => {
    return findScheduleColumn(columnId).cards;
};

const getColumnTitle = (columnId) => {
    return scheduleModel.find((column) => column.id === columnId).title;
};

const getCardDataForServer = (columnId, cardData) => {
    return {
        id: cardData.id,
        title: cardData.title,
        body: cardData.body,
        caption: cardData.caption,
        columnTitle: getColumnTitle(columnId),
        columnId: columnId,
    };
};

const addScheduleCard = (columnId, cardData) => {
    const cardsInScheduleColumn = findScheduleColumn(columnId).cards;
    cardsInScheduleColumn.unshift(cardData);
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
    return cardsInScheduleColumn.length - 1;
};

export {
    init,
    getScheduleModel,
    getScheduleColumnTitle,
    getScheduleCards,
    addScheduleCard,
    removeScheduleCard,
    updateScheduleCard,
    getScheduleCardDataById,
    insertScheduleCard,
    getScheduleCardNumberInColumn,
};
