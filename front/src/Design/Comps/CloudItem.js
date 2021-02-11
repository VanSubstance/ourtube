import React, { Component } from 'react';

class RankListItem extends Component {
    
    state = {
        ex01: {
            width: "400px",
            height: "60px",
            background: "#000000",
            color: "#FFFFFF",
        }
    }
    render () {
        console.log(this.props.item);
        return (
            <div 
                className = "ItemThumbnail"
                style = {this.state.ex01}>
                    <p>리스트: {this.props.item} </p>
            </div>
        );
    }
}

export default RankListItem;