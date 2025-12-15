document.addEventListener("DOMContentLoaded", () => {
    // --- ELIMINAR CUENTA ---
    const deleteBtn = document.getElementById('delete-account');
    const modalRemove = document.getElementById('modal-remove');
    const closeRemove = document.getElementById('close-modal-remove');
    const confirmRemove = document.querySelector('.confirm-remove');

    // Función abrir modal
    function openModal(modal) {
        modal.style.display = 'flex';
        document.body.style.overflow = 'hidden';
    }

    // Función cerrar modal
    function closeModal(modal) {
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';
    }

    deleteBtn.addEventListener('click', (e) => {
        e.preventDefault();
        openModal(modalRemove);
    });

    closeRemove.addEventListener('click', () => {
        closeModal(modalRemove);
    });

    // Cerrar modal al hacer clic fuera
    window.addEventListener('click', e => {
        if (e.target === modalRemove) {
            closeModal(modalRemove);
        }
    });

    modalRemove.querySelector('.modal-content').addEventListener('click', e => {
        e.stopPropagation();
    });
});