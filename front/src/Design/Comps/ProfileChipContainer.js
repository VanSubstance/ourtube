import React, { useState, useEffect } from "react";
import "../Styles.css";
import ProfileChip from "./ProfileChip";

const ProfileChipContainer = ({ titles, func }) => {
  return (
    <div
      className="tmp_ProfileChipContainer">
      {
        titles.map((title) => (
          <ProfileChip title={title} func={func}></ProfileChip>
        ))
      }
    </div>
  );
};

export default ProfileChipContainer;
