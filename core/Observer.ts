
import {StateObj} from "../types";
import {EventHandler} from "./Eventhandler";

export default class Observer extends WeakMap {
    protected currentObserver: (() => void) | null = null;
    private eventHandler = new EventHandler();
    observe = (fn: () => void) => {
        this.currentObserver = this.eventHandler.debounce(fn);
        fn();
        this.currentObserver = null;
    }
    track = (target: StateObj, name: string | symbol) => {
        if (!this.currentObserver) {
            return;
        }

        let depsMap = super.get(target);
        if (!depsMap) {
            super.set(target, (depsMap = new Map()));
        }
        let dep = depsMap.get(name);
        if (!dep) {
            depsMap.set(name, (dep = new Set()));
        }
        if (!dep.has(this.currentObserver)) {
            dep.add(this.currentObserver);
        }
    }
    trigger = (target: StateObj, name: string | symbol) => {
        const depsMap = super.get(target);
        if (!depsMap) {
            return;
        }
        const dep: Set<() => void> = depsMap.get(name);
        if (dep) {
            dep.forEach(fn => fn());
        }
    }

    proxy(state: StateObj) {
        const isProxy = Symbol("isProxy");

        const handler: ProxyHandler<StateObj> = {
            get: (target, name, receiver) => {

                if (name === isProxy) return true;
                const prop = Reflect.get(target, name);
                if (typeof prop == undefined) return;
                this.track(target, name);
                if (!prop.isProxy && typeof prop == "object") {
                    return new Proxy(prop, handler);
                }

                return prop;
            },
            set: (target, name, value, receiver) => {
                if (target[name] == value) return true;
                Reflect.set(target, name, value);
                this.trigger(target, name);
                return true;
            },
            apply(target: StateObj, thisArg: any, argArray: any[]): any {
            }

        };

        return new Proxy(state, handler);
    }

}


