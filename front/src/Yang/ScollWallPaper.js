import React, { Component } from 'react';
import './Styles.css';
import Chip from '@material-ui/core/Chip';
import ListFont from './ListFont';

class ScollWallPaper extends Component {
    render() {
        const X = "0 0 261.987 286";
        return (
            <div >

                <div id="scollWallpaper_">
                    <img id="andy-holmes-rCbdp8VCYhQ-unspla1" src="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png"
                        srcset="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png 1x, ./Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png 2x" />
                </div>
                <svg class="scollGradient" viewBox="0 0 1920 1500">
                    <linearGradient id="_137_bn" spreadMethod="pad" x1="0.952" x2="0.071" y1="0.067" y2="0.886">
                        <stop offset="0" stop-color="#f60" stop-opacity="1"></stop>
                        <stop offset="0.1673" stop-color="#cf8845" stop-opacity="1"></stop>
                        <stop offset="0.3389" stop-color="#b79967" stop-opacity="1"></stop>
                        <stop offset="0.4937" stop-color="#b4838b" stop-opacity="1"></stop>
                        <stop offset="0.6735" stop-color="#b16ab4" stop-opacity="1"></stop>
                        <stop offset="0.7991" stop-color="#7c84b9" stop-opacity="1"></stop>
                        <stop offset="1" stop-color="#28adc2" stop-opacity="1"></stop>
                    </linearGradient>
                    <path id="Gradient" d="M 0 0 L 1920 0 L 1920 1500 L 0 1500 L 0 0 Z">
                    </path>
                </svg>
                <div id="scollOURTUBE">
                    <span>OURTUBE</span>
                </div>
                <svg class="listtop">
                    <rect id="listtop" rx="0" ry="0" x="0" y="0" width="923" height="41">
                    </rect>
                </svg>
                <svg class="rightbox">
                    <rect id="rightbox" rx="0" ry="0" x="0" y="0" width="339" height="311">
                    </rect>
                </svg>
                <svg class="exbox">
                    <rect id="exbox" rx="0" ry="0" x="0" y="0" width="339" height="772">
                    </rect>
                </svg>
                <svg class="listtop2">
                    <rect id="listtop2" rx="0" ry="0" x="0" y="0" width="923" height="41">
                    </rect>
                </svg>

                <svg class="listbox">
                    <rect id="listbox" rx="0" ry="0" x="0" y="0" width="923" height="270">
                    </rect>
                </svg>
               
                <ListFont >
                </ListFont>
                

            </div>
        );
    }
}

export default ScollWallPaper