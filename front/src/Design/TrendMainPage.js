import React, { Component } from 'react';
import './Styles.css';
import { ListFont, TrendChip } from './Comps';

class TrendMainPage extends Component {
    render() {
        return (
            <div >
                {/* //배경 및 그라데이션  */}
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
                <div>
                    <svg class="_scrollSearchbox1">
                        <rect id="_Searchbox1" rx="0" ry="0" x="0" y="0" width="615" height="31.702">
                        </rect>
                    </svg>
                </div>
                <div
                    className="_scrollSearchbox">
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
                <TrendChip></TrendChip>
                <svg class="listtop">
                    <rect id="listtop" rx="0" ry="0" x="0" y="0" width="923" height="41">
                    </rect>
                </svg>
                <svg class="listbox">
                    <rect id="listbox" rx="0" ry="0" x="0" y="0" width="923" height="270">
                    </rect>
                </svg>
                <ListFont ></ListFont>
                <svg class="rightbox">
                    <rect id="rightbox" rx="0" ry="0" x="0" y="0" width="339" height="311">
                    </rect>
                </svg>
                <span id="ourscore">아울스코어</span>
                <span id="ourscorenumber">98</span>
                <div id="rightboxfontgps">
                    <div>
                        <span id="rightboxfont">리그 오브 섹스</span>
                        <span id="rightboxfont2">1996년</span>
                        <span id="rightboxfont3">양승혁 골짜기</span>
                    </div>
                </div>
                <svg class="listtop2">
                    <rect id="listtop2" rx="0" ry="0" x="0" y="0" width="923" height="41">
                    </rect>
                </svg>
                <svg class="secondbox">
                    <rect id="secondbox" rx="0" ry="0" x="0" y="0" width="923" height="227">
                    </rect>
                </svg>
                <span id="secondboxfont">키워드 월별 순위변동</span>
                <svg class="exbox">
                    <rect id="exbox" rx="0" ry="0" x="0" y="0" width="339" height="772">
                    </rect>
                </svg>
                <svg class="thirdtop">
                    <rect id="thirdtop" rx="0" ry="0" x="0" y="0" width="456" height="41">
                    </rect>
                </svg>
                <svg class="thirdbox">
                    <rect id="thirdbox" rx="0" ry="0" x="0" y="0" width="456" height="196">
                    </rect>
                </svg>
                <span id="thirdfont1">신규 조회수</span>
                <svg class="thirdtop2">
                    <rect id="thirdtop2" rx="0" ry="0" x="0" y="0" width="456" height="41">
                    </rect>
                </svg>

                <svg class="thirdbox2">
                    <rect id="thirdbox2" rx="0" ry="0" x="0" y="0" width="456" height="196">
                    </rect>
                </svg>
                <span id="thirdfont2">신규 동영상수</span>
                <svg class="fourthtop">
                    <rect id="fourthtop" rx="0" ry="0" x="0" y="0" width="923" height="41">
                    </rect>
                </svg>
                <svg class="fourthbox">
                    <rect id="fourthbox" rx="0" ry="0" x="0" y="0" width="923" height="196">
                    </rect>
                </svg>
                <span id="fourthfont">채널 구독자수</span>
            </div>
        );
    }
}

export default TrendMainPage;