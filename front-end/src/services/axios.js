import axios from 'axios';

const domain = 'http://localhost:8080';
const contextPath = '';
const domainContextPath = domain + contextPath;
const headerConfig = {'authorization': localStorage.getItem('bookapp_token')};

export function getSearchApi() {
    return axios.get(domainContextPath + '/searchApis', {headers: headerConfig});
}

export function getBooks(query, page, target, searchApiIdx) {
    return axios.get(domainContextPath + '/searchApis/'+searchApiIdx+'/books', {
        params: { // query string
            query: query,
            page: page,
            target: target,
        },
        headers: headerConfig
    });
}

export function saveQueryHistory(query) {
    return axios.post(domainContextPath + '/queryHistories', {
        query: query,
    },{
        headers: headerConfig
    });
}

export function getQueryhistory() {
    return axios.get(domainContextPath + '/queryHistories', {headers: headerConfig});
}

export function saveBook(book, searchApiIdx) {
    return axios.post(domainContextPath + '/searchApis/'+searchApiIdx+'/books', book, {headers: headerConfig});
}

export function removeBook(bookIdx, searchApiIdx) {
    return axios.delete(domainContextPath + '/searchApis/'+searchApiIdx+'/books/'+bookIdx, {headers: headerConfig});
}

export function saveBookmark(bookmark){
    return axios.post(domainContextPath + '/bookmarks', bookmark, {headers: headerConfig});
}