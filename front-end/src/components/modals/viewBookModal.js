import React, { Component } from 'react';
import {errorCallback} from "../../constants/common";
import { Button, Image, Header, Icon, Modal, Form , Divider, Table} from 'semantic-ui-react'
import * as service from 'services/axios';

const testBookImage = 'http://i1.daumcdn.net/img-contents/book/2010/155x225_v2.gif?moddttm=20160107070547';
const imageLoader = (imageUrl) => {
    return imageUrl == null || imageUrl.length === 0 ? testBookImage : imageUrl;
}

export default class ViewBookModal extends Component {
    constructor(props) {
        super(props);
        this.onRemoveHandler = this.onRemoveHandler.bind(this);

    }

    onBookmarkHandler(book){
        let bookmark = {
            bookPrimaryInfo : {
                title : book.title,
                contents : book.contents,
                price : book.price,
                author : book.author,
                publisher : book.publisher,
                isbn : book.isbn,
                regiDatetime : book.regiDatetime,
            }
        }

        service.saveBookmark(bookmark)
            .then((response) => {
                alert('북마크되었습니다.');
                this.props.handleViewBookModalClose();
            }, (error) => {
                errorCallback(error, null, this.props.history);
            })
    }

    onRemoveHandler(bookIdx, searchApiIdx){
        service.removeBook(bookIdx, searchApiIdx)
            .then((reponse) => {
                alert('삭제되었습니다.');
                this.props.handleViewBookModalClose();
            }, (error) => {
                errorCallback(error, null, this.props.history);
            })
    }

    render(){
        return (
            <Modal
                closeIcon size='large'
                open={this.props.isViewBookModalOpen}
                onClose={this.props.handleViewBookModalClose}>
                <Header icon='book' content='상세 정보'/>
                <Modal.Content scrolling>
                    <Modal.Description>
                        <Form>
                            <Header>기본 정보</Header>
                            <Table definition>
                                <Table.Header>
                                    <Table.Row>
                                        <Table.HeaderCell colSpan='2'>
                                            <Image src={imageLoader(this.props.book.thumbnail)} size='small' centered bordered/>
                                        </Table.HeaderCell>
                                    </Table.Row>
                                </Table.Header>
                                <Table.Body>
                                    <Table.Row>
                                        <Table.Cell>제목</Table.Cell>
                                        <Table.Cell>{this.props.book.title}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell>내용</Table.Cell>
                                        <Table.Cell>{this.props.book.contents}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell>가격</Table.Cell>
                                        <Table.Cell>{this.props.book.price}</Table.Cell>
                                    </Table.Row>
                                </Table.Body>
                            </Table>
                            <Form.Group widths='equal'>
                                <Form.Field>
                                    <label>저자</label>
                                    {this.props.book.authors}
                                </Form.Field>
                                <Form.Field>
                                    <label>출판사</label>
                                    {this.props.book.publisher}
                                </Form.Field>
                                <Form.Field>
                                    <label>isbn</label>
                                    {this.props.book.isbn}
                                </Form.Field>
                                <Form.Field>
                                    <label>등록일</label>
                                    {this.props.book.datetime}
                                </Form.Field>
                            </Form.Group>
                            <Divider horizontal><Icon color='red' name='heart' /></Divider>
                            <Header>부가정보</Header>
                            <Form.Group widths='equal'>
                                <Form.Field>
                                    <label>키워드</label>
                                    {this.props.book.keyword}
                                </Form.Field>
                                <Form.Field>
                                    <label>미리보기말</label>
                                    {this.props.book.overview}
                                </Form.Field>
                            </Form.Group>
                        </Form>
                    </Modal.Description>
                </Modal.Content>
                <Modal.Actions>
                    <Button basic color='teal' onClick={() => this.onBookmarkHandler(this.props.book)}>
                        <Icon name='bookmark'/> 북마크
                    </Button>
                    <Button basic color='red' onClick={() =>this.onRemoveHandler(this.props.book.barcode, this.props.searchApi)}>
                        <Icon name='remove'/>
                        삭제
                    </Button>
                </Modal.Actions>
            </Modal>
        )
    }
}
