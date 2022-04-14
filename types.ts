import {Dto} from "./core/DTOs/dto";

export enum Action {

    ADD,
    UPDATE,
    DELETE,
    MOVE,
    SELECT,

}

//export type Action = "ADD" | "UPDATE" | "DELETE" | "MOVE" | "SELECT"

export type StoreType = {
    state: StateObj,
    mutations: { [key in Action]: Commit<key> }
    actions: { [key in Action]: Dispatch<key> }
}
export type StoreContext<T extends Action> = {
    state: StateObj,
    commit: (action: T, payload: Dto<T>) => void,
    dispatch: (action: T, payload: Dto<T>) => any,
}
export type Commit<T extends Action> = (state: StateObj, payload: Dto<T>) => void;
export type Dispatch<T extends Action> = (context: StoreContext<T>, payload: Dto<T>) => void;
export type StateObj = Record<string | symbol, any>;

type Mutations<T extends Action> = Record<T, Commit<T>>;
type Actions<T extends Action> = Record<T, Commit<T>>;