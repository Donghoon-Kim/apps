import React, { Component } from 'react';
import { Container, Header } from 'semantic-ui-react';
import SearchInput from 'components/common/searchInput';
import BookTable from 'components/book/bookTable';
import * as service from 'services/axios';
import ViewBookModal from 'components/modals/viewBookModal'
import { SEARCH_OPTIONS, SORT_OPTIONS, exportOptionKey } from 'constants/common';

export default class manageBookForm extends Component {
    constructor(props) {
        super();

        this.state = {
            searchApis: [],
            books: [],
            queryHistory: [],
            isCreateBookModalOpen: false,
            isViewBookModalOpen: false,
            createTargetApiIdx: 0,
            removeTargetApiIdx: 0,
            selectedBook: {},
        };
        this.handleKeyPress = this.handleKeyPress.bind(this);
        this.getSearchCondition = this.getSearchCondition.bind(this);
        this.handleCreateBookModalClose = this.handleCreateBookModalClose.bind(this);
        this.handleCreateBookModalOpen = this.handleCreateBookModalOpen.bind(this);
        this.handleViewBookModalClose = this.handleViewBookModalClose.bind(this);
        this.handleViewBookModalOpen = this.handleViewBookModalOpen.bind(this);
        this.handleSaveBook = this.handleSaveBook.bind(this);
    }

    handleSaveBook() {
        this.handleCreateBookModalClose();
    }

    handleViewBookModalClose() {
        const targetApiIdx = this.state.removeTargetApiIdx;
        this.setState({
            selectedBook: {},
            isViewBookModalOpen: false,
            removeTargetApiIdx: 0,
        });
        const bookResult = this.state.books
            .filter((item) => item.searchCondition.apiIdx === targetApiIdx)[0];
        const currentPage = bookResult.searchCondition.page;
        const currentSort = bookResult.searchCondition.sort;
        this.movingPage(targetApiIdx, currentPage, currentSort);
    }

    handleViewBookModalOpen(e, book, searchApiIdx) {
        e.preventDefault();
        this.setState({
            selectedBook: book,
            isViewBookModalOpen: true,
            removeTargetApiIdx: searchApiIdx,
        });
    }

    handleCreateBookModalClose() {
        this.setState({
            isCreateBookModalOpen: false,
            createTargetApiIdx: 0
        });
    }

    handleCreateBookModalOpen(e, targetApi) {
        e.preventDefault();
        this.setState({
            isCreateBookModalOpen: true,
            createTargetApiIdx: targetApi.searchApiIdx
        });
    }

    componentDidMount() {
        document.querySelectorAll(".inputWrapper .ui.compact.selection.dropdown>.text")[0].innerText = "전체";
        this.fetchSearchApis();
        this.fetchQueryHistories();
    }

    fetchSearchApis = () => {
        service.getSearchApi()
            .then((result) => {
                this.setState({searchApis: result.data});
                this.fetchAllBookApi();
            })
    }

    fetchQueryHistories = () => {
        service.getQueryhistory()
            .then((result) => {
                this.setState({queryHistory: result.data});
            });
    }

    fetchQueryHistory = (query) => {
        service.saveQueryHistory(query)
            .then((result) => {
                let newHistories = Array.from(Object.create(this.state.queryHistory));
                newHistories.push(result.data);
                this.setState({queryHistory: newHistories});
            });
    }

    getSearchCondition = () => {
        let query = document.getElementById('searchQueryInput').value;
        if (query.length === 0) {
            return null;
        }

        let targetText = document.querySelectorAll(".inputWrapper .menu.transition .selected .text")[0].innerText;
        let target = exportOptionKey(SEARCH_OPTIONS, targetText);


        return {
            query: query,
            target: target,
        }
    }

    movingPage = (apiIdx, page, sort) => {
        let currentSearchCondition = this.state.books
            .filter((item) => item.searchCondition.apiIdx === apiIdx)[0]
            .searchCondition;

        service.getBooks(currentSearchCondition.query, page, currentSearchCondition.target, sort, apiIdx)
            .then((response) => {
                let searchBookResult = response.data;
                let booksNoneSorted = this.state.books
                    .filter((item) => item.searchCondition.apiIdx !== apiIdx)
                    .concat(searchBookResult);
                let newBooks = this.state.searchApis
                    .map((searchApi) => {
                        return booksNoneSorted
                            .filter((item) => item.searchCondition.apiIdx === searchApi.searchApiIdx)[0]
                    });

                this.setState({books: newBooks});
            }, (error) => {
                alert(error.response.data.message);
            });

    }

    fetchAllBookApi = async () => {
        let searchCondition = this.getSearchCondition();
        if (searchCondition === null) {
            return;
        }

        const promises = this.state.searchApis.map((searchApi) => {
            return service.getBooks(searchCondition.query, 1, searchCondition.target, '', searchApi.searchApiIdx);
        });
        const bookApiList = await Promise.all(promises);
        this.setState({books: bookApiList.map((bookApiResult) => bookApiResult.data)});
    }

    handleKeyPress(e) {
        if (e.charCode === 13) {
            this.fetchAllBookApi();
            this.fetchQueryHistory(e.currentTarget.value);
            document.getElementById('searchQueryInput').select();
        }
    }

    render() {
        const bookTables = this.state.books.map((item, index) => {
            return <BookTable searchApi={this.state.searchApis[index]} document={item} key={index}
                movingPage={this.movingPage} isCreateBookModalOpen={this.state.isCreateBookModalOpen}
                handleCreateBookModalClose={this.handleCreateBookModalClose}
                handleCreateBookModalOpen={(e) => this.handleCreateBookModalOpen(e, this.state.searchApis[index])}
                handleViewBookModalOpen={this.handleViewBookModalOpen} handleSaveBook={this.handleSaveBook}
                createTargetApiIdx={this.state.createTargetApiIdx}/>
        });

        return (
            <div>
                <Container style={{paddingTop: '4em'}}>
                    <div style={{width:'70%',margin:'0 auto',paddingBottom: '1em'}}>
                        <Header as='h1'>도서관리</Header>
                        <p>Kakao 도서 관리 페이지에 오신 것을 환영합니다.</p>
                        <p>이 페이지에서 여러가지 기능을 볼 수 있습니다.</p>
                    </div>
                    <SearchInput onKeyPress={this.handleKeyPress} options={SEARCH_OPTIONS} queryHistories={this.state.queryHistory}/>
                    {bookTables.length > 0 && bookTables}
                </Container>

                <ViewBookModal book={this.state.selectedBook} searchApi={this.state.removeTargetApiIdx}
                    isViewBookModalOpen={this.state.isViewBookModalOpen}
                    handleViewBookModalClose={this.handleViewBookModalClose}/>
            </div>
        )
    }
}
