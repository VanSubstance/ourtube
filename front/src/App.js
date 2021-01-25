import React, { Component } from 'react';
import './App.css';

import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Home, About, User } from './Yang';

class App extends Component {

  render() {
    return (
      <Router>
        <div>
          <nav>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/about">About</Link>
              </li>
              <li>
                <Link to="/user">User</Link>
              </li>
            </ul>
          </nav>

          <Route exact path='/' component={Home}/>
          <Route path='/about' component={About}/>
          <Route path='/user' component={User}/>
        </div>
      </Router>
    );
  }
}
export default App;