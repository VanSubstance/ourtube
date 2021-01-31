import React, { Component } from 'react';
import { SearchModule } from './Comps';

class MainPage extends Component {
    
    state = {
        ex01: {
            width: "100px",
            height: "100px",
            background: "#000000",
            margin: "100px 0 0 100px",
        }
    }
    render () {
        return (
            <div>
                <SearchModule searchType = "트렌드">

                </SearchModule>
                <SearchModule searchType = "올타임">
                    
                </SearchModule>
            </div>
        );
    }
}

export default MainPage;