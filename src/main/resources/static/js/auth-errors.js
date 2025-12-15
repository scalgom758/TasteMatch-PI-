function showError(id) {
    const error = document.getElementById(id);

    if (!error) return;

    error.hidden = false;

    setTimeout(() => {
        error.hidden = true;
    }, 3000);
}
