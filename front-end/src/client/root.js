import React from 'react';
import {BrowserRouter as Router} from 'react-router-dom';
import App from 'shared/app';

const Root = () => {
    return (
        <Router>
            <App/>
        </Router>
    )
};

export default Root;