export const Store = {
  observers: {},

  subscribe(interest, observer) {
    if (!this.observers[interest]) {
      this.observers[interest] = [];
    }
    this.observers[interest].push(observer);
  },

  notify(interest, param) {
    this.observers[interest].forEach((observer) => observer(param));
  },

  state: {
    columnOrder: [0, 1],
    0: {
      title: "해야할 일",
      cardOrder: [222, 333],
      cards: {
        222: {
          columnID: 0,
          type: "editing",
          title: "now it's editing",
          description: "qqqqqq",
          author: "author by web",
        },
        333: {
          columnID: 0,
          type: "normal",
          title: "dfdfdf",
          description: "nmnmnmn",
          author: "author by web",
        },
      },
    },
    1: {
      title: "하는 중",
      cardOrder: [111, 333],
      cards: {
        111: {
          columnID: 0,
          type: "editing",
          title: "now it's editing",
          description: "qqqqqq",
          author: "author by web",
        },
        333: {
          columnID: 0,
          type: "normal",
          title: "dfdfdf",
          description: "nmnmnmn",
          author: "author by web",
        },
      },
    },
  },

  setState(type, state) {
    if (type === "card") {
      this.setCardState(state);
    }
  },

  setCardState(cardState) {
    const columnID = Object.values(cardState);
    this.state[columnID].cards = { ...this.state[columnID].cards, ...cardState };
    this.notify("column", columnID);
  },

  addNewCardState(columnID) {
    const newCardID = this.getNewCardID();
    this.state[columnID].cardOrder.unshift(newCardID);
    this.state[columnID].cards[newCardID] = { columnID, type: "new" };
    this.notify("column", columnID);
    console.log(this);
  },

  getNewCardID() {
    return new Date().getUTCMilliseconds();
  },
};
