import React, { Component } from 'react';
import './Styles.css';
import {SubBox} from './';

class ItemBox extends Component {
    render() {
        const X="0 0 261.987 286";
        return (
            <div>
                <svg class="_itemBox" viewBox={X}>
                    <path id="_itemBox" d="M 0 0 L 261.98681640625 0 L 261.98681640625 286 L 0 286 L 0 0 Z">
                    </path>
                </svg>                
                <SubBox></SubBox>
                <div id="_subboxfont">
			        <span> Unknown </span>
		        </div>
            </div>
        );
    }
}

export default ItemBox;