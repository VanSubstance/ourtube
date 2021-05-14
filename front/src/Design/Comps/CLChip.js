import React, { useState, useEffect } from "react";
import "../Styles.css";
import axios from "axios";

const CLChip = ({word, rank}) => {
  return (
    <div
      className="trp_CLChipContainer">
      <div className="trp_CLChip_L">
        {rank}
      </div>
      <div className="trp_CLChip_M">
        {word.text}
      </div>
      <div className="trp_CLChip_R">
        {word.value}
      </div>
    </div >
  );
};

export default CLChip;
