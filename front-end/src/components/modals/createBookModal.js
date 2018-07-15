import React, { Component } from 'react';
import {errorCallback} from "../../constants/common";
import { Button, Header, Icon, Modal, Form , Input, Divider} from 'semantic-ui-react'
import * as service from 'services/axios';

export default class CreateBookModal extends Component {
    constructor(props) {
        super(props);
        this.onSaveHandler = this.onSaveHandler.bind(this);
        this.inputTitle = React.createRef();
        this.inputContents = React.createRef();
        this.inputPrice = React.createRef();
        this.inputAuthor = React.createRef();
        this.inputPublisher = React.createRef();
        this.inputIsbn = React.createRef();
        this.inputKeyword = React.createRef();
        this.inputOverview = React.createRef();
    }

    onSaveHandler(callback, searchApiIdx){
        let title = this.inputTitle.current.inputRef.value;
        let contents = this.inputContents.current.inputRef.value;
        let price = this.inputPrice.current.inputRef.value;
        let author = this.inputAuthor.current.inputRef.value;
        let publisher = this.inputPublisher.current.inputRef.value;
        let isbn = this.inputIsbn.current.inputRef.value;
        let keyword = this.inputKeyword.current.inputRef.value;
        let overview = this.inputOverview.current.inputRef.value;

        let book = {
            bookPrimaryInfo : {
                title : title,
                contents : contents,
                price : price,
                author : author,
                publisher : publisher,
                isbn : isbn,
            },
            keyword : keyword,
            overview : overview,
        }

        service.saveBook(book, searchApiIdx)
            .then((response) =>{
                alert("저장되었습니다.");
                callback();
            }, (error) => {
                errorCallback(error, null, this.props.history);
            });
    }

    render(){
        return (
            <Modal closeIcon size='large'
                open={this.props.isCreateBookModalOpen}
                onClose={this.props.handleCreateBookModalClose}>
                <Header icon='book' content='새로운 도서 등록'/>
                <Modal.Content scrolling>
                    <Modal.Description>
                        <Form>
                            <Header>기본 정보</Header>
                            <Form.Group widths='equal'>
                                <Form.Field>
                                    <label>제목</label>
                                    <Input fluid placeholder='제목' ref={this.inputTitle} />
                                </Form.Field>
                                <Form.Field>
                                    <label>내용</label>
                                    <Input fluid placeholder='내용' ref={this.inputContents}/>
                                </Form.Field>
                                <Form.Field>
                                    <label>가격</label>
                                    <Input fluid placeholder='가격' ref={this.inputPrice}/>
                                </Form.Field>
                            </Form.Group>
                            <Form.Group widths='equal'>
                                <Form.Field>
                                    <label>저자</label>
                                    <Input fluid placeholder='저자' ref={this.inputAuthor}/>
                                </Form.Field>
                                <Form.Field>
                                    <label>출판사</label>
                                    <Input fluid placeholder='출판사' ref={this.inputPublisher}/>
                                </Form.Field>
                                <Form.Field>
                                    <label>isbn</label>
                                    <Input fluid placeholder='isbn' ref={this.inputIsbn}/>
                                </Form.Field>
                            </Form.Group>
                            <Divider horizontal><Icon color='red' name='heart' /></Divider>
                            <Header>선택정보</Header>
                            <Form.Group widths='equal'>
                                <Form.Field>
                                    <label>키워드</label>
                                    <Input fluid placeholder='키워드' ref={this.inputKeyword}/>
                                </Form.Field>
                                <Form.Field>
                                    <label>미리보기말</label>
                                    <Input fluid placeholder='미리보기말' ref={this.inputOverview}/>
                                </Form.Field>
                            </Form.Group>
                        </Form>
                    </Modal.Description>
                </Modal.Content>
                <Modal.Actions>
                    <Button color='teal' onClick={() => this.onSaveHandler(this.props.handleSaveBook, this.props.createTargetApiIdx)}>
                        <Icon name='checkmark'/> 저장
                    </Button>
                </Modal.Actions>
            </Modal>
        )
    }
}
