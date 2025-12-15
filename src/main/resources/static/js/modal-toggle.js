document.addEventListener("DOMContentLoaded", () => {
    // --- REPORT ---
    const reportBtn = document.getElementById('report');
    const reportIconInside = reportBtn.querySelector('i');
    const reportIcon = document.getElementById('report-icon');
    const modalReport = document.getElementById('modal-report');
    const closeReport = document.getElementById('close-modal-report');

    // --- REMOVE ---
    const removeBtn = document.getElementById('remove');
    const removeIconInside = removeBtn.querySelector('i');
    const removeIcon = document.getElementById('remove-icon');
    const modalRemove = document.getElementById('modal-remove');
    const closeRemove = document.getElementById('close-modal-remove');

    // Función abrir
    function openModal(modal, btn, icon) {
        modal.style.display = 'flex';
        if (btn) btn.classList.add('active');
        icon.classList.remove('fa-regular');
        icon.classList.add('fa-solid');
    }

    // Función cerrar
    function closeModal(modal, btn, icon) {
        modal.style.display = 'none';
        if (btn) btn.classList.remove('active');
        icon.classList.remove('fa-solid');
        icon.classList.add('fa-regular');
    }

    // --- ABRIR REPORT ---
    reportBtn.addEventListener('click', () => openModal(modalReport, reportBtn, reportIconInside));
    reportIcon.addEventListener('click', () => openModal(modalReport, reportIcon, reportIcon));

    // --- CERRAR REPORT ---
    closeReport.addEventListener('click', () => {
        closeModal(modalReport, reportBtn, reportIconInside);
        closeModal(modalReport, reportIcon, reportIcon);
    });

    // --- ABRIR REMOVE ---
    removeBtn.addEventListener('click', () => openModal(modalRemove, removeBtn, removeIconInside));
    removeIcon.addEventListener('click', () => openModal(modalRemove, removeIcon, removeIcon));

    // --- CERRAR REMOVE ---
    closeRemove.addEventListener('click', () => {
        closeModal(modalRemove, removeBtn, removeIconInside);
        closeModal(modalRemove, removeIcon, removeIcon);
    });

    // Cerrar clic fuera
    window.addEventListener('click', e => {
        if (e.target === modalReport) {
            closeModal(modalReport, reportBtn, reportIconInside);
            closeModal(modalReport, reportIcon, reportIcon);
        }

        if (e.target === modalRemove) {
            closeModal(modalRemove, removeBtn, removeIconInside);
            closeModal(modalRemove, removeIcon, removeIcon);
        }
    });
});
