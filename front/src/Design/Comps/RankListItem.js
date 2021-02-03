import React, { Component } from 'react';

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
                    <p>리스트 {this.props.seq} </p>
            </div>
        );
    }
}

export default RankListItem;