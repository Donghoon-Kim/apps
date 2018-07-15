import axios from 'axios';

const domain = 'http://localhost:8080';
const contextPath = '';
const domainContextPath = domain + contextPath;
const headerConfigLoader = () => {
    return {'authorization': localStorage.getItem('bookapp_token')};
};

export function getSearchApi() {
    return axios.get(domainContextPath + '/searchApis', {headers: headerConfigLoader()});
}

export function getBooks(query, page, target, sort, searchApiIdx) {
    return axios.get(domainContextPath + '/searchApis/'+searchApiIdx+'/books', {
        params: { // query string
            query: query,
            page: page,
            target: target,
            sort: sort
        },
        headers: headerConfigLoader()
    });
}

export function saveQueryHistory(query) {
    return axios.post(domainContextPath + '/queryHistories', {
        query: query,
    },{
        headers: headerConfigLoader()
    });
}

export function getQueryhistory() {
    return axios.get(domainContextPath + '/queryHistories', {headers: headerConfigLoader()});
}

export function saveBook(book, searchApiIdx) {
    return axios.post(domainContextPath + '/searchApis/'+searchApiIdx+'/books', book, {headers: headerConfigLoader()});
}

export function removeBook(bookIdx, searchApiIdx) {
    return axios.delete(domainContextPath + '/searchApis/'+searchApiIdx+'/books/'+bookIdx, {headers: headerConfigLoader()});
}

export function saveBookmark(bookmark){
    return axios.post(domainContextPath + '/bookmarks', bookmark, {headers: headerConfigLoader()});
}