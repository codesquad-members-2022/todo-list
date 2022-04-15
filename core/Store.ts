
import Observer from "./Observer";
import {Action, Mutation, StateObj, StoreContext, StoreType} from "../types";
import {load} from "../utils";
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

    commit<T extends Action>(action: T, payload: Mutation[T]) {
        this.mutations[action]!(this._state, payload);
    }

    dispatch<T extends Action>(action: T, payload: Mutation[T]) {
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

            [Action.ADD]: (state, {title, content, listIdx}) => {
                const list = state.lists[listIdx]

                const newTodo = {title, content, caption: ""};
                const todos = list.todos
                for (let i = 0; i < todos.length; i++) {
                    todos[i + 1] = todos[i];
                }
                todos[0] = newTodo;

            },
            [Action.DELETE]: (state, {listIdx, idx}) => {

                const todos = state.lists[listIdx].todos
                console.log(todos);
                todos.splice(idx, 1);
            },
            [Action.UPDATE]: (state, payload) => {

            },
            [Action.MOVE]: (state, payload) => {
            },
            [Action.SELECT]: (state, {listIdx, selected, idx}) => {
                const list = state.lists[listIdx];
                list.selectedIndex = selected ? idx : -1;
            }
        },
        actions: {
            [Action.SELECT]: ({state, commit, dispatch}, payload) => {
            },
            [Action.MOVE]: ({state, commit, dispatch}, payload) => {
            },
            [Action.ADD]: async ({state, commit, dispatch}, {listIdx, title, content}) => {
                commit(Action.ADD, new AddDto(listIdx, title, content));
                await load("PUT", state);
            },
            [Action.DELETE]: async ({state, commit, dispatch}, {listIdx, idx}) => {
                commit(Action.DELETE, new DeleteDto(listIdx, idx));
                await load('POST', state);
            },
            [Action.UPDATE]: async ({state, commit, dispatch}, payload) => {
            }
        },
    })

}



