import Observer from "./Observer";
import {Action, Actions, Mutations, StateObj, StoreContext, StoreProp} from "../types";
import {load} from "../utils";


export class Store extends Observer {
    private _state: StateObj;
    public state: StateObj;
    private mutations;
    private actions;

    constructor({state, mutations, actions}: StoreProp) {
        super();
        this.mutations = mutations;
        this.actions = actions;
        this._state = this.proxy(state);
        this.state = new Proxy(state, {
            get: (target, name) => this._state[name],
        });
    }

    commit(action: Action, payload: Mutations[Action]) {
        this.mutations[action]!(this._state, payload);
    }

    dispatch(action: Action, payload: Actions[Action]): void {
        const {_state, commit, dispatch, actions} = this;
        return actions[action]!({
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
            [Action.ADD]: (state, {title, content}) => {
            },
            [Action.ONEDIT]: (state, {editting, idx}) => {
                state.lists[idx].editting = true;
            },
            [Action.DELETE]: (state, payload) => {
            },
            [Action.UPDATE]: (state, payload) => {
            },
            [Action.MOVE]: (state, payload) => {
            },
            [Action.SELECT]: (state, {selected, listIdx, idx}) => {
                const todos = state.lists[listIdx].todos
                todos[idx].selected = selected;
            }
        },
        actions: {
            [Action.SELECT]: ({state, commit, dispatch}, payload) => {
            }
        },
    })
}



