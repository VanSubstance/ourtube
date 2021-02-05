import React, { Component } from 'react';

class CtgrThumbnail extends Component {
    
    state = {
        ex01: {
            width: "200px",
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
                    <p>키워드 </p>
                    <p>{this.props.seq}</p>
                    <button>자세히 보기</button>
            </div>
        );
    }
}

export default CtgrThumbnail;