
import {delay} from "../utils";


export class EventHandler {
    private _throttle: boolean = false;
    private autoCallback = -1;
    private prev = performance.now();

    debounce(fn: () => void) {
        let currentCallback = -1;
        return () => {
            cancelAnimationFrame(currentCallback);
            currentCallback = requestAnimationFrame(fn);
        };
    }

    throttle(fn: () => void, time: number) {
        if (this._throttle) return;
        this._throttle = true;
        fn();
        delay(time).then(() => (this._throttle = false));
    }

    start(fn: Function) {
        cancelAnimationFrame(this.autoCallback);
        this.autoCallback = requestAnimationFrame((time) => {
            fn(time);
        });
    }

    cancel() {
        cancelAnimationFrame(this.autoCallback);
    }

    startAuto(fn: () => void, delay: number) {
        cancelAnimationFrame(this.autoCallback);
        this.autoCallback = requestAnimationFrame((time) =>
            this.autoInterrupt(time, fn, delay)
        );
    }

    autoInterrupt(time: number, fn: () => void, delay: number) {
        const past = time - this.prev;
        if (past >= delay) {
            this.prev = time;
            fn();
        } else {
            cancelAnimationFrame(this.autoCallback);
        }
        this.autoCallback = requestAnimationFrame((time) =>
            this.autoInterrupt(time, fn, delay)
        );
    }

}
