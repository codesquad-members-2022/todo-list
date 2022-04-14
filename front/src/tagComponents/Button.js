import peact from "../core/peact";
import { useId } from "../hooks/useId";

const Button = ({ onClick, className, innerHTML, ref, ...rest }) => {
  const id = useId("button");
  return peact.createElement({
    tag: "button",
    id,
    className,
    ref,
    attrs: { onClick, ...rest },
    child: [innerHTML],
  });
};

export default Button;
