import React from 'react';
import {Route, Switch, Redirect} from 'react-router-dom';
import AppHeader from 'components/appHeader/appHeader';
import {Home, LoginForm, ManageBookForm, Bookmark} from 'pages';
import {TOKEN_KEY} from "../constants/common";

const App = () => {
    return (
        <div>
            <Switch>
                <Route path="/login-form" component={LoginForm}/>
                <Route path="/" component={TopHeaderApp}/>
            </Switch>
        </div>
    )
};

const TopHeaderApp = () => {
    return (
        <div>
            {localStorage.getItem(TOKEN_KEY) == null && <Redirect to="/login-form"/>}
            <AppHeader/>
            <Route exact path="/" component={Home}/>
            <Route path="/manage-book-form" component={ManageBookForm}/>
            <Route path="/bookmark" component={Bookmark} />
        </div>
    )
}

export default App;
