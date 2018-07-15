import React, { Component } from 'react';
import { Button, Header, Icon, Modal, Form , Input} from 'semantic-ui-react'
import * as memberController from 'services/memberController';
import {errorCallback} from "../../constants/common";


export default class CreateMemberModal extends Component {
    constructor(props) {
        super(props);
        this.onClickHandler = this.onClickHandler.bind(this);
    }

    onClickHandler(e){
        let username = document.getElementById('joinUsername').value;
        let password = document.getElementById('joinPassword').value;
        let passwordConfirm = document.getElementById('joinPasswordConfirm').value;

        if(password !== passwordConfirm){
            alert('패스워드 확인을 정확하게 입력해주세요.');
            document.getElementById('joinPasswordConfirm').select();
            return;
        }

        memberController.join(username, password)
            .then((response) => {
                alert(username + '님 회원가입이 완료되었습니다.');
                this.props.handleCrateMemberModalClose();
            }, (error) => {
                errorCallback(error, null, this.props.history);
            });
    }

    render(){
        return (
            <Modal size='mini' open={this.props.isCreateMemberModalOpen} onClose={this.props.handleCrateMemberModalClose} closeIcon>
                <Header icon='user' content='새로운 관리자 등록' />
                <Modal.Content scrolling>
                    <Modal.Description>
                        <Form>
                            <Header>회원 정보 입력</Header>
                            <Form.Field>
                                <label>회원 아이디</label>
                                <Input id='joinUsername' fluid placeholder='아이디' />
                            </Form.Field>

                            <Form.Field>
                                <label>패스워드</label>
                                <Input id='joinPassword' type='password' fluid placeholder='패스워드'/>
                            </Form.Field>

                            <Form.Field>
                                <label>패스워드 확인</label>
                                <Input id='joinPasswordConfirm' type='password' fluid placeholder='패스워드 확인'/>
                            </Form.Field>
                        </Form>
                    </Modal.Description>
                </Modal.Content>
                <Modal.Actions>
                    <Button color='teal' onClick={this.onClickHandler}>
                        <Icon name='checkmark'/> 저장
                    </Button>
                </Modal.Actions>
            </Modal>
        )
    }
}
