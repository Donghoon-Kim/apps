export const TOKEN_KEY = 'bookapp_token';

export const KOREAN_DATE_FORMAT = 'YYYY년 M월 D일 H시 m분 s초';

export const exportOptionKey = (options, text) => {
    let filter = options.filter((item) => item.text === text);
    return filter.length === 0 ? '' : filter[0].key;
}

export const errorCallback = (error, message, history) => {
    if(error.response.status === 403){
        alert("권한문제로 로그인 페이지로 이동합니다.");
        logout();
        history.push('/login-form');
    }else{
        if(message === null){
            alert(error.response.data.message);
        }else{
            alert(message);
        }
    }
}

export const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
}

export const SEARCH_OPTIONS = [
    {key: 'all', text: '전체', value: 'all'},
    {key: 'isbn', text: 'isbn', value: 'isbn'},
    {key: 'keyword', text: '주제어', value: 'keyword'},
    {key: 'contents', text: '목차', value: 'contents'},
    {key: 'overview', text: '책소개', value: 'overview'},
    {key: 'publisher', text: '출판사', value: 'publisher'},
];

export const SORT_OPTIONS = [
    {key: '', text: '정렬없음', value: ''},
    {key: 'title', text: '제목순', value: 'title'},
    {key: 'regiDatetime', text: '등록일순(최신순)', value: 'regiDatetime'},
    {key: 'sales', text: '판매량순', value: 'sales'},
    {key: 'accuracy', text: '정확도순', value: 'accuracy'},
];

export const BOOK_TABLE_LABELS = [
    {title: '고유번호'},
    {title: '이미지'},
    {title: '도서 제목'},
    {title: '저자'},
    {title: '가격'},
    {title: '바로가기'},
];

export const BOOK_MARK_TABLE_LABELS = [
    {title: '북마크시간'},
    {title: '책제목'},
    {title: '내용'},
    {title: '가격'},
    {title: '저자'},
    {title: '출판사'},
    {title: '등록일'},
    {title: 'isbn'},
];