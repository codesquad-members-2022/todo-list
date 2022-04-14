import {Dto} from "./dto";
import {Action} from "../../types";

export class DeleteDto implements Dto<Action.DELETE> {
    constructor(
        public readonly payload: { listIdx: number, idx: number }) {
    }
}