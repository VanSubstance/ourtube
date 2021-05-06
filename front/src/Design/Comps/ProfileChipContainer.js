import { TableHead } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import "../Styles.css";
import ProfileChip from "./ProfileChip";

const ProfileChipContainer = (props) => {
  return (
    <div>
      {props.titles.map((title) => {
        return (
          <ProfileChip
            title = {title}
            func={props.func1}
          ></ProfileChip>
        );
      })}
    </div>
  );
};

export default ProfileChipContainer;
