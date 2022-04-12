import { Store } from "./Store";
import Node from "./Node";
import { StateObj } from "../utils";
export default class View extends Node<HTMLElement>{
  $props;
  state;
  store;
  constructor(store:Store,private target:HTMLElement, props:StateObj = {},parent : View|null =null,  ) {
    super(target, parent);
    this.store = store;
    this.state = store.state;
    this.$props = props;
    this.store.observe(()=> {
      this.setEvent();
      this.render();
    });
    this.mount();
  }
  mount(){}


  addEvent(eventType:string, selector:string, callback:(e:Event)=>void) {

    const children = [...this.el.querySelectorAll(selector)];
    const isTarget = (target:any) =>
      children.includes(target) || target.closest(selector);
    this.el.addEventListener(eventType, (e) => {
      if (!isTarget(e.target)) return false;
      callback(e);
    });
  }

  setEvent() {
  }

  select(selector:string):HTMLElement|null {
    return this.el.querySelector(selector);
  }

  selectAll(selector:string) {
    return this.el.querySelectorAll(selector);
  }

  _render(): void {
    this.el.innerHTML= this.template();
  }
}
