import React from "react";
import "./Styles.css";
import { LeftBoxFont } from "./";

const LeftBox = (props) => {

  return (
      <div>
        <div id="__Leftbox">
          <svg className="__Leftbox"></svg>
        </div>
        <div id="_boxpostion">
          <svg className="_Mainbox" viewBox="0 0 307.779 307.779">
            <path
              id="_Mainbox"
              d="M 0 0 L 307.779296875 0 L 307.779296875 307.779296875 L 0 307.779296875 L 0 0 Z"
            >
              {props.keyword !== undefined ? (
                <img src={props.keyword.thumbnail}></img>
              ) : (
                <img src="https://upload.wikimedia.org/wikipedia/commons/9/92/Question_mark_alternate_inverted.svg"></img>
              )}
            </path>
          </svg>
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
