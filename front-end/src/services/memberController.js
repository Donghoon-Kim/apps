import axios from 'axios';

const domain = 'http://127.0.0.1:8080';
const contextPath = '';
const domainContextPath = domain + contextPath;

export function login(username, password) {
    return axios.post(domainContextPath + '/login', {
        username: username,
        password: password
    });
}

export function join(username, password){
    return axios.post(domainContextPath + '/members', {
        username: username,
        password: password
    });
}