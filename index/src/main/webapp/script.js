const wrapper = document.querySelector('.wrapper');
const registerLink = document.querySelector('.registerLink');
const loginLink = document.querySelector('.login-link');
const Login = document.querySelector('.Login-popo');
const iconClose = document.querySelector('.icon-close');
registerLink.onclick = () => {
    wrapper.classList.add('active');
};

loginLink.onclick = () => {
    wrapper.classList.remove('active');
};

Login.onclick = () => {
    wrapper.classList.add('active-popo');
};

iconClose.onclick = () => {
    wrapper.classList.remove('active-popo');
    wrapper.classList.remove('active');
};
