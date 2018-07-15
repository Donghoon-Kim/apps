import React, { Component } from 'react';
import LoginComponent from 'components/loginForm/loginComponent'
import * as memberController from 'services/memberController';
import {pubKeyString} from "../constants/pub.key";

export default class LoginForm extends Component {
    constructor(props) {
        super(props);
        this.state = {isCreateMemberModalOpen : false};
        this.handleClick = this.handleClick.bind(this);
        this.handleCrateMemberModalClose = this.handleCrateMemberModalClose.bind(this);
        this.handleCrateMemberModalOpen = this.handleCrateMemberModalOpen.bind(this);

    }

    componentDidMount() {
        const publicKey = pubKeyString;
        const JSEncrypt = require('node-jsencrypt');
        let encrypt = new JSEncrypt();
        this.encrypt = encrypt;
        encrypt.setPublicKey(publicKey);
    }

    handleClick(e){
        this.handleLogin();
    }

    handleLogin = () => {
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;

        if(username.length === 0){
            alert("아이디를 입력해주세요.");
            document.getElementById("username").select();
            return;
        }

        if(password.length === 0){
            alert("패스워드를 입력해주세요.");
            document.getElementById("password").select();
            return;
        }

        
        const ep = this.encrypt.encrypt(password);
        memberController.login(username, ep)
            .then((response) => {
                localStorage.setItem('bookapp_token', response.headers.authorization);
                this.props.history.push("/");
            }, function(error){
                alert('로그인 정보를 확인해주세요.');
            });
    }

    handleCrateMemberModalClose(){
        this.setState({isCreateMemberModalOpen : false});
    }

    handleCrateMemberModalOpen(e){
        e.preventDefault();
        this.setState({isCreateMemberModalOpen : true});
    }

    render(){
        return (
            <LoginComponent
                handleLogin={this.handleClick}
                isCreateMemberModalOpen={this.state.isCreateMemberModalOpen}
                handleCrateMemberModalOpen={this.handleCrateMemberModalOpen}
                handleCrateMemberModalClose={this.handleCrateMemberModalClose}
                encrypt={this.encrypt}
            />
        )
    }
}
