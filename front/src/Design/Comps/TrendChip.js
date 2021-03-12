import React, { useState } from 'react';
import '../Styles.css';
import Chip from '@material-ui/core/Chip';

const TrendChip = (props) => {

    return (
        <div id="_scrollchip">
            {
                props.ctgrs.map((element, index) => {
                    return (
                        <Chip id="_chip1"
                            style = {{
                                marginLeft: index * 110 + "px"
                            }}
                            className="chipStyles"
                            label={element}
                            clickable
                            component="button">
                        </Chip>
                    );
                })
            }
        </div>

    );
}

export default TrendChip