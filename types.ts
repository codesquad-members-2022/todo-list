import {AddDto} from "./core/DTOs/add.dto";
import {SelectDto} from "./core/DTOs/select.dto";
import {MoveDto} from "./core/DTOs/move.dto";
import {UpdateDto} from "./core/DTOs/update.dto";
import {DeleteDto} from "./core/DTOs/delete.dto";

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
    commit: (action: T, payload: Mutation[T]) => void,
    dispatch: (action: T, payload: Mutation[T]) => any,
}
export type Commit<T extends Action> = (state: StateObj, payload: Mutation[T]) => void;
export type Dispatch<T extends Action> = (context: StoreContext<T>, payload: Mutation[T]) => void;
export type StateObj = Record<string | symbol, any>;


type Actions<T extends Action> = Record<T, Commit<T>>;
export type Mutation = {
    [Action.ADD]: AddDto,
    [Action.SELECT]: SelectDto,
    [Action.MOVE]: MoveDto,
    [Action.UPDATE]: UpdateDto,
    [Action.DELETE]: DeleteDto
}