import { TableHead } from '@material-ui/core';
import React, { useState, useEffect } from 'react';
import '../Styles.css';

const ProfileChip = (props) => {

    return (
        <div>
            <div
                className="tmp_PFBoxChip">
                <button
                    className="tmp_PFBoxDeleteButton">
                        X
                </button>
                <a
                    className="tmp_lookWellBox"
                // href={/game/ + props.keyword.title}
                >
                    <div
                        className="tmp_lookWell">
                        자세히 보기
                    </div>
                </a>
                <div
                    className="tmp_PFThumbnailCircle">
                    <img
                        className="tmp_PFThumbnail"
                        src="/Ex/happy.jpg"></img>
                </div>
                <div
                    className="tmp_PFKeywordName">
                    제목
                </div>
                <div
                    className="tmp_PFKeywordYear">
                    테스트 제작연도
                </div>
                <div
                    className="tmp_PFKeywordCompony">
                    테스트 제작사
                            </div>
            </div>
        </div>
    );
}

export default ProfileChip;