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
    return tokens[2] + tokens[1] + tokens[0] + tokens[3] + tokens[4];
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
