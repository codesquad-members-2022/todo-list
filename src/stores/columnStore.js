const Store = {
  observers: new Set(),

  state: {
    colmnOrder: [0, 1],
    0: {
      title: "해야할 일",
      cardOrder: [111, 333, 222],
      cards: {
        111: {
          columnID: 0,
          type: "new",
          title: "",
          description: "",
        },
        222: {
          columnID: 0,
          type: "editing",
          title: "now it's editing",
          description: "qqqqqq",
        },
        333: {
          columnID: 0,
          type: "normal",
          title: "dfdfdf",
          description: "nmnmnmn",
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
        },
        555: {
          columnID: 1,
          type: "editing",
          title: "now it's editing",
          description: "qqqqqq",
        },
        666: {
          columnID: 1,
          type: "normal",
          title: "dfdfdf",
          description: "nmnmnmn",
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
    this.observers.forEach((observer) => observer());
  },
};
