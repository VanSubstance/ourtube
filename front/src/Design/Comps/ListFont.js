import React, { Component } from 'react';
import '../Styles.css';

const ListFont = (props) => {

    const addRows = (dataRows) => {
        return (
            dataRows.map((dataRow, index) => {
                return (addRow(dataRow, index));
            })
        );
    }

    const checkFunction = (e) => {
        console.log("clicked");
    }

    const addRow = (dataRow, index) => {
        return (
            <tr align="center" >
                <td id="checkbox">
                    <form action='a.jsp' >
                        <input type='checkbox' name='checkboxList' onClick={(e) => {checkFunction(e)}} value='checkbox' />
                    </form>
                </td>
                <td align="left">{index + 1}</td>
                <td id="longfont" align="center">{dataRow.title}</td>
                <td>{dataRow.viewCount}</td>
                <td>{dataRow.score}</td>
                <td>{dataRow.likeCount}</td>
                <td>{dataRow.dislikeCount}</td>
                <td>{dataRow.videoCount}</td>
            </tr>
        );
    }

    const headTitle = (dataRow) => {
        return (
            <tr>
                <th>{dataRow.rank}</th>
                <th>{dataRow.keyword}</th>
                <th>{dataRow.like}</th>
                <th>{dataRow.unlike}</th>
                <th>{dataRow.search}</th>
                <th>{dataRow.video}</th>
                <th>{dataRow.comment}</th>
            </tr>

        );
    }
    return (
        <div>
            <div>
                <table id="scrollHead1" cellSpacing="0">
                    {headTitle(
                        {
                            rank: "순위",
                            keyword: "키워드",
                            like: "좋아요",
                            unlike: "싫어요",
                            search: "검색량",
                            video: "동영상",
                            comment: "댓글수"
                        }
                    )}
                </table>
            </div>

            <div id="scrollbitch" >
                <table id="scrollbitch2" cellSpacing="30">
                    {addRows(
                        [
                            {
                                title: "리그 오브 레전드",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "Higgs Domino Island-Gaple QiuQiu Poker Game Online",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "프로젝트 세카이: 컬러풀 스테이지 feat. 하츠네 미쿠",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "히어로즈 오브 더 스톰",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },
                            {
                                title: "test3",
                                viewCount: 2314,
                                score: 213123,
                                likeCount: 2424,
                                dislikeCount: 1231423,
                                videoCount: 123214
                            },

                        ]
                    )}

                </table>
            </div>
        </div>

    );
}

export default ListFont;