const box = document.querySelector(".box");
const dragDrop = (event) => {
  const { target, pageX, pageY, clientX, clientY } = event;
  let shiftX = clientX - target.getBoundingClientRect().left;
  let shiftY = clientY - target.getBoundingClientRect().top;

  target.style.position = "absolute";

  moveAt(pageX, pageY);

  function moveAt(pageX, pageY) {
    target.style.left = pageX - shiftX + "px";
    target.style.top = pageY - shiftY + "px";
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener("mousemove", onMouseMove);

  target.onmouseup = function () {
    document.removeEventListener("mousemove", onMouseMove);
    target.onmouseup = null;
  };

  target.ondragstart = function () {
    return false;
  };
};
box.addEventListener("mousedown", dragDrop);
