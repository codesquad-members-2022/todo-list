import {getUrl, request2Server} from "../utils/utils.js";

const history = [];
const URL = getUrl();

export const recordPostEvent = (columnId, columnTitle, cardData) => {
    const dataForServer = getCardDataForServer(columnId, columnTitle, cardData);
    history.push({
        event: "post",
        cardData: dataForServer,
    });
}

export const recordDeleteEvent = (cardId) => {
    history.push({
        event: "delete",
        cardId: cardId,
    });
}

const getCardDataForServer = (columnId, columnTitle, cardData) => {
    return {
        id: cardData.id,
        title: cardData.title,
        body: cardData.body,
        caption: cardData.caption,
        columnTitle: columnTitle,
        columnId: columnId,
    };
};

export const applyHistory2ServerInterval = () => {
    setInterval(() => {
        if (!history.length) return;
        const curHistory = history.shift();
        switch (curHistory.event) {
            case "post": {
                request2Server(
                    URL,
                    "POST",
                    curHistory.cardData
                );
                break;
            }
            case "delete": {
                request2Server(
                    `${URL}/${curHistory.cardId}`,
                    "DELETE"
                );
                break;
            }
        }
    }, 1000);
};

export const applyHistory2Server = async () => {
    for (const h of history) {
        switch (h.event) {
            case "post": {
                await request2Server(
                    URL,
                    "POST",
                    h.cardData
                );
                break;
            }
            case "delete": {
                await request2Server(
                    `${URL}/${h.cardId}`,
                    "DELETE"
                );
                break;
            }
        }
    }
};
