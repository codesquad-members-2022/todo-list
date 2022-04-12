export const Store = {
  observers: {},

  subscribe(interest, observer) {
    if (!this.observers[interest]) {
      this.observers[interest] = [];
    }
    this.observers[interest].push(observer);
  },

  notify(interest, state) {
    this.observers[interest].forEach((observer) => observer(state));
  },

  state: {
    columnOrder: [0, 1, 2],
    0: {
      id: 0,
      title: "해야할 일",
      addBtnActivated: false,
      cardOrder: [],
      cards: {},
    },
    1: {
      id: 1,
      title: "하고 있는 일",
      addBtnActivated: false,
      cardOrder: [],
      cards: {},
    },
    2: {
      id: 2,
      title: "완료된 일",
      addBtnActivated: false,
      cardOrder: [],
      cards: {},
    },
  },

  setAddingCardState(columnID) {
    this.toggleColumnAddBtnActivation(columnID);
    this.addNewCardForm(columnID);
    this.notify("column", this.state[columnID]);
  },

  unsetAddingCardState(columnID) {
    this.toggleColumnAddBtnActivation(columnID);
    this.deleteNewCardForm(columnID);
    this.notify("column", this.state[columnID]);
  },

  toggleColumnAddBtnActivation(columnID) {
    this.state[columnID].addBtnActivated = !this.state[columnID].addBtnActivated;
  },

  addNewCardForm(columnID) {
    const newCardID = this.getNewCardID();
    this.state[columnID].cardOrder.unshift(newCardID);
    this.state[columnID].cards[newCardID] = { columnID, type: "new" };
  },

  deleteNewCardForm(columnID) {
    const newCardID = this.state[columnID].cardOrder.shift();
    delete this.state[columnID].cards[newCardID];
  },

  getNewCardID() {
    return new Date().getUTCMilliseconds();
  },

  deleteCard(columnID, cardID) {
    delete this.state[columnID].cards[cardID];
    this.state[columnID].cardOrder = this.state[columnID].cardOrder.filter((e) => e !== cardID);
    this.notify("column", this.state[columnID]);
  },

  changeCard(columnID, cardID, cardData) {
    const changedCardData = {};
    changedCardData[cardID] = cardData;
    this.state[columnID].cards = { ...this.state[columnID].cards, ...changedCardData };
    this.notify("column", this.state[columnID]);
  },

  changeCardType(columnID, cardID, type) {
    this.state[columnID].cards[cardID].type = type;
    this.notify("column", this.state[columnID]);
  },
};
