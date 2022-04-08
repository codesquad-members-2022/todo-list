export const Store = {
  observers: {},

  subscribe(interest, observer) {
    if (!this.observers[interest]) {
      this.observers[interest] = [];
    }
    this.observers[interest].push(observer);
  },

  notify(interest) {
    this.observers[interest].forEach((observer) => observer());
  },

  state: {
    columnOrder: [0, 1, 2],
    0: {
      title: "해야할 일",
      cardOrder: [111],
      cards: {
        111: {
          columnID: 0,
          type: "normal",
          title: "dfdfdf",
          description: "nmnmnmn",
          author: "author by web",
        },
      },
    },
    1: {
      title: "하고 있는 일",
      cardOrder: [],
      cards: {},
    },
    2: {
      title: "완료된 일",
      cardOrder: [],
      cards: {},
    },
  },

  addNewCard(columnID) {
    const newCardID = this.getNewCardID();
    this.state[columnID].cardOrder.unshift(newCardID);
    this.state[columnID].cards[newCardID] = { columnID, type: "new" };
    this.notify("column");
  },

  getNewCardID() {
    return new Date().getUTCMilliseconds();
  },

  deleteCard(columnID, cardID) {
    delete this.state[columnID].cards[cardID];
    this.state[columnID].cardOrder = this.state[columnID].cardOrder.filter((e) => e !== cardID);
    this.notify("column");
  },

  changeCard(columnID, cardID, cardData) {
    const changedCardData = {};
    changedCardData[cardID] = cardData;
    this.state[columnID].cards = { ...this.state[columnID].cards, ...changedCardData };
    this.notify("column");
  },

  chageToEditState(columnID, cardID) {
    this.state[columnID].cards[cardID].type = "editing";
    this.notify("column");
  },
};
