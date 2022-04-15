const drag = (e) => {
  const { target, clientX, clientY } = e;
  const $draggableTarget = target.closest("[data-draggable]");
  if (!$draggableTarget) {
    return;
  }
  const { left, top } = $draggableTarget.getBoundingClientRect();
  const $draggingTarget = $draggableTarget.cloneNode(true);
  $draggingTarget.classList.add("dragging");
  const draggableTargetStyle = window.getComputedStyle($draggableTarget);
  Array.from(draggableTargetStyle).forEach((key) =>
    $draggingTarget.style.setProperty(key, draggableTargetStyle.getPropertyValue(key))
  );
  $draggingTarget.style.position = "absolute";
  $draggingTarget.style.top = `${top}px`;
  $draggingTarget.style.left = `${left}px`;
  $draggingTarget.style.zIndex = 1000;

  document.body.appendChild($draggingTarget);
  const offsetX = clientX - left;
  const offsetY = clientY - top;

  const moveDragging = ({ clientX, clientY }) => {
    Object.assign($draggingTarget.style, {
      top: `${clientY - offsetY}px`,
      left: `${clientX - offsetX}px`,
    });
  };

  $body.addEventListener("mousemove", moveDragging);
  $draggingTarget.addEventListener("mouseup", () => {
    $body.removeEventListener("mousemove", moveDragging);
    $body.removeChild($draggingTarget);
  });
};

const $body = document.body;
$body.addEventListener("mousedown", drag);

export default {};
