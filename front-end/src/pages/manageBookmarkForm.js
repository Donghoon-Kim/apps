import React, { Component } from 'react';
import './manageBookmarkForm.css';
import { Container, Header, Divider } from 'semantic-ui-react';
import BookmarkTable from 'components/bookmark/bookmarkTable';
import {errorCallback} from "../constants/common";
import * as service from 'services/axios';

export default class Bookmark extends Component {
    constructor(props) {
        super();

        this.state = {
            bookmarks:[]
        };

    }

    componentDidMount() {
        this.fetchBookMarks();
    }

    fetchBookMarks = () => {
        service.getBookmarks()
            .then((response) => {
                this.setState({bookmarks : response.data})
            }, (error) => {
                errorCallback(error, null, this.props.history)
            })
    };

    render() {
        return (
            <div>
                <Container className='containerWrapper'>
                    <div className='headerTitle'>
                        <Header as='h1'>북마크</Header>
                        <p>이 페이지에서 현재 로그인 한 사용자의 북마크된 도서정보를 보실 수 있습니다.</p>
                    </div>
                    <Divider />
                    <BookmarkTable bookmarks={this.state.bookmarks} />
                </Container>
            </div>
        )
    }
}
