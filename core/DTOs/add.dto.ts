import {Dto} from "./dto";
import {Action} from "../../types";

export class AddDto implements Dto<Action.ADD> {
    constructor(
        public readonly payload: { listIdx: number, title: string, content: string }) {
    }
}