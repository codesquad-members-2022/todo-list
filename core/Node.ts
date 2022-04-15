
import {EventHandler} from "./Eventhandler";


export default abstract class Node<T> extends EventHandler {
    private head: Node<T> | null = null;
    private next: Node<T> | null = null;

    constructor(protected el: T, private parent: Node<T> | null = null) {
        super();
        if (parent) parent.setChild(this);
    }

    setChild(node: Node<T>) {
        node.parent = this;
        if (!this.head) this.head = node;
        else this.head.setNext(node);
    }

    setNext(v: Node<T>): void {
        let curr: Node<T> = this;
        if (!curr.next) {
            curr.next = v;
        } else {
            while (curr.next) {
                curr = curr.next;
            }
            curr.next = v;
        }
    }

    render() {
        this._render();
        // if (this.next) this.next.render();
        // if (this.head) this.head.render();
    }

    abstract _render(): void;

    template() {
        return ``;
    }

    abstract select(selector: string): T ;

    abstract selectAll(selector: string): T[] ;

}
