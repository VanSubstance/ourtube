import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import './Css/Comps.css';

class KeywordThumbnail extends Component {
    
    state = {
    }
    
    // 변수 적용
    setThumbnailInfo = () => {
        console.log("카테고리에 변수 전달");
    }
    render () {
        return (
            <div 
                className = "keywordThumbnail">
                    <p> 키워드 썸네일 </p>
                    <p> </p>
            </div>
        );
    }
}

export default KeywordThumbnail;