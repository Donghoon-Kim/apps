import React from 'react';
import {Table} from 'semantic-ui-react';
import BookMarkItem from 'components/bookMark/bookMarkItem';

import { BOOK_MARK_TABLE_LABELS } from 'constants/common';

const BookMarkTable = (props) => {
    return (
        <div className='bookTable'>
            <Table size='small'>
                <Table.Header>
                    <Table.Row>
                        {
                            BOOK_MARK_TABLE_LABELS.map((label, idx) => (
                                <Table.HeaderCell key={idx}>{label.title}</Table.HeaderCell>
                            ))
                        }
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {
                        props.list.length === 0 ? (
                            <Table.Row>
                                <Table.Cell className='emptyCell' colSpan={BOOK_MARK_TABLE_LABELS.length}>
                                    북마크가 없습니다.
                                </Table.Cell>
                            </Table.Row>
                        ) : (
                            props.list.map((book, index) => (
                                <BookMarkItem
                                    key={index}
                                    sequence={index + 1}
                                    book={book}
                                />
                            ))
                        )
                    }
                </Table.Body>
            </Table>
        </div>
    );
};

export default BookMarkTable;
