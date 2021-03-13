import React from "react";
import "./Styles.css";
import "./Css/styles.css";
import { LeftBoxFont } from "./";

const LeftBox = (props) => {

  return (
      <div>
        <div className="_Mainbox">
          {props.keyword != undefined ? (
            <img
              className="leftThumnail"  
                src={props.keyword.thumbnail}></img>
          ) : (
            <img src="https://upload.wikimedia.org/wikipedia/commons/9/92/Question_mark_alternate_inverted.svg"></img>
          )}
        </div>
        <div>
          <svg className="_rankchange">
            <path
              id="_rankchange"
              d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
            ></path>
          </svg>
          <svg className="_viewchange">
            <path
              id="_viewchange"
              d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
            ></path>
          </svg>
          <svg className="_videochange">
            <rect
              id="_videochange"
              rx="0"
              ry="0"
              x="0"
              y="0"
              width="27"
              height="5"
            ></rect>
          </svg>
          <svg className="_scorechange">
            <path
              id="_scorechange"
              d="M 8.17737865447998 0 L 16.35475921630859 10.58249282836914 L 0 10.58249282836914 Z"
            ></path>
          </svg>
        </div>
        <LeftBoxFont keyword={props.keyword} data = {props.data}></LeftBoxFont>
      </div>
  );
};

export default LeftBox;
