import React, { Component, useState, useEffect } from 'react';
import './Styles.css';
import './Css/TrendResultPage.css';
import { ListFont, TrendChip } from './Comps';
import axios from "axios";
import Chip from "@material-ui/core/Chip";
import { Bar, Line } from "react-chartjs-2";


const TrendResultPage = (props) => {

    const [url] = useState("http://222.232.15.205:8082");

    return (
        console.log(props.match.params.keyword),
        <div
            className="trp_MainWarpper">
            <div
                className="trp_BackGroundPanel">
            </div>
            <div
                className="trp_BackGroundPanelLine">
            </div>

            {/* //배경 및 그라데이션  */}

            <div id="trp_ScollWallPaper">
                <img
                    className="trp_ScrollWallPaperImg"
                    src="/Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png"
                    srcSet="/Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png">
                </img>
                <svg className="trp_scollGradient"
                    width="1920px"
                    height="1500px"
                    viewBox="0 0 1920 1500"
                    preserveAspectRatio="xMidyMin meet">
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
                        id="trp_Gradient"
                        d="M 0 0 L 1920 0 L 1920 1500 L 0 1500 L 0 0 Z"
                    ></path>
                </svg>
            </div>

            {/* 헤더 */}

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

            {/* 컨테이너 */}

            <div id="container">
                <div
                    className="trp_LeftBox">
                    <div
                        className="trp_TextCloudBox">
                        <div
                            className="trp_BoxNameBar">
                            텍스트 클라우드
                        </div>
                    </div>
                    <div
                        className="trp_CommentListBox">
                        <div
                            className="trp_BoxNameBar">
                            연관 댓글 순위
                        </div>
                        <div
                            className="trp_CommentList">
                            <div
                                className="trp_CLChip_1">
                                순위
                            </div>
                            <div
                                className="trp_CLChip_2">
                                내용
                            </div>
                            <div
                                className="trp_CLChip_3">
                                빈도
                            </div>
                            <div
                                className="trp_CLChip_4">
                                테스트
                            </div>
                            <div
                                className="trp_CLChip_4">
                                테스트
                            </div>
                            <div
                                className="trp_CLChip_4">
                                테스트
                            </div>
                        </div>
                    </div>
                    <div
                        className="trp_GraphBox_1">
                        <div
                            className="trp_BoxNameBar">
                            좋아요 싫어요 평균
                        </div>
                    </div>
                    <div
                        className="trp_GraphBox_2">
                        <div
                            className="trp_BoxNameBar">
                            활동 비
                        </div>
                    </div>
                    <div
                        className="trp_GraphBox_3">
                        <div
                            className="trp_BoxNameBar">
                            피드백 지수
                        </div>
                    </div>
                    <div
                        className="trp_GraphBox_4">
                        <div
                            className="trp_BoxNameBar">
                            키워드 관련 동영상 평균 누적 조회
                        </div>
                    </div>
                    <div
                        className="trp_GraphBox_5">
                        <div
                            className="trp_BoxNameBar">
                            키워드 관련 동영상 활동 횟수
                        </div>
                    </div>
                    <div
                        className="trp_GraphBox_6">
                        <div
                            className="trp_BoxNameBar">
                            주별 순위
                        </div>
                    </div>
                </div>
                <div
                    className="trp_RightBox">
                    <div
                        className="trp_ProfileBox">
                    </div>
                    <div
                        className="trp_RadarBox">
                        <div
                            className="trp_BoxNameBar">
                            레이더 그래프
                        </div>
                    </div>
                    <div
                        className="trp_KWDRecommend">
                        <div
                            className="trp_BoxNameBar">
                            추천 키워드
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default TrendResultPage;