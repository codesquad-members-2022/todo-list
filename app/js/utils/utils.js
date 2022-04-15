const uuid = () => {
    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(
        /[xy]/g,
        function (c) {
            const r = (Math.random() * 16) | 0,
                v = c == "x" ? r : (r & 0x3) | 0x8;
            return v.toString(16);
        }
    );
};

export const getId = () => {
    const tokens = uuid().split("-");
    return tokens[2];
};

export const TEXT_LENGTH_LIMIT = 500;

export const doubleClickEventHandler = (eventHandler) => {
    let clickCount = 0;
    let timerId;

    return function ({ target }) {
        clickCount += 1;
        if (clickCount === 1) {
            timerId = setTimeout(() => {
                clickCount = 0;
            }, 300);
        } else if (clickCount === 2) {
            clearTimeout(timerId);
            clickCount = 0;

            eventHandler.call(this, target);
        }
    };
};

export const request2Server = async (url, method = "GET", cardData = {}) => {
    switch (method) {
        case "GET": {
            const response = await fetch(url);
            const responseObj = await response.json();
            return responseObj;
        }
        case "DELETE": {
            await fetch(url, { method }).catch((error) => console.error(error));
            return;
        }
        case "POST": {
            await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(cardData),
            }).catch((error) => console.error(error));
            return;
        }
    }
};

export const getUrl = () => {
    const DEV_URL = "http://localhost:3000/todos";
    const DEPLOY_URL = "https://nanbangtodo.herokuapp.com/todos";
    if (window.location.hostname !== "localhost") {
        return DEPLOY_URL;
    }
    return DEV_URL;
};

export const parseScheduleModel = (fetchedData) => {
    const scheduleModel = [];
    fetchedData.forEach((cardData) => {
        let column = scheduleModel.find(
            (columnData) => columnData.id === cardData.columnId
        );
        if (column) {
            const card = {
                title: cardData.title,
                body: cardData.body,
                caption: cardData.caption,
                id: cardData.id,
            };
            column.cards.push(card);
        } else {
            column = {
                id: cardData.columnId,
                title: cardData.columnTitle,
                cards: [
                    {
                        title: cardData.title,
                        body: cardData.body,
                        caption: cardData.caption,
                        id: cardData.id,
                    },
                ],
            };
            scheduleModel.push(column);
        }
    });
    return scheduleModel
};
