import { Store } from "./Store";
import Node from "./Node";
import { StateObj } from "../types";
export default class View extends Node<HTMLElement>{
  protected $props;
  protected state;
  protected store;
  constructor(store:Store,private target:HTMLElement, props:StateObj = {},parent : View|null =null,  ) {
    super(target, parent);
    this.store = store;
    console.log(store);
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

  select(selector:string|undefined=undefined):HTMLElement|null {
    return selector? this.el.querySelector(selector):this.el
  }

  selectAll(selector:string|undefined=undefined) {
    return selector? this.el.querySelectorAll(selector):this.el;
  }

  _render(): void {
    this.el.innerHTML= this.template();
  }
}
