function createStore(initState) {
  let state = initState;

  return {
    getState() {
      return state;
      /* state 정보 가져옴*/
    },
    setState(newState) {
      // history.push(newState);
      state = [...state, newState];
    },
    removeState() {
      /* staet 정보 삭제*/
    },
  };
}

export { createStore };
