import React from 'react';
import { Table } from 'semantic-ui-react'

const authorsJoiner = (authors) => {
    return authors.join(', ');
}

const BookMarkItem = ({book}) => {
    return (
        <Table.Row>
            <Table.Cell>{book.barcode}</Table.Cell>
            <Table.Cell>
                test
            </Table.Cell>
            <Table.Cell>
                {book.title}
            </Table.Cell>
            <Table.Cell>
                {authorsJoiner(book.authors)}</Table.Cell>
            <Table.Cell>
                {book.price}
            </Table.Cell>
            <Table.Cell>
                aaa
            </Table.Cell>
        </Table.Row>
    );
};

export default BookMarkItem;
