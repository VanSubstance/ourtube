import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Bar, Line, Radar } from 'react-chartjs-2';
import './Css/Comps.css';

class KeywordItem extends Component {

    state = {
    }
    render() {
        return (
            <svg className="_138_cc_svg" viewBox="0 0 261.987 286">
                <path className="_138_cc_path" d="M 0 0 L 261.98681640625 0 L 261.98681640625 286 L 0 286 L 0 0 Z">
                    <p>{this.props.type}</p>
                    <p>키워드 이름: {this.props.keyword}</p>
                    {this.props.type === "트렌드"
                        ? (
                            <Link to={"/trend/" + this.props.keyword}>자세히 보기</Link>
                        )
                        : (
                            <Link to={"/alltime/" + this.props.keyword}>자세히 보기</Link>
                        )
                    }
                </path>
            </svg>
        );
    }
}

export default KeywordItem;