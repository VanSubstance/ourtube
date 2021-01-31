import React, { Component } from 'react';
import './App.css';

import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Home, About, User } from './Yang';
import { MainPage } from './Design';

class App extends Component {

  render() {
    return (
      <Router>
        <div>
          <nav>
            <ul>
              <li>
                <Link to="/">승혁</Link>
              </li>
              <li>
                <Link to="/about">준희</Link>
              </li>
              <li>
                <Link to="/user">민준</Link>
              </li>
              <li>
                <Link to = "/design">디자인</Link>
              </li>
            </ul>
          </nav>

          <Route exact path='/' component={Home}/>
          <Route path='/about' component={About}/>
          <Route path='/user' component={User}/>
          <Route path = '/design' component = {MainPage}/>
        </div>
      </Router>
    );
  }
}
export default App;