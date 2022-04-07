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
    columnOrder: [0, 1],
    0: {
      title: "해야할 일",
      cardOrder: [111, 333, 222],
      cards: {
        111: {
          columnID: 0,
          type: "new",
          title: "",
          description: "",
          author: "",
        },
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
      title: "하고 있는 일",
      cardOrder: [444, 666, 555],
      cards: {
        444: {
          columnID: 1,
          type: "new",
          title: "",
          description: "",
          author: "",
        },
        555: {
          columnID: 1,
          type: "editing",
          title: "now it's editing",
          description: "qqqqqq",
          author: "author by web",
        },
        666: {
          columnID: 1,
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
      setCardState(state);
    }
  },

  setCardState(cardState) {
    const columnID = cardState.columnID;
    this.state[columnID].cards = { ...this.state[columnID].cards, ...cardState };
    this.notify("card");
  },
};
