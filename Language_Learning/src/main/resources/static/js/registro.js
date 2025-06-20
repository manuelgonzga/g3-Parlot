document.addEventListener('DOMContentLoaded', () => {
    const dropzoneArea = document.querySelector('.dropzone-area');
    const fileInput = document.querySelector('#imagen');
    const fileInfo = document.querySelector('.file-info');
    // Selector de países
    const countrySelect = document.getElementById('paises');
    // Selector de prefijos
    const prefixSelect = document.getElementById('prefijo');
    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');
    const eyeIcon = document.querySelector('#eyeIcon');

    togglePassword.addEventListener('click', function () {
        // Cambiar el tipo de input
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        // Cambiar la imagen del icono
        eyeIcon.src = type === 'password' ? 'img/eye-off.png' : 'img/eye.png';
    });

    // Establecer España como país seleccionado por defecto
    // Asume que el valor para España en tus opciones es "ES"
    countrySelect.value = 'ES';

    // Actualizar el prefijo basado en el país seleccionado (España)
    // Asume que el prefijo de España es "+34"
    // Esto puede requerir ajustes si los valores de tus opciones son diferentes
    const options = prefixSelect.options;
    for (let i = 0; i < options.length; i++) {
        if (options[i].text.includes('+34')) {
            prefixSelect.selectedIndex = i;
            break;
        }
    }


    dropzoneArea.addEventListener('click', () => {
        fileInput.click();
    });

    fileInput.addEventListener('change', () => {
        const file = fileInput.files[0];
        showFileInfo(file);
    });

    dropzoneArea.addEventListener('dragover', (event) => {
        event.preventDefault();
        dropzoneArea.classList.add('dragover');
    });

    dropzoneArea.addEventListener('dragleave', () => {
        dropzoneArea.classList.remove('dragover');
    });

    dropzoneArea.addEventListener('drop', (event) => {
        event.preventDefault();
        dropzoneArea.classList.remove('dragover');
        const file = event.dataTransfer.files[0];
        fileInput.files = event.dataTransfer.files;
        showFileInfo(file);
    });

    // Función para actualizar la información del archivo seleccionado
    const updateFileInfo = (file) => {
        fileInfo.textContent = `Archivo seleccionado: ${file.name}`;
    };

    fileInput.addEventListener('change', (event) => {
        const file = event.target.files[0];
        if (file) {
            updateFileInfo(file);
        }
    });

    dropzoneArea.addEventListener('drop', (event) => {
        event.preventDefault();
        dropzoneArea.classList.remove('dragover');
        const file = event.dataTransfer.files[0];
        fileInput.files = event.dataTransfer.files; // Actualiza el input de archivo con el archivo arrastrado
        updateFileInfo(file); // Actualiza la información del archivo
    });
});
