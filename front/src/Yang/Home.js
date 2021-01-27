import React, { Component, useState } from 'react';
import axios, * as others from "axios";

import Counter from '../Practices/Counter.js';
import Trial from '../Practices/Trial.js';
import ListPrac from '../Practices/listPrac.js';

class Home extends Component {

    constructor(props) {
        super(props);
    }

    state = {
        ip: "http://222.232.15.205:8012",
        massage: "",
        setMassage: "",
        number: 0,
        trialList: ["1", "2", "3", "4"],
        datas: []
    };

    getData = async () => {
        const { datas } = this.state;
        const result = await axios.get('https://jsonplaceholder.typicode.com/users');
        console.log(result.data);
        result.data.map((item) => {
            console.log(item);
        });
        this.setState({
            datas : result.data
        });

    }

    getRandomNumber = () => {
        const { trialList } = this.state;
        var rands = trialList.map(Math.random);
        console.log(rands);
        this.setState ( {
            trialList: rands
        })
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
            <div>
                <header>
                    <Counter
                        handleIncrease={this.handleIncrease}
                        handleDecrease={this.handleDecrease}
                        number={this.state.number}
                    />
                    <Trial
                        items={this.state.datas}
                    />
                </header>
                <button onClick={this.getData}> Trial </button>
                <button onClick={this.getRandomNumber}> 난수 생성 </button>
                <ListPrac
                    items={this.state.trialList}>
                </ListPrac>
                <p>
                    Trial: {this.state.result}
                </p>
            </div>
        );
    }
}

export default Home;