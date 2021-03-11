import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Bar, Line, Radar } from 'react-chartjs-2';
import './Css/Comps.css';

const KeywordItem = (props) => {
    return (
        <div
        className="_itemBox"
        // viewBox="0 0 261.987 286"
        >
            <img
                className="keywordThumbnail"
                src={props.keyword.thumbnail}>
            </img>
            <div
                className="keywordTitleBox">
                <div
                    className="keywordTitle">
                    {props.keyword.title}
                </div>
            </div>
            {/* <path className="_itemBoxPath" d="M 0 0 L 261.98681640625 0 L 261.98681640625 286 L 0 286 L 0 0 Z">
            </path> */}
        </div>
    );
}

export default KeywordItem;


// <img
// className="backGroundImg"
// src="/Ex/backGroundIMG.PNG">
// </img>

/* <link
className="keywordTitle"
to={"/trend/" + props.keyword.title}>
{props.keyword.title}
</link> */

// <Link to={"/trend/" + props.keyword.title}>자세히 보기</Link>

/* <a
href="http://localhost:3012/"
className="bannerMain">
<img
    className="bannerImage"
    src="/Ex/ourtubeLogo.PNG">
</img>
</a> */