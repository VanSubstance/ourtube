import { TableHead } from '@material-ui/core';
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

    let currentChecked = 0;

    const checkFunction = (e) => {
        if (e.target.checked) {
            if (currentChecked == 5) {
                alert("최대 5개까지만 선택 가능합니다.");
                e.target.checked = false;
            } else {
                currentChecked += 1;
                props.func(e.target.value, 0);
            }
        } else {
            currentChecked -= 1;
            props.func(e.target.value, 1);
        }
    }

    const addRow = (dataRow, index) => {
        return (
            <tr align="left" >
                <td id="checkbox">
                    <form action='a.jsp' >
                        <input type='checkbox' name='checkboxList' onClick={(e) => { checkFunction(e) }} value = {dataRow.title}/>
                    </form>
                </td>
                <td align="left">{index + 1}</td>
                <td id="longfont" align="center">{dataRow.title}</td>
                <td >{dataRow.viewCount}</td>
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
            </div>

            <div id="scrollbitch" >
                <table id="scrollbitch2" cellSpacing="0">
                    <tbody>
                        {addRows(props.keywords
                        )}
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default ListFont;