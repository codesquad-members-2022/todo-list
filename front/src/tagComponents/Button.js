import { addEventAfterRender } from "../common/domUtils";
import { useId } from "../hooks/useId";

const Button = ({ onClick, className, innerHTML }) => {
  const id = useId("button");
  if (onClick) {
    addEventAfterRender({
      eventType: "click",
      selector: id,
      callback: onClick,
    });
  }
  return `<button id=${id} class=${className}>${innerHTML}</button>`;
};

export default Button;
