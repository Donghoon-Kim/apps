import React, { Component } from 'react';
import { Container, Header } from 'semantic-ui-react';
import BookMarkTable from 'components/bookMark/bookMarkTable';

export default class Bookmark extends Component {
    constructor(props) {
        super();

        this.state = {
           bookMarkList:[]
        };

    }

    componentDidMount() {
        // this.fetchBookMarks();
    }

    fetchBookMarks = () => {
        // service.getSearchApi()
        //     .then((result) => {
        //         this.setState({searchApis: result.data});
        //         this.fetchAllBookApi();
        //     })

    };

    render() {
        return (
            <div>
                <Container style={{paddingTop: '4em'}}>
                    <div style={{width:'70%',margin:'0 auto',paddingBottom: '1em'}}>
                        <Header as='h1'>북마크</Header>
                        <p>이 페이지에서 북마크된 도서를 보실 수 있습니다.</p>
                    </div>
                    <BookMarkTable list={this.state.bookMarkList} />
                </Container>
            </div>
        )
    }
}
