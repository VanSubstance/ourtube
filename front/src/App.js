import logo from './logo.svg';
import React, { Component } from 'react';
import './App.css';
import Counter from './Counter.js'// 카운터를 표시해줄 컴포넌트 호출
import Trial from './Trial.js'
class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      number: 0,
      trialList: []
    };
  }

  handleIncrease = () => {
    const { number, trialList } = this.state;
    this.setState({
      number: number + 1
    });
  }

  handleDecrease = () => {
    const { number, trialList } = this.state;
    this.setState({
      number: number - 1
    });
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img
          src={logo} className="App-logo" alt="logo" />
          <Counter
            handleIncrease={this.handleIncrease}
            handleDecrease={this.handleDecrease}
            number={this.state.number}
          />
          <Trial
            trialList={this.state.trialList}
          />
        </header>
      </div>
    );
  }
}
export default App;