
const dropzoneBox = document.getElementsByClassName("dropzone-box")[0];

const inputFiles = document.querySelectorAll(
    ".dropzone-area input[type='file']"
);

const inputElement = inputFiles[0];

const dropZoneElement = inputElement.closest(".dropzone-area");

const inputFile2 = document.querySelector("#upload-file2");
const dropZoneElement2 = inputFile2.closest(".dropzone-area");

const inputFile3 = document.querySelector("#upload-file3");
const dropZoneElement3 = inputFile3.closest(".dropzone-area");

inputElement.addEventListener("change", (e) => {
    if (inputElement.files.length) {
        updateDropzoneFileList(dropZoneElement, inputElement.files[0]);
    }
});

inputFile2.addEventListener("change", (e) => {
    if (inputFile2.files.length) {
        updateDropzoneFileList(dropZoneElement2, inputFile2.files[0]);
    }
});

inputFile3.addEventListener("change", (e) => {
    if (inputFile3.files.length) {
        updateDropzoneFileList(dropZoneElement3, inputFile3.files[0]);
    }
});

dropZoneElement.addEventListener("dragover", (e) => {
    e.preventDefault();
    dropZoneElement.classList.add("dropzone--over");
});

dropZoneElement2.addEventListener("dragover", (e) => {
    e.preventDefault();
    dropZoneElement2.classList.add("dropzone--over");
});

dropZoneElement3.addEventListener("dragover", (e) => {
    e.preventDefault();
    dropZoneElement3.classList.add("dropzone--over");
});

["dragleave", "dragend"].forEach((type) => {
    dropZoneElement.addEventListener(type, (e) => {
        dropZoneElement.classList.remove("dropzone--over");
    });
});

["dragleave", "dragend"].forEach((type) => {
    dropZoneElement2.addEventListener(type, (e) => {
        dropZoneElement2.classList.remove("dropzone--over");
    });
});

["dragleave", "dragend"].forEach((type) => {
    dropZoneElement3.addEventListener(type, (e) => {
        dropZoneElement3.classList.remove("dropzone--over");
    });
});

dropZoneElement.addEventListener("drop", (e) => {
    e.preventDefault();

    if (e.dataTransfer.files.length) {
        inputElement.files = e.dataTransfer.files;

        updateDropzoneFileList(dropZoneElement, e.dataTransfer.files[0]);
    }

    dropZoneElement.classList.remove("dropzone--over");
});

dropZoneElement2.addEventListener("drop", (e) => {
    e.preventDefault();

    if (e.dataTransfer.files.length) {
        inputFile2.files = e.dataTransfer.files;
        updateDropzoneFileList(dropZoneElement2, e.dataTransfer.files[0]);
    }

    dropZoneElement2.classList.remove("dropzone--over");
});

dropZoneElement3.addEventListener("drop", (e) => {
    e.preventDefault();

    if (e.dataTransfer.files.length) {
        inputFile3.files = e.dataTransfer.files;
        updateDropzoneFileList(dropZoneElement3, e.dataTransfer.files[0]);
    }

    dropZoneElement3.classList.remove("dropzone--over");
});

const updateDropzoneFileList = (dropzoneElement, file) => {
    let dropzoneFileMessage = dropzoneElement.querySelector(".file-info");

    dropzoneFileMessage.innerHTML = `
        ${file.name}, ${file.size} bytes
    `;
};




dropzoneBox.addEventListener("submit", (e) => {
    e.preventDefault();
    const myFiled = document.getElementById("upload-file");
    console.log(myFiled.files[0]);

    const myFile2 = document.getElementById("upload-file2");
    console.log(myFile2.files[0]);

    const myFile3 = document.getElementById("upload-file3");
    console.log(myFile3.files[0]);
});
