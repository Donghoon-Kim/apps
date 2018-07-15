import React from 'react';
import {Table} from 'semantic-ui-react'
import {KOREAN_DATE_FORMAT} from "../../constants/common"
import moment from 'moment'

const Bookmark = ({bookmark}) => {
    const bookmarkRegiDatetimeMomento = moment(bookmark.bookmarkRegiDatetime);
    const bookmarkRegiDatetime = bookmarkRegiDatetimeMomento.isValid() ? bookmarkRegiDatetimeMomento.format(KOREAN_DATE_FORMAT) : '';
    const regiDatetimeMomento = moment(bookmark.bookPrimaryInfo.regiDatetime);
    const regiDatetime = regiDatetimeMomento.isValid() ? regiDatetimeMomento.format(KOREAN_DATE_FORMAT) : '';
    return (
        <Table.Row>
            <Table.Cell>
                {bookmarkRegiDatetime}
            </Table.Cell>
            <Table.Cell>
                {bookmark.bookPrimaryInfo.title}
            </Table.Cell>
            <Table.Cell>
                {bookmark.bookPrimaryInfo.contents}
            </Table.Cell>
            <Table.Cell>
                {bookmark.bookPrimaryInfo.price}
            </Table.Cell>
            <Table.Cell>
                {bookmark.bookPrimaryInfo.author}
            </Table.Cell>
            <Table.Cell>
                {bookmark.bookPrimaryInfo.publisher}
            </Table.Cell>
            <Table.Cell>
                {regiDatetime}
            </Table.Cell>
            <Table.Cell>
                {bookmark.bookPrimaryInfo.isbn}
            </Table.Cell>
        </Table.Row>
    );
};

export default Bookmark;
