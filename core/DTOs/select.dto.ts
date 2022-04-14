import {Dto} from "./dto";
import {Action} from "../../types";

export class SelectDto implements Dto<Action.SELECT> {
    constructor(
        public readonly payload: { selected: boolean, idx: number, listIdx: number }) {
    }
}