import React, { Component } from 'react';
import { RankList } from './Comps';

class AlltimeMainPage extends Component {

    state = {
        chart: {
            width: "200px",
            height: "100px",
            background: "#000000",
            color: "#FFFFFF",
            margin: "100 100 100 100",
        }
    }
    render() {
        return (
            console.log("SearchPage opened."),
            <div>
                <p>{this.props.searchType} 페이지</p>
                <RankList>

                </RankList>
                <div
                    style={this.state.chart}>
                    <p> 바 그래프: 좋아요 싫어요 비율 </p>
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 월별 순위 변동 </p>
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 동영상 신규 조회수 </p>
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 신규 동영상 개수 </p>
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 동영상 신규 조회수 </p>
                </div>
            </div>
        );
    }
}

export default AlltimeMainPage;