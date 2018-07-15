import React from 'react';
import {Menu, Container} from 'semantic-ui-react'
import {Link} from 'react-router-dom'
import {logout} from "../../constants/common";

const AppHeader = () => {
    return (
        <Menu fixed='top' inverted>
            <Container>
                <Menu.Item as={Link} to='/' header>도서 관리 시스템</Menu.Item>
                <Menu.Item as={Link} to='/manage-book-form'>도서 관리</Menu.Item>
                <Menu.Item as={Link} to='/bookmark'>북마크</Menu.Item>
                <Menu.Item as={Link} to='/login-form' onClick={logout} position='right'>로그아웃</Menu.Item>
            </Container>
        </Menu>
    )
};



export default AppHeader;
