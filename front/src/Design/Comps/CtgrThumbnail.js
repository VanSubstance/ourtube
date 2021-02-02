import React, { Component } from 'react';

class CtgrThumbnail extends Component {
    
    state = {
        ex01: {
            width: "100px",
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
                    <p>카테고리 </p>
                    <p>{this.props.seq}</p>
            </div>
        );
    }
}

export default CtgrThumbnail;