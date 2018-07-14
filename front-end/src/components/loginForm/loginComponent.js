import React from 'react'
import {Button, Form, Grid, Header, Segment, Message} from 'semantic-ui-react'
import CreateMemberModal from 'components/modals/createMemberModal'
import './loginComponent.css'

const LoginComponent = (props) => (
    <div className='login-form'>
        <Grid
            textAlign='center'
            style={{height: '100%'}}
            verticalAlign='middle'
        >
            <Grid.Column style={{maxWidth: 450}}>
                <Header as='h2' color='teal' textAlign='center'>
                    도서 관리 시스템
                </Header>
                <Form size='large'>
                    <Segment stacked>
                        <Form.Input
                            fluid
                            icon='user'
                            iconPosition='left'
                            placeholder='User id'
                            id='username'
                        />
                        <Form.Input
                            fluid
                            icon='lock'
                            iconPosition='left'
                            placeholder='Password'
                            type='password'
                            id='password'
                        />

                        <Button color='teal' fluid size='large' onClick={props.handleLogin}>Login</Button>
                    </Segment>
                </Form>
                <Message>
                    처음 방문하세요? <a href='' onClick={props.handleCrateMemberModalOpen}>회원가입</a><CreateMemberModal
                        isCreateMemberModalOpen={props.isCreateMemberModalOpen}
                        handleCrateMemberModalClose={props.handleCrateMemberModalClose}
                    />
                </Message>
            </Grid.Column>
        </Grid>
    </div>
)

export default LoginComponent