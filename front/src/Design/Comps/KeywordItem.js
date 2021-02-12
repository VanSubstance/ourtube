import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Bar, Line, Radar } from 'react-chartjs-2';
import './Css/Comps.css';

class KeywordItem extends Component {
    
    state = {
    }
    render () {
        return (
            <div 
                className = "keywordItem">
                    <p>{this.props.type}</p>
                    <p>키워드 이름: {this.props.keyword}</p>
                    {this.props.type === "트렌드"
                    ? (
                        <Link to = {"/trend/" + this.props.keyword}>자세히 보기</Link>
                    )
                    : (
                        <Link to = {"/alltime/" + this.props.keyword}>자세히 보기</Link>
                    )
                    }
            </div>
        );
    }
}

export default KeywordItem;