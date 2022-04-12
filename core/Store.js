import { debounceFrame } from "../utils";
import { EventHandler } from "./Eventhandler";


export class Store extends EventHandler {
  #state;
  state;
  #currentObserver=null;
  targetMap = new WeakMap;
  constructor(state) {
    super();

    this.#state = this.proxy(state);
    this.state = new Proxy(state, {
      get: (target, name) => this.#state[name],
    });
  }
  //
  // addView(view) {
  //   const state = view.$props;
  //   this.subscribe(state, view);
  // }
  //
  // subscribe(state, view) {
  //   super.has(state) //super map임
  //     ? super.get(state).add(view)
  //     : super.set(state, new Set().add(view));
  // }
  // unsubscribe(state,view){
  //   if(!super.has(state)) return;
  //   super.get(state).delete(view);
  // }

  observe = (fn) => {
    this.#currentObserver = this.debounce(fn);
    fn();
    this.#currentObserver = null;
  }
  track = (target,name)=>{
    if (!this.#currentObserver) {
      return;
    }

    let depsMap = this.targetMap.get(target);
    if (!depsMap) {
      this.targetMap.set(target, (depsMap = new Map()));
    }
    let dep = depsMap.get(name);
    if (!dep) {
      depsMap.set(name, (dep = new Set()));
    }
    if(!dep.has(this.#currentObserver)){
      dep.add(this.#currentObserver);
    }
  }
  trigger=(target, name)=> {
    const depsMap = this.targetMap.get(target);
    if (!depsMap) {
      return;
    }
    const dep = depsMap.get(name);
    if (dep) {
      dep.forEach(fn=>fn());
    }
  }
  proxy(state) {
    const isProxy = Symbol("isProxy");
    const handler = {
      get: (target, name, receiver) => {
        if (name === isProxy) return true;
        const prop = Reflect.get(target,name,receiver);
        if(prop==undefined)return undefined;
        this.track(target,name);
        if (!prop.isProxy && typeof prop == "object")
          return new Proxy(prop, handler);
        return prop;
      },
      set: (target, name, value, receiver) => {
        if (target[name] == value) return;
        Reflect.set(target, name, value);
        this.trigger(target,name);
        return true;
      }
    };

    return new Proxy(state, handler);
  }

}


