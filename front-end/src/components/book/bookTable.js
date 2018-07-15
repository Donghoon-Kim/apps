import React, {Component} from 'react';
import './bookTable.css';
import {Button, Header, Table, Icon, Menu, Dropdown} from 'semantic-ui-react';
import Book from 'components/book/book';
import Pagination from 'components/common/pagination';
import CreateBookModal from 'components/modals/createBookModal';

import { BOOK_TABLE_LABELS, SORT_OPTIONS } from 'constants/common';

export default class BookTable extends Component {
    constructor(props) {
        super(props);
        this.onSortHandler = this.onSortHandler.bind(this);
    }

    onSortHandler(e, {value}){
        this.props.movingPage(
            this.props.searchApi.searchApiIdx,
            this.props.document.searchCondition.page,
            value
        );
    }
    
    render(){
        return (
            <div className='bookTable'>
                <Header as='h3'>{this.props.searchApi.description}</Header>
                <Table size='small' striped>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell colSpan='6'>
                                <Menu compact className='left floated'>
                                    <Dropdown
                                        placeholder='정렬없음'
                                        options={SORT_OPTIONS}
                                        onChange={this.onSortHandler}
                                        simple item selection closeOnChange/>
                                </Menu>

                                <Button floated='right' color='teal' onClick={this.props.handleCreateBookModalOpen}>
                                    <Icon name='book' />
                                    추가
                                </Button>
                                <CreateBookModal
                                    isCreateBookModalOpen={this.props.isCreateBookModalOpen}
                                    handleCreateBookModalClose={this.props.handleCreateBookModalClose}
                                    handleSaveBook={this.props.handleSaveBook}
                                    createTargetApiIdx={this.props.createTargetApiIdx}
                                />
                            </Table.HeaderCell>
                        </Table.Row>
                        <Table.Row>
                            {
                                BOOK_TABLE_LABELS.map((label, idx) => (
                                    <Table.HeaderCell key={idx}>{label.title}</Table.HeaderCell>
                                ))
                            }
                        </Table.Row>
                    </Table.Header>

                    <Table.Body>
                        {
                            this.props.document.documents.length === 0 ? (
                                <Table.Row>
                                    <Table.Cell className='emptyCell' colSpan={BOOK_TABLE_LABELS.length}>검색어를 입력해주세요</Table.Cell>
                                </Table.Row>
                            ) : (
                                this.props.document.documents.map((book, index) => (
                                    <Book
                                        key={index}
                                        sequence={index + 1}
                                        book={book}
                                        handleViewBookModalOpen={this.props.handleViewBookModalOpen}
                                        searchApiIdx={this.props.searchApi.searchApiIdx}
                                    />
                                ))
                            )
                        }
                    </Table.Body>

                    <Table.Footer>
                        <Table.Row>
                            <Table.HeaderCell colSpan='6'>
                                <Pagination count={this.props.document.meta.pageableCount}
                                    page={this.props.document.searchCondition.page}
                                    perPage={this.props.document.searchCondition.size}
                                    searchApiIdx={this.props.searchApi.searchApiIdx}
                                    onPageClick={this.props.movingPage}
                                />
                            </Table.HeaderCell>
                        </Table.Row>
                    </Table.Footer>
                </Table>
            </div>
        );
    }
}