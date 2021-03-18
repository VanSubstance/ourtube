import React, { Component } from 'react';
import './Styles.css';
import './Css/TrendResultPage.css';
import { ListFont, TrendChip } from './Comps';

class TrendResultPage extends Component {
    render() {
        return (
            <div
                className="trp_mainContainer">
                <div id="header">
                    <div
                        className="trp_BannerBox">
                        <a
                            className="trp_BannerA"
                            href="http://localhost:3012/">
                            <img
                                className="trp_BannerImage"
                                src="/Ex/ourtubeLogoWhite.PNG">
                            </img>
                        </a>
                    </div>
                    <div
                        className="trp_InfoBar">
                            <div
                                className="trp_CtgrChip">
                                    RPG
                            </div>
                            <div
                                className="trp_CtgrChip">
                                    AOS
                            </div>
                            <div
                                className="trp_CtgrChip">
                                    액션
                            </div>
                            <div
                                className="trp_BadgeBar">
                                    <div
                                        className="trp_BadgeChip">
                                        이 주의 조회수 1위
                                    </div>
                                    <div
                                        className="trp_BadgeChip">
                                        이 주의 신규 동영상 1위
                                    </div>
                                    <div
                                        className="trp_BadgeChip">
                                        이 주의 최고 RPG 게임
                                    </div>
                                    <div
                                        className="trp_BadgeChip">
                                        양승혁의 골짜기
                                    </div>
                            </div>
                    </div>
                </div>
                {/* //배경 및 그라데이션  */}
                <div id="scollWallpaper_">
                    <img
                        id="andy-holmes-rCbdp8VCYhQ-unspla1"
                        src="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png"
                        srcSet="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png 1x, ./Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png 2x"
                    />
                </div>
                <svg className="scollGradient" viewBox="0 0 1920 1500">
                    <linearGradient
                        id="_137_bn"
                        spreadMethod="pad"
                        x1="0.952"
                        x2="0.071"
                        y1="0.067"
                        y2="0.886"
                    >
                        <stop offset="0" stopColor="#f60" stopOpacity="1"></stop>
                        <stop offset="0.1673" stopColor="#cf8845" stopOpacity="1"></stop>
                        <stop offset="0.3389" stopColor="#b79967" stopOpacity="1"></stop>
                        <stop offset="0.4937" stopColor="#b4838b" stopOpacity="1"></stop>
                        <stop offset="0.6735" stopColor="#b16ab4" stopOpacity="1"></stop>
                        <stop offset="0.7991" stopColor="#7c84b9" stopOpacity="1"></stop>
                        <stop offset="1" stopColor="#28adc2" stopOpacity="1"></stop>
                    </linearGradient>
                    <path
                        id="Gradient"
                        d="M 0 0 L 1920 0 L 1920 1500 L 0 1500 L 0 0 Z"
                    ></path>
                </svg>
                {/* <TrendChip></TrendChip> */}
                <svg className="listTop">
                    <rect id="listTop" rx="0" ry="0" x="0" y="0" width="923" height="41">
                    </rect>
                </svg>
                <div id="cloudfont">
                    <span>텍스트 클라우드</span>
                </div>
                <svg className="listBox1">
                    <rect id="listBox" rx="0" ry="0" x="0" y="0" width="923" height="287">
                    </rect>
                </svg>
                <svg className="rightBox">
                    <rect id="rightBox" rx="0" ry="0" x="0" y="0" width="339" height="156">
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
                <div id="reSecondTopFont1">
                    <span>좋아요 싫어요 평균</span>
                </div>
                <div id="reSecondTopFont2">
                    <span>활동별 비율</span>
                </div>
                <div id="reSecondTopFont3">
                    <span>좋아요 싫어요 평균</span>
                </div>
                <svg className="reSecondBox">
                    <rect id="reSecondBox" x="0" y="0" width="300" height="206">
                    </rect>
                    <rect id="reSecondBox" x="311" y="0" width="300" height="205">
                    </rect>
                    <rect id="reSecondBox" x="622" y="0" width="300" height="206">
                    </rect>
                </svg>
                <svg className="reThirdTop">
                    <rect id="reThirdTop" x="0" y="0" width="456" height="41">
                    </rect>
                    <rect id="reThirdTop" x="467" y="0" width="456" height="41">
                    </rect>
                </svg>
                <svg className="reThirdBox">
                    <rect id="reThirdBox" x="0" y="0" width="456" height="196">
                    </rect>
                    <rect id="reThirdBox" x="467" y="0" width="456" height="196">
                    </rect>
                </svg>
            </div>
        );
    }
}

export default TrendResultPage;