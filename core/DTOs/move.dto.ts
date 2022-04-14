import {Dto} from "./dto";
import {Action} from "../../types";

export class MoveDto implements Dto<Action.MOVE> {
    constructor(public readonly payload: {}) {
    }

}