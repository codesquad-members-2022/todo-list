import Observer from "./Observer";
import {Action, StateObj, StoreContext, StoreType} from "../types";
import {load} from "../utils";
import {Dto} from "./DTOs/dto";
import {AddDto} from "./DTOs/add.dto";
import {DeleteDto} from "./DTOs/delete.dto";
import {SelectDto} from "./DTOs/select.dto";


export class Store extends Observer {
    private _state: StateObj;
    public state: StateObj;
    private mutations;
    private actions;

    constructor({state, mutations, actions}: StoreType) {
        super();
        this.mutations = mutations;
        this.actions = actions;
        this._state = this.proxy(state);
        this.state = new Proxy(state, {
            get: (target, name) => this._state[name],
        });
    }

    commit<T extends Action>(action: T, payload: Dto<T>) {
        this.mutations[action]!(this._state, payload);
    }

    dispatch<T extends Action>(action: T, payload: Dto<T>) {
        const {_state, commit, dispatch, actions} = this;
        actions[action]!({
            state: _state,
            commit: commit.bind(this),
            dispatch: dispatch.bind(this)
        }, payload);
    }
}


export async function loadStore() {
    const state = await load();

    return new Store({
        state,
        mutations: {
            [Action.ADD]: (state, {payload}) => {
                const {title, content, listIdx} = payload;
                const todos = state.lists[listIdx]
                todos.unshift({title, content, caption: "", selected: false});
            },
            [Action.DELETE]: (state, {payload}) => {
                const {listIdx, idx} = payload;
                const todos = state.lists[listIdx].todos
                todos.splice(idx, 1);
            },
            [Action.UPDATE]: (state, payload) => {

            },
            [Action.MOVE]: (state, payload) => {
            },
            [Action.SELECT]: (state, {payload}) => {
                const {listIdx, selected, idx} = payload;
                const todos = state.lists[listIdx].todos
                todos[idx].selected = selected ? idx : -1;
            }
        },
        actions: {
            [Action.SELECT]: ({state, commit, dispatch}, payload) => {
            },
            [Action.MOVE]: ({state, commit, dispatch}, payload) => {
            },
            [Action.ADD]: async ({state, commit, dispatch}, {payload}) => {
                const {listIdx, title, content} = payload
                commit(Action.ADD, new AddDto({listIdx, title, content}));
                await load("PUT", state);
            },
            [Action.DELETE]: async ({state, commit, dispatch}, {payload}) => {
                const {listIdx, idx} = payload;
                commit(Action.DELETE, new DeleteDto({listIdx, idx}));
                await load('POST', state);
            }
        },
    })
}



