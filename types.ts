export enum Action {

    ADD,
    UPDATE,
    DELETE,
    MOVE,
    SELECT,
    ONEDIT,
}

export type StoreProp = {
    state: StateObj
    mutations: Partial<Record<Action, Commit>>
    actions: Partial<Record<Action, Dispatch>>
}
export type StoreContext = {
    state: StateObj,
    commit: (action: Action, payload: Mutations[Action]) => void,
    dispatch: (action: Action, payload: Actions[Action]) => any,
}
export type Commit = (state: StateObj, payload: Mutations[Action]) => void;
export type Dispatch = (context: StoreContext, payload: Actions[Action]) => void;
export type StateObj = Record<string | symbol, any>;

export type Mutations = {
    [Action.ADD]: any,
    [Action.DELETE]: any,
    [Action.MOVE]: any,
    [Action.SELECT]: { selected: boolean, idx: number, listIdx: number },
    [Action.UPDATE]: any,
    [Action.ONEDIT]: { editting: boolean, listIdx: number },
}
export type Actions = {
    [Action.ADD]: any,
    [Action.DELETE]: any,
    [Action.MOVE]: any,
    [Action.SELECT]: { selected: boolean, idx: number },
    [Action.UPDATE]: any,
    [Action.ONEDIT]: { editting: boolean, idx: number },
}