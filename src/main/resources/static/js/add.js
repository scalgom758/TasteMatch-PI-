document.addEventListener("DOMContentLoaded", () => {
  const ingredientList = document.querySelector(".ingredient-list");

  const addParagraphBtn = document.getElementById("add-paragraph");
  const addIngredientBtn = document.getElementById("add-ingredient");

  // Función para crear un div paragraph
  function createParagraph() {
    const div = document.createElement("div");
    div.classList.add("paragraph");

    const input = document.createElement("input");
    input.type = "text";
    input.name = "paragraph-input";
    input.placeholder = "Ej: Para la salsa...";

    const removeBtn = document.createElement("button");
    removeBtn.type = "button"; // para que no haga submit
    removeBtn.innerHTML = '<i class="fa-regular fa-minus"></i>';

    // Quitar este div al pulsar el botón
    removeBtn.addEventListener("click", () => {
      div.remove();
    });

    div.appendChild(input);
    div.appendChild(removeBtn);

    return div;
  }

  // Función para crear un div ingredient
  function createIngredient() {
    const div = document.createElement("div");
    div.classList.add("ingredient");

    const input = document.createElement("input");
    input.type = "text";
    input.name = "ingredient-input";
    input.placeholder = "Ej: 100gr harina...";

    const removeBtn = document.createElement("button");
    removeBtn.type = "button";
    removeBtn.innerHTML = '<i class="fa-regular fa-minus"></i>';

    removeBtn.addEventListener("click", () => {
      div.remove();
    });

    div.appendChild(input);
    div.appendChild(removeBtn);

    return div;
  }

  // Añadir paragraph al final antes de los botones
  addParagraphBtn.addEventListener("click", () => {
    ingredientList.insertBefore(createParagraph(), addParagraphBtn);
  });

  // Añadir ingredient al final antes de los botones
  addIngredientBtn.addEventListener("click", () => {
    ingredientList.insertBefore(createIngredient(), addParagraphBtn);
  });
});

document.addEventListener("DOMContentLoaded", () => {
  const stepsList = document.querySelector(".add-steps ol");
  const addStepBtn = document.getElementById("add-step");

  // Función para crear un nuevo paso
  function createStep() {
    const li = document.createElement("li");

    // Textarea del paso
    const textarea = document.createElement("textarea");
    textarea.rows = 1;
    textarea.classList.add("step-desc");
    textarea.placeholder = "Ej: Fríe la cebolla hasta que...";

    // Botón de eliminar paso
    const removeBtn = document.createElement("button");
    removeBtn.type = "button";
    removeBtn.classList.add("remove-step");
    removeBtn.innerHTML = '<i class="fa-regular fa-minus"></i>';

    removeBtn.addEventListener("click", () => {
      li.remove();
    });

    // Contenedor de la foto
    const stepPhoto = document.createElement("div");
    stepPhoto.classList.add("step-photo");

    const label = document.createElement("label");
    label.setAttribute("for", "photo-input");

    const inputFile = document.createElement("input");
    inputFile.type = "file";
    inputFile.id = "photo-input";
    inputFile.accept = "image/*";

    const img = document.createElement("img");
    img.src = "/Images/step_photo_default.png";
    img.id = "img";

    const p = document.createElement("p");
    p.textContent = "Pon una imagen del paso";

    label.appendChild(inputFile);
    label.appendChild(img);
    label.appendChild(p);
    stepPhoto.appendChild(label);

    // Añadir todo al li
    li.appendChild(textarea);
    li.appendChild(removeBtn);
    li.appendChild(stepPhoto);

    return li;
  }

  // Insertar el nuevo paso antes del botón de añadir
  addStepBtn.addEventListener("click", () => {
    stepsList.insertBefore(createStep(), addStepBtn);
  });

  // Asociar los botones de eliminar existentes
  const existingRemoveBtns = stepsList.querySelectorAll(".remove-step");
  existingRemoveBtns.forEach(btn => {
    btn.addEventListener("click", e => {
      btn.parentElement.remove();
    });
  });
});
