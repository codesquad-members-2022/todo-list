import peact from "../core/peact";
import { useId } from "../hooks/useId";

const Button = ({ onClick, className, innerHTML }) => {
  const id = useId("button");
  return peact.createElement({
    tag: "button",
    id,
    className,
    attrs: { onClick },
    child: [innerHTML],
  });
};

export default Button;
