
const input = document.querySelector(".input__fieldd");
const inputIcon = document.querySelector(".input__icon");

inputIcon.addEventListener("click", (e) => {
    e.preventDefault();

    inputIcon.setAttribute(
        'src',
        input.getAttribute('type') === 'password' ?
            'img/eye.png'
            :
            'img/eye-off.png'
    );

    input.setAttribute(
        'type',
        input.getAttribute('type') === 'password' ?
            'text'
            :
            'password'
    );
});

