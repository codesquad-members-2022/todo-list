export function removeText({ target }) {
  if (
    target.classList.contains("card-title") ||
    target.classList.contains("card-details")
  ) {
    target.innerText = "";
  }
}

export function isTextLengthExceeded(text) {
  const textLength = text.length;
  const maxLength = 500;
  return textLength > maxLength;
}

export function hideElement($element) {
  $element.style.display = "none";
}
