import React, { useState, useEffect } from "react";
import "../Styles.css";
import CLChip from "./CLChip";

const ProfileChipContainer = (props) => {
  let rank = 0;
  return (
    <div
      className="tmp_ChipContainer">
        {props.words.map((word) => {
          rank += 1;
          return (
            <CLChip word = {word} rank = {rank}></CLChip>
          );
        })}
    </div>
  );
};

export default ProfileChipContainer;
