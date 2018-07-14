import React from 'react';
import {Table, Icon, Button} from 'semantic-ui-react'

const testBookImage = 'http://i1.daumcdn.net/img-contents/book/2010/155x225_v2.gif?moddttm=20160107070547';
const imageLoader = (imageUrl) => {
    return imageUrl.length === 0 ? testBookImage : imageUrl;
}
const authorsJoiner = (authors) => {
    return authors.join(', ');
}
const Book = (props) => {
    return (
        <Table.Row>
            <Table.Cell>{props.book.barcode}</Table.Cell>
            <Table.Cell>
                <img alt='thumbnail' src={imageLoader(props.book.thumbnail)}/>
            </Table.Cell>
            <Table.Cell>
                {props.book.title}
            </Table.Cell>
            <Table.Cell>
                {authorsJoiner(props.book.authors)}</Table.Cell>
            <Table.Cell>
                {props.book.price}
                </Table.Cell>
            <Table.Cell>
                <Button animated='vertical' basic color='teal' onClick={(e)=>props.handleViewBookModalOpen(e, props.book, props.searchApiIdx)}>
                    <Button.Content hidden>자세히</Button.Content>
                    <Button.Content visible>
                        <Icon name='book' />
                    </Button.Content>
                </Button>


            </Table.Cell>
        </Table.Row>
    );
};

export default Book;