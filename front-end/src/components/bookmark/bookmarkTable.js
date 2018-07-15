import React from 'react';
import {Table} from 'semantic-ui-react';
import Bookmark from 'components/bookmark/bookmark';
import './bookmarkTable.css';

import { BOOK_MARK_TABLE_LABELS } from 'constants/common';

const BookmarkTable = (props) => {
    return (
        <div className='bookmarkTable'>
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
                        props.bookmarks.length === 0 ? (
                            <Table.Row>
                                <Table.Cell className='emptyCell' colSpan={BOOK_MARK_TABLE_LABELS.length}>
                                    북마크가 없습니다.
                                </Table.Cell>
                            </Table.Row>
                        ) : (
                            props.bookmarks.map((bookmark, index) => (
                                <Bookmark
                                    key={index}
                                    sequence={index + 1}
                                    bookmark={bookmark}
                                />
                            ))
                        )
                    }
                </Table.Body>
            </Table>
        </div>
    );
};

export default BookmarkTable;
