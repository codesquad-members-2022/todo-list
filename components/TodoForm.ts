import View from "../core/View";
import {Action} from "../types";
import {AddDto} from "../core/DTOs/add.dto";
import {DeleteDto} from "../core/DTOs/delete.dto";
import {SelectDto} from "../core/DTOs/select.dto";

export class TodoForm extends View {
    template() {
        const {todo: {title, content, caption}} = this.$props;
        return `<div class="form-wrapper">
            <input class="title">${title}</input><input class="content">${content}</input>
            </div>
            <div class="frame">
              <div class="button-left"><span>취소</span></div>
              <div class="button-right"><span>등록</span></div>
            </div>`
    }

    setEvent() {
        const {listIdx, idx} = this.$props;
        this.addEvent('click', '.button-right', e => {
            const title = <HTMLInputElement>this.select('.title')
            const content = <HTMLInputElement>this.select('.content');
            this.store.dispatch(Action.ADD, new AddDto({title: title.value, content: content.value, listIdx}))

        });
        this.addEvent('click', '.button-left', e => {
            if (this.state.lists[listIdx].editting) {
                this.store.commit(Action.DELETE, new DeleteDto({listIdx, idx}));
                this.store.commit(Action.SELECT, new SelectDto({idx, listIdx, selected: false}))
            } else {
                this.store.commit(Action.SELECT, new SelectDto({idx, listIdx, selected: false}));
            }
        })
    }
}