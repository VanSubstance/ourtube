import React, { useState } from "react";
import axios from "axios";
import "./Styles.css";
import "./Css/leftbox.css"
import moment from "moment";

const LeftBoxFont = (props) => {
  const [dateTarget, setDateTarget] = useState(
    {
      right: moment().format("yyyy-MM-DD"),
      left: moment().subtract(1, "days").format("yyyy-MM-DD"),
    }
  );

  //SI단위로 숫자를 세어주는 함수, x에 값을 삽입
  const siRound = (x) => {
    if (x<1e3) return x+'';
    const digits = Math.log10(x) | 0
    const tier = digits/3 | 0
    let str = (x / 10**(tier*3)).toFixed(2-(digits%3))
    // Turn "10.00" into "10.0" and "100.0" into "100"
    str = str.replace(/^(.{3})\..+|^(.{4}).+/, '$1$2')
    if (str.length>4) { ///Add this check
      siRound(str)
    }
    return str + (['','k','M','G','T'])[tier]
}

  return (
    <div
      className="mp_LeftBoxContents">
      <div
        className="mp_LeftBoxTop">
        <div id="_gamestyle">
          {props.ctgr !== undefined ? (
            <span>{props.ctgr}</span>
          ) : (
            <span>없음</span>
          )}
        </div>
        <div id="_gameName">
          {props.keyword !== undefined ? (
            <span>{props.keyword.title}</span>
          ) : (
            <span>없음</span>
          )}
        </div>
        <div id="_glorystyle1">
          <span>이 주의 최고 조회수</span>
        </div>
        <img
          className="mp_GloryFlag1"
          src="/Ex/BadgeFlagBlue.png">
        </img>
        <div id="_glorystyle2">
          <span>이 주의 최고 동영상</span>
        </div>
        <img
          className="mp_GloryFlag2"
          src="/Ex/BadgeFlagBlue.png">
        </img>
        <div id="_glorystyle3">
          <span>이 주의 최고 게임</span>
        </div>
        <img
          className="mp_GloryFlag3"
          src="/Ex/BadgeFlagBlue.png">
        </img>
      </div>
      <div
        className="mp_LeftBoxBottom">
        <div id="_leftboxmainfont1">
          <span>순위</span>
        </div>
        <div id="_leftboxmainfont2">
          <span>총 조회수</span>
        </div>
        <div id="_leftboxmainfont3">
          <span>검색량</span>
        </div>
        <div id="_leftboxmainfont4">
          <span>아울 스코어</span>
        </div>
        <div id="_now">
          <span>
            {props.data[dateTarget.right] !== undefined
              ? dateTarget.right
              : 0}
          </span>
        </div>
        <div id="_past">
          <span>
            {props.data[dateTarget.left] !== undefined
              ? dateTarget.left
              : 0}
          </span>
        </div>
        <div id="_pastranknumber1">
          <span>1</span>
        </div>
        <div id="_pastranknumber2">
          <span>
            {props.data[dateTarget.left] !== undefined
              ? siRound(props.data[dateTarget.left].viewCount)
              : 0}
          </span>
        </div>
        <div id="_pastranknumber3">
          <span>{props.data[dateTarget.left] !== undefined
              ? siRound(props.data[dateTarget.left].resultCount)
              : 0}</span>
        </div>
        <div id="_pastranknumber4">
          <span>4</span>
        </div>
        <div id="_nowranknumber1">
          <span>5</span>
        </div>
        <div id="_nowranknumber2">
          <span>
            {props.data[dateTarget.right] !== undefined
              ? siRound(props.data[dateTarget.right].viewCount)
              : 0}
          </span>
        </div>
        <div id="_nowranknumber3">
          <span>{props.data[dateTarget.right] !== undefined
              ? siRound(props.data[dateTarget.right].resultCount)
              : 0}</span>
        </div>
        <div id="_nowranknumber4">
          <span>8</span>
        </div>
        <div
          className="mp_ChangeMarks">
          <svg
            className="mp_RankChange">
            {
              props.data[dateTarget.left] !== undefined
                ? (props.data[dateTarget.right] !== undefined
                  ? (props.data[dateTarget.left].viewCount < props.data[dateTarget.right].viewCount
                    ? <path
                      id="mp_UpArrow"
                      d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
                    ></path>
                    : (props.data[dateTarget.left].viewCount > props.data[dateTarget.right].viewCount
                      ? <path
                        id="mp_DownArrow"
                        d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
                      ></path>
                      : <rect
                        id="mp_NoChangeBar"
                        rx="0"
                        ry="0"
                        x="0"
                        y="0"
                        width="27"
                        height="5"
                      ></rect>
                    )
                  )
                  : <rect
                    id="mp_NoChangeBar"
                    rx="0"
                    ry="0"
                    x="0"
                    y="0"
                    width="27"
                    height="5"
                  ></rect>)
                : <rect
                  id="mp_NoChangeBar"
                  rx="0"
                  ry="0"
                  x="0"
                  y="0"
                  width="27"
                  height="5"
                ></rect>
            }
          </svg>
          <svg className="mp_ViewChange">
            {
              props.data[dateTarget.left] !== undefined
                ? (props.data[dateTarget.right] !== undefined
                  ? (props.data[dateTarget.left].viewCount < props.data[dateTarget.right].viewCount
                    ? <path
                      id="mp_UpArrow"
                      d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
                    ></path>
                    : (props.data[dateTarget.left].viewCount > props.data[dateTarget.right].viewCount
                      ? <path
                        id="mp_DownArrow"
                        d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
                      ></path>
                      : <rect
                        id="mp_NoChangeBar"
                        rx="0"
                        ry="0"
                        x="0"
                        y="0"
                        width="27"
                        height="5"
                      ></rect>
                    )
                  )
                  : <rect
                    id="mp_NoChangeBar"
                    rx="0"
                    ry="0"
                    x="0"
                    y="0"
                    width="27"
                    height="5"
                  ></rect>)
                : <rect
                  id="mp_NoChangeBar"
                  rx="0"
                  ry="0"
                  x="0"
                  y="0"
                  width="27"
                  height="5"
                ></rect>
            }
          </svg>
          <svg className="mp_ScoreChange">
            {
              props.data[dateTarget.left] !== undefined
                ? (props.data[dateTarget.right] !== undefined
                  ? (props.data[dateTarget.left].resultCount < props.data[dateTarget.right].resultCount
                    ? <path
                      id="mp_UpArrow"
                      d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
                    ></path>
                    : (props.data[dateTarget.left].resultCount > props.data[dateTarget.right].resultCount
                      ? <path
                        id="mp_DownArrow"
                        d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
                      ></path>
                      : <rect
                        id="mp_NoChangeBar"
                        rx="0"
                        ry="0"
                        x="0"
                        y="0"
                        width="27"
                        height="5"
                      ></rect>
                    )
                  )
                  : <rect
                    id="mp_NoChangeBar"
                    rx="0"
                    ry="0"
                    x="0"
                    y="0"
                    width="27"
                    height="5"
                  ></rect>)
                : <rect
                  id="mp_NoChangeBar"
                  rx="0"
                  ry="0"
                  x="0"
                  y="0"
                  width="27"
                  height="5"
                ></rect>
            }
          </svg>
          <svg className="mp_VideoChange">
            {
              props.data[dateTarget.left] !== undefined
                ? (props.data[dateTarget.right] !== undefined
                  ? (props.data[dateTarget.left].viewCount < props.data[dateTarget.right].viewCount
                    ? <path
                      id="mp_UpArrow"
                      d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
                    ></path>
                    : (props.data[dateTarget.left].viewCount > props.data[dateTarget.right].viewCount
                      ? <path
                        id="mp_DownArrow"
                        d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
                      ></path>
                      : <rect
                        id="mp_NoChangeBar"
                        rx="0"
                        ry="0"
                        x="0"
                        y="0"
                        width="27"
                        height="5"
                      ></rect>
                    )
                  )
                  : <rect
                    id="mp_NoChangeBar"
                    rx="0"
                    ry="0"
                    x="0"
                    y="0"
                    width="27"
                    height="5"
                  ></rect>)
                : <rect
                  id="mp_NoChangeBar"
                  rx="0"
                  ry="0"
                  x="0"
                  y="0"
                  width="27"
                  height="5"
                ></rect>
            }
          </svg>
        </div>
      </div>
    </div>
  );
};

export default LeftBoxFont;

// {
//   props.data[dateTarget.left] !== undefined
//     ? (props.data[dateTarget.right] !== undefined
//       ? (props.data[dateTarget.left].viewCount < props.data[dateTarget.right].viewCount
//         ? <path
//           id="mp_UpArrow"
//           d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
//         ></path>
//         : (props.data[dateTarget.left].viewCount > props.data[dateTarget.right].viewCount
//           ? <path
//             id="mp_DownArrow"
//             d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
//           ></path>
//           : <rect
//             id="mp_NoChangeBar"
//             rx="0"
//             ry="0"
//             x="0"
//             y="0"
//             width="27"
//             height="5"
//           ></rect>
//         )
//       )
//       : (console.log('우측 데이터 없음'))) 
//     : (console.log('좌측 데이터 없음'))
// }