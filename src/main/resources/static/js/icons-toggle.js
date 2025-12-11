//Menú de botones
const actionButtons = document.querySelectorAll('#like, #favourite, #report, #remove');

actionButtons.forEach(button => {
    button.addEventListener('click', () => {

        const icon = button.querySelector('i');

        button.classList.toggle('active');

        icon.classList.toggle('fa-regular');
        icon.classList.toggle('fa-solid');
    });
});


//Menú de iconos
const icons = document.querySelectorAll('#heart-icon, #report-icon, #remove-icon');

icons.forEach(icon => {
    icon.addEventListener('click', () => {

        icon.classList.toggle('active');

        if (icon.classList.contains('active')) {
            icon.classList.remove('fa-regular');
            icon.classList.add('fa-solid');
        } else {
            icon.classList.remove('fa-solid');
            icon.classList.add('fa-regular');
        }

    });
});