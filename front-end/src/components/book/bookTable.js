import React from 'react';
import {Button, Header, Table, Icon} from 'semantic-ui-react';
import Book from 'components/book/book';
import Pagination from 'components/common/pagination';
import CreateBookModal from 'components/modals/createBookModal';

import { BOOK_TABLE_LABELS } from 'constants/common';

const BookTable = (props) => {
    return (
        <div className='bookTable'>
            <Header as='h3'>{props.searchApi.description}</Header>
            <Table size='small'>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell colSpan='6'>
                            <Button floated='right' color='teal' onClick={props.handleCreateBookModalOpen}>
                                <Icon name='book' />
                                추가
                            </Button>
                            <CreateBookModal
                                isCreateBookModalOpen={props.isCreateBookModalOpen}
                                handleCreateBookModalClose={props.handleCreateBookModalClose}
                                handleSaveBook={props.handleSaveBook}
                                createTargetApiIdx={props.createTargetApiIdx}
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
                        props.document.documents.length === 0 ? (
                                <Table.Row>
                                    <Table.Cell className='emptyCell' colSpan={BOOK_TABLE_LABELS.length}>검색어를 입력해주세요</Table.Cell>
                                </Table.Row>
                            ) : (
                            props.document.documents.map((book, index) => (
                                    <Book
                                        key={index}
                                        sequence={index + 1}
                                        book={book}
                                        handleViewBookModalOpen={props.handleViewBookModalOpen}
                                        searchApiIdx={props.searchApi.searchApiIdx}
                                    />
                                ))
                            )
                    }
                </Table.Body>

                <Table.Footer>
                    <Table.Row>
                        <Table.HeaderCell colSpan='6'>
                            <Pagination count={props.document.meta.pageableCount}
                                page={props.document.searchCondition.page}
                                perPage={props.document.searchCondition.size}
                                searchApiIdx={props.searchApi.searchApiIdx}
                                onPageClick={props.movingPage}
                            />
                        </Table.HeaderCell>
                    </Table.Row>
                </Table.Footer>
            </Table>
        </div>
    );
};

export default BookTable;
