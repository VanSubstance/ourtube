import { TableHead } from '@material-ui/core';
import React, { useState, useEffect } from 'react';
import '../Styles.css';

const ListFont = (props) => {

    const addRows = (dataRows) => {
        return (
            dataRows.map((dataRow, index) => {
                return (addRow(dataRow, index));
            })
        );
    }

    const addRow = (dataRow, index) => {
        return (
            <div
                className="tmp_KeywordRankParent">
                <button className="tmp_KeywordRankButton" onClick = {() => props.func(dataRow.title, 0)}></button>
                <div className="tmp_KeywordRankChip">{index + 1}</div>
                <div className="tmp_KeywordNameChip">{dataRow.title}</div>
                <div className="tmp_KeywordScoreChip">아울스코어</div>
                <div className="tmp_KeywordScoreChip">누적 조회수</div>
                <div className="tmp_KeywordScoreChip">누적 동영상</div>
                <div className="tmp_KeywordScoreChip">누적 댓글수</div>
                <div className="tmp_KeywordScoreChip">검색량</div>
            </div>

            // <tr align="left" >
            //     <td align="left">{index + 1}</td>
            //     <td id="longfont" align="center">{dataRow.title}</td>
            //     <td>{dataRow.viewCount}</td>
            //     <td>{dataRow.score}</td>
            //     <td>{dataRow.likeCount}</td>
            //     <td>{dataRow.dislikeCount}</td>
            //     <td>{dataRow.videoCount}</td>
            // </tr>
        );
    }

    // const headTitle = (dataRow) => {
    //     return (
    //         <tr>
    //             <th>{dataRow.rank}</th>
    //             <th>{dataRow.keyword}</th>
    //             <th>{dataRow.like}</th>
    //             <th>{dataRow.unlike}</th>
    //             <th>{dataRow.search}</th>
    //             <th>{dataRow.video}</th>
    //             <th>{dataRow.comment}</th>
    //         </tr>
    //     );
    // }

    return (
        <div>
            <div className="tmp_BoxNameBarNoPad">
                <div className="tmp_KeywordRankHead">순위</div>
                <div className="tmp_KeywordNameHead">이름</div>
                <div className="tmp_KeywordScoreHead">아울스코어</div>
                <div className="tmp_KeywordScoreHead">누적 조회수</div>
                <div className="tmp_KeywordScoreHead">누적 동영상</div>
                <div className="tmp_KeywordScoreHead">누적 댓글수</div>
                <div className="tmp_KeywordScoreHead">검색량</div>
            </div>
            {/* <div>
                <table id="scrollHead1" cellSpacing="0">
                    <thead>
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
                    </thead>
                </table>
            </div> */}

            <div id="scrollbitch" >
                {addRows(props.keywords
                )}
            </div>
        </div>

    );
}

export default ListFont;