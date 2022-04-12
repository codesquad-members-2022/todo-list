import { delay } from "../utils";


export class EventHandler {
  #throttle: boolean = false;
  #autoCallback = -1;
  #prev = performance.now();

  debounce(fn: () => void) {
    let currentCallback = -1;
    return ( )=> {
      cancelAnimationFrame(currentCallback);
      currentCallback = requestAnimationFrame(fn);
    };
  }

  throttle(fn: () => void, time: number) {
    if (this.#throttle) return;
    this.#throttle = true;
    fn();
    delay(time).then(() => (this.#throttle = false));
  }

  start(fn: Function) {
    cancelAnimationFrame(this.#autoCallback);
    this.#autoCallback = requestAnimationFrame((time) => {
      fn(time);
    });
  }

  cancel() {
    cancelAnimationFrame(this.#autoCallback);
  }

  startAuto(fn: () => void, delay: number) {
    cancelAnimationFrame(this.#autoCallback);
    this.#autoCallback = requestAnimationFrame((time) =>
      this.autoInterrupt(time, fn, delay)
    );
  }

  autoInterrupt(time: number, fn: () => void, delay: number) {
    if (time - this.#prev >= delay) {
      this.#prev = time;
      fn();
    } else {
      cancelAnimationFrame(this.#autoCallback);
    }
    this.#autoCallback = requestAnimationFrame((time) =>
      this.autoInterrupt(time, fn, delay)
    );
  }

}
