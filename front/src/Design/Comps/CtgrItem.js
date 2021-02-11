import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Bar, Line, Radar } from 'react-chartjs-2';
import './Css/Comps.css';

class CtgrItem extends Component {
    
    state = {
    }
    render () {
        return (
            <div 
                className = "ctgrThumbnail">
                    <p>{this.props.type} 카테고리 : </p>
                    <p>{this.props.keyword}</p>
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

export default CtgrItem;