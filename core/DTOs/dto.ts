import {Action} from "../../types";

export interface Dto<T extends Action> {
    readonly payload: any;
}