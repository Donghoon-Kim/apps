import React from 'react';
import ReactDOM from 'react-dom';
import registerServiceWorker from './registerServiceWorker';
import Root from 'client/root';

ReactDOM.render(<Root/>, document.getElementById('root'));
registerServiceWorker();
