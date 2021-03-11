import React, { Component } from 'react';
import '../Styles.css';
import Chip from '@material-ui/core/Chip';

class TrendChip extends Component {


    render() {
        const X = "0 0 0.0 0";

        return (
            <div id="_scrollchip">
                    <Chip id="_chip1"
                        className="chipStyles"
                        label="chip"
                        clickable
                        component="button">
                    </Chip>
                    <Chip id="_chip2"
                        className="chipStyles"
                        label="chip"
                        clickable
                        component="button">
                    </Chip>
                    <Chip id="_chip3"
                        className="chipStyles"
                        label="chip"
                        clickable
                        component="button">
                    </Chip>
                    <Chip id="_chip4"
                        className="chipStyles"
                        label="chip"
                        clickable
                        component="button">
                    </Chip>
                    <Chip id="_chip5"
                        className="chipStyles"
                        label="chip"
                        clickable
                        component="button">
                    </Chip>
                    
                </div>
            
        )
    }
}

export default TrendChip