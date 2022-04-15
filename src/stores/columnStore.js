import { pipe, request } from "@/common/util";
import { CARD_TYPE } from "@/common/variable";

export const Store = {
  state: {},

  async setInitialState() {
    const rawColumnStates = await request.allState();
    this.state = parseRawColumnStates(rawColumnStates);
  },

  setAddingCardState(columnID) {
    toggleColumnAddBtnActivation(columnID);
    addAddingCardInClientStore(columnID);
    observer.notify("column", this.state[columnID]);
  },

  unsetAddingCardState(columnID) {
    toggleColumnAddBtnActivation(columnID);
    deleteAddingCardFromClientStore(columnID);
    observer.notify("column", this.state[columnID]);
  },

  deleteCard(columnID, cardID) {
    deleteCardFromServer(columnID, cardID);
    deleteCardFromClientStore(columnID, cardID);
    observer.notify("column", this.state[columnID]);
  },

  async changeCardState(columnID, cardID, newState) {
    const resultColumnState = await changeCardInServer(columnID, cardID, newState);
    pipe(makeColumnState, arrangeCardOrder, storeCardStates)([resultColumnState, this.state]);
    observer.notify("column", this.state[columnID]);
  },

  async addCardState(columnID, newStateForPost) {
    const resultColumnState = await request.addCard(columnID, newStateForPost);
    pipe(makeColumnState, arrangeCardOrder, storeCardStates)([resultColumnState, this.state]);
    observer.notify("column", this.state[columnID]);
  },

  changeCardType(columnID, cardID, type) {
    this.state[columnID].cards[cardID].type = type;
    observer.notify("column", this.state[columnID]);
  },

  async changeCardPosition(originalParentColumnID, cardID, newParentColumnID, movedIdx) {
    const resultColumnStates = await request.moveCard(
      originalParentColumnID,
      cardID,
      newParentColumnID,
      movedIdx
    );
    const parsedColumnStates = parseRawColumnStates(resultColumnStates);
    this.state = parsedColumnStates;
  }
};

const parseRawColumnStates = (rawColumnStates) => {
  const parsedRawColumnStates = pipe(
    arrangeColumnOrder,
    storeEachColumnState,
    arrangeEachColumnCardOrder,
    storeEachColumnCardStates
  )([rawColumnStates, {}]);
  return parsedRawColumnStates;
};

const arrangeColumnOrder = ([rawColumnStates, parsedColumnStates]) => {
  const columnOrder = rawColumnStates.map((columnState) => columnState._id);
  parsedColumnStates.columnOrder = columnOrder;
  return [rawColumnStates, parsedColumnStates];
};

const storeEachColumnState = ([rawColumnStates, parsedColumnStates]) => {
  rawColumnStates.forEach((rawColumnState) => {
    makeColumnState([rawColumnState, parsedColumnStates]);
  });
  return [rawColumnStates, parsedColumnStates];
};

const makeColumnState = ([rawColumnState, parsedColumnStates]) => {
  const columnState = {};
  columnState._id = rawColumnState._id;
  columnState.title = rawColumnState.title;
  columnState.addBtnActivated = false;
  parsedColumnStates[columnState._id] = columnState;
  return [rawColumnState, parsedColumnStates];
};

const arrangeEachColumnCardOrder = ([rawColumnStates, parsedColumnStates]) => {
  rawColumnStates.forEach((rawColumnState) => {
    arrangeCardOrder([rawColumnState, parsedColumnStates]);
  });
  return [rawColumnStates, parsedColumnStates];
};

const arrangeCardOrder = ([rawColumnState, parsedColumnStates]) => {
  const cardOrder = rawColumnState.cards.map((card) => card._id);
  const columnID = rawColumnState._id;
  parsedColumnStates[columnID].cardOrder = cardOrder;
  return [rawColumnState, parsedColumnStates];
};

const storeEachColumnCardStates = ([rawColumnStates, parsedColumnStates]) => {
  rawColumnStates.forEach((rawColumnState) => {
    storeCardStates([rawColumnState, parsedColumnStates]);
  });
  return parsedColumnStates;
};

const storeCardStates = ([rawColumnState, parsedColumnStates]) => {
  const columnID = rawColumnState._id;
  parsedColumnStates[columnID].cards = {};
  rawColumnState.cards.forEach((rawCardState) => {
    const cardID = rawCardState._id;
    parsedColumnStates[columnID].cards[cardID] = makeCardState(rawCardState);
  });
  return [rawColumnState, parsedColumnStates];
};

const makeCardState = (rawCardState) => {
  const defaultCardState = getDefaultCardState();
  return { ...rawCardState, ...defaultCardState };
};

const getDefaultCardState = () => {
  return { author: "author by web", type: CARD_TYPE.NORMAL };
};

const toggleColumnAddBtnActivation = (columnID) => {
  Store.state[columnID].addBtnActivated = !Store.state[columnID].addBtnActivated;
};

const addAddingCardInClientStore = (columnID) => {
  const tempCardID = getTempCardID();
  Store.state[columnID].cardOrder.unshift(tempCardID);
  Store.state[columnID].cards[tempCardID] = { id: tempCardID, type: CARD_TYPE.ADDING };
};

const deleteAddingCardFromClientStore = (columnID) => {
  const newCardID = Store.state[columnID].cardOrder.shift();
  delete Store.state[columnID].cards[newCardID];
};

const getTempCardID = () => {
  return new Date().getUTCMilliseconds();
};

const deleteCardFromServer = (columnID, cardID) => {
  request.deleteCard(columnID, cardID);
};

const deleteCardFromClientStore = (columnID, cardID) => {
  delete Store.state[columnID].cards[cardID];
  Store.state[columnID].cardOrder = Store.state[columnID].cardOrder.filter((e) => e != cardID);
};

const changeCardInServer = (columnID, cardID, newState) => {
  return request.changeCard(columnID, cardID, newState);
};

export const observer = {
  subscribers: {},

  subscribe(interest, subscriber) {
    if (!this.subscribers[interest]) {
      this.subscribers[interest] = [];
    }
    this.subscribers[interest].push(subscriber);
  },

  notify(interest, state) {
    this.subscribers[interest].forEach((subscriber) => subscriber(state));
  }
};
