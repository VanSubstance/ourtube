import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

class RankListItem extends Component {
    
    state = {
        ex01: {
            width: "400px",
            height: "100px",
            background: "#000000",
            color: "#FFFFFF",
        }
    }
    render () {
        return (
            <div 
                className = "CtgrThumbnail"
                style = {this.state.ex01}>
                    <p>리스트: {this.props.keyword} </p>
                    {
                    this.props.type === "트렌드"
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

export default RankListItem;