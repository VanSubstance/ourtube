import React, { Component } from 'react';
import './Styles.css';
import { LeftBoxFont } from './';


class LeftBox extends Component {
    render() {
        const X = "0 0 261.987 286";
        return (
            <div>
                <div id="__Leftbox">
                    <svg class="__Leftbox">
                        <rect id="__Leftbox" rx="0" ry="0" x="0" y="0" width="640" height="936">
                        </rect>
                    </svg>
                </div>
                <div id="_boxpostion">
                    <svg class="_Mainbox" viewBox="0 0 307.779 307.779">
                        <path id="_Mainbox" d="M 0 0 L 307.779296875 0 L 307.779296875 307.779296875 L 0 307.779296875 L 0 0 Z">
                        </path>
                    </svg>
                </div>
                <div>
                    <svg class="_rankchange">
                        <path id="_rankchange" d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z">
                        </path>
                    </svg>
                    <svg class="_viewchange">
                        <path id="_viewchange" d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z">
                        </path>
                    </svg>
                    <svg class="_videochange">
                        <rect id="_videochange" rx="0" ry="0" x="0" y="0" width="27" height="5">
                        </rect>
                    </svg>
                    <svg class="_scorechange">
                        <path id="_scorechange" d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z">
                        </path>
                    </svg>
                </div>
                <LeftBoxFont></LeftBoxFont>

            </div>
        );
    }
}

export default LeftBox