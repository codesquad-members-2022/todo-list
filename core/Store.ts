

import Observer from "./Observer";
import { Action, Actions, Mutations, StateObj, StoreContext, StoreProp } from "../types";
import { load } from "../utils";


export class Store extends Observer {
  private _state:StateObj;
  public state:StateObj;
  private mutations;
  private actions;
  constructor({state, mutations,actions}:StoreProp) {
    super();
    this.mutations = mutations;
    this.actions = actions;
    this._state = this.proxy(state);
    this.state = new Proxy(state, {
      get: (target, name) => this._state[name],
    });
    console.log(this._state, this.state);
  }
  commit(action:Action,payload:Mutations[Action]){
    this.mutations[action]!(this._state, payload);
  }
  dispatch(action:Action, payload:Actions[Action]):void{
    return this.actions[action]!({state:this._state, commit:this.commit.bind(this), dispatch:this.dispatch.bind(this)}, payload);
  }
}


export async function foo() {
  const state = await load();
  return new Store({
    state,
    mutations: {
      [Action.ADD]: (state, { title, content }) => {
      },
      [Action.ONEDIT]: (state, { editting, idx }) => {
        state.lists[idx].editting = true;
      },
      [Action.DELETE]: (state, payload) => {

      },
      [Action.UPDATE]: (state, payload) => {
      },
      [Action.MOVE]: (state, payload) => {
      },
      [Action.SELECT]: (state, { selected, listIdx, idx }) => {
        const todos = state.lists[listIdx].todos
        console.log(todos[idx]);
        todos[idx].selected = selected;
      }
    },
    actions: {
      [Action.SELECT]: ({ state, commit, dispatch }, payload) => {
      }
    },
  })
}



