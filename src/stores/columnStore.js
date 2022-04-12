import { pipe } from "../util/util.js";

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

  state: {},

  async setInitialState() {
    const rawColumnStates = await (await fetch("http://localhost:3000/columns")).json();
    const parsedColumnStates = {};
    this.state = pipe(
      this.arrangeColumnOrder.bind(this),
      this.storeEachColumnState.bind(this),
      this.arrangeEachColumnCardOrder.bind(this),
      this.storeEachColumnCardStates.bind(this)
    )([rawColumnStates, parsedColumnStates]);
  },

  arrangeColumnOrder([rawColumnStates, parsedColumnStates]) {
    const columnOrder = rawColumnStates.map((columnState) => columnState._id);
    parsedColumnStates.columnOrder = columnOrder;
    return [rawColumnStates, parsedColumnStates];
  },

  storeEachColumnState([rawColumnStates, parsedColumnStates]) {
    rawColumnStates.forEach((rawColumnState) => {
      this.makeColumnState(rawColumnState, parsedColumnStates);
    });
    return [rawColumnStates, parsedColumnStates];
  },

  makeColumnState(rawColumnState, parsedColumnStates) {
    const columnState = {};
    columnState._id = rawColumnState._id;
    columnState.title = rawColumnState.title;
    columnState.addBtnActivated = false;
    parsedColumnStates[columnState._id] = columnState;
  },

  arrangeEachColumnCardOrder([rawColumnStates, parsedColumnStates]) {
    rawColumnStates.forEach((rawColumnState) => {
      this.arrangeCardOrder(rawColumnState, parsedColumnStates);
    });
    return [rawColumnStates, parsedColumnStates];
  },

  arrangeCardOrder(rawColumnState, parsedColumnStates) {
    const cardOrder = rawColumnState.cards.map((card) => card._id);
    const columnID = rawColumnState._id;
    parsedColumnStates[columnID].cardOrder = cardOrder;
  },

  storeEachColumnCardStates([rawColumnStates, parsedColumnStates]) {
    rawColumnStates.forEach((rawColumnState) => {
      this.storeCardStates(rawColumnState, parsedColumnStates);
    });
    return parsedColumnStates;
  },

  storeCardStates(rawColumnState, parsedColumnStates) {
    const columnID = rawColumnState._id;
    parsedColumnStates[columnID].cards = {};
    rawColumnState.cards.forEach((rawCardState) => {
      const cardID = rawCardState._id;
      parsedColumnStates[columnID].cards[cardID] = this.makeCardState(rawCardState);
    });
  },

  makeCardState(rawCardState) {
    const defaultCardState = this.getDefaultCardState();
    return { ...rawCardState, ...defaultCardState };
  },

  getDefaultCardState() {
    return { author: "author by web", type: "normal" };
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
    this.state[columnID].cards[newCardID] = { id: newCardID, type: "adding" };
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
    this.state[columnID].cardOrder = this.state[columnID].cardOrder.filter((e) => e != cardID);
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
