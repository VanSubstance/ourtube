import React, { Component } from 'react';
import './Styles.css';
import { ListFont, TrendChip } from './Comps';

class TrendResultPage extends Component {
    render() {
        return (
            <div>
                {/* //배경 및 그라데이션  */}
                <div id="scollWallpaper_">
                    <img id="andy-holmes-rCbdp8VCYhQ-unspla1" src="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png"
                        srcset="./andy-holmes-rCbdp8VCYhQ-unspla.png 1x, ./andy-holmes-rCbdp8VCYhQ-unspla@2x.png 2x" />
                </div>
                <svg className="scollGradient" viewBox="0 0 1920 1500">
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
                <svg className="Reswipebox">
                    <rect id="Reswipebox" rx="0" ry="0" x="0" y="0" width="934" height="41">
                    </rect>
                </svg>
                <div id="swiperfont">
                    <span>이 주의 조회수 1위</span>
                    <span>이 주의 조회수 1위</span>
                    <span>이 주의 조회수 1위</span>
                </div>
                {/* <TrendChip></TrendChip> */}
                <svg className="listtop">
                    <rect id="listtop" rx="0" ry="0" x="0" y="0" width="923" height="41">
                    </rect>
                </svg>
                <div id="cloudfont">
                    <span>텍스트 클라우드</span>
                </div>
                <svg className="listbox1">
                    <rect id="listbox" rx="0" ry="0" x="0" y="0" width="923" height="287">
                    </rect>
                </svg>
                <svg className="rightbox">
                    <rect id="rightbox" rx="0" ry="0" x="0" y="0" width="339" height="156">
                    </rect>
                </svg>
                <svg className="Rerightbox2">
                    <rect id="Rerightbox2" rx="0" ry="0" x="0" y="0" width="339" height="286">
                    </rect>
                </svg>
                <svg className="Resecondtop">
                    <rect id="Resecondtop" x="0" width="300" height="41">
                    </rect>
                    <rect id="Resecondtop" x="311" width="300" height="41">
                    </rect>
                    <rect id="Resecondtop" x="622" width="300" height="41">
                    </rect>
                </svg>
                <div id="Resecondtopfont1">
                    <span>좋아요 싫어요 평균</span>
                </div>
                <div id="Resecondtopfont2">
                    <span>활동별 비율</span>
                </div>
                <div id="Resecondtopfont3">
                    <span>좋아요 싫어요 평균</span>
                </div>
                <svg className="Resecondbox">
                    <rect id="Resecondbox" x="0" y="0" width="300" height="206">
                    </rect>
                    <rect id="Resecondbox" x="311" y="0" width="300" height="205">
                    </rect>
                    <rect id="Resecondbox" x="622" y="0" width="300" height="206">
                    </rect>
                </svg>
                <svg className="Rethirdtop">
                    <rect id="_Rethirdtop" x="0" y="0" width="456" height="41">
                    </rect>
                    <rect id="Rethirdtop" x="467" y="0" width="456" height="41">
                    </rect>
                </svg>
                <svg className="Rethirdbox">
                    <rect id="Rethirdbox" x="0" y="0" width="456" height="196">
                    </rect>
                    <rect id="Rethirdbox" x="467" y="0" width="456" height="196">
                    </rect>
                </svg>
            </div>
        );
    }
}

export default TrendResultPage;