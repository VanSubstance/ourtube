import React, { Component } from 'react';
import './Styles.css';
import { WallPaperChip } from './';

class WallPaper extends Component {
    render() {
        const X = "0 0 261.987 286";
        return (
            <div>

                <div id="Wallpaper_">
                    <img id="andy-holmes-rCbdp8VCYhQ-unspla" src="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png"
                        srcset="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png 1x, ./Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png 2x" />
                </div>
                <svg class="Gradient" viewBox="0 0 1920 936">
                    <linearGradient id="_137_bn" spreadMethod="pad" x1="0.952" x2="0.071" y1="0.067" y2="0.886">
                        <stop offset="0" stop-color="#f60" stop-opacity="1"></stop>
                        <stop offset="0.1673" stop-color="#cf8845" stop-opacity="1"></stop>
                        <stop offset="0.3389" stop-color="#b79967" stop-opacity="1"></stop>
                        <stop offset="0.4937" stop-color="#b4838b" stop-opacity="1"></stop>
                        <stop offset="0.6735" stop-color="#b16ab4" stop-opacity="1"></stop>
                        <stop offset="0.7991" stop-color="#7c84b9" stop-opacity="1"></stop>
                        <stop offset="1" stop-color="#28adc2" stop-opacity="1"></stop>
                    </linearGradient>
                    <path id="Gradient" d="M 0 0 L 1920 0 L 1920 1080 L 0 1080 L 0 0 Z">
                    </path>
                </svg>
                <div>
                    <svg class="_Searchbox1">
                        <rect id="_Searchbox1" rx="0" ry="0" x="0" y="0" width="615" height="31.702">
                        </rect>
                    </svg>
                </div>
                <div
                    className="_Searchbox">
                    <input
                        className="searchInput"
                        placeholder="검색어를 입력하세요"
                        autoComplete="off"
                        ref="searchBar"
                        type="text"
                        onChange={this.searchTracker} />
                    <button className="searchButton" onClick={this.searchCtgr}>
                        <img
                            className="searchButtonImg"
                            src="/Ex/searchbutton.png"></img>
                    </button>
                </div>
                <div id="OURTUBE">
                    <span>OURTUBE</span>
                </div>
                <WallPaperChip></WallPaperChip>
            </div>


        );
    }
}

export default WallPaper