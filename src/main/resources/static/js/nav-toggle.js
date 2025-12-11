const iconMenu = document.getElementById("icon-menu");
const nav = document.getElementById("nav");
const container = document.getElementById("container");

iconMenu.addEventListener("click", () => {
  nav.classList.toggle("minimized");
  container.classList.toggle("minimized");
});