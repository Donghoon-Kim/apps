import React from 'react'
import {Container, Header} from 'semantic-ui-react'

const Home = () => {
    return (
        <div>
            <Container text style={{marginTop: '7em'}}>
                <Header as='h1'>Kakao 도서 관리 페이지</Header>
                <p>Kakao 도서 관리 페이지에 오신 것을 환영합니다.</p>
                <p>이 페이지에서 여러가지 기능을 볼 수 있습니다.</p>
            </Container>
        </div>
    )
};

export default Home;