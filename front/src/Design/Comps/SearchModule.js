import React, { Component } from 'react';
import CtgrResults from './CtgrResults';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import {SearchPage} from '../';

class SearchModule extends Component {
    
    state = {
        outside: {
            margin: "100px",
            background: "#DEDEDE",
        },
        searchBar: {
            width: "180px",
            height: "40px",
            background: "#c8c8c8",
            display: "inline",
        },
        logo: {
            width: "80px",
            height: "40px",
            background: "red",
            color: "white",
            display: "inline",
        },
        searchType: "",
        
    }
    getValues = () => {
        this.setState ({
            searchType: this.props.searchType,
        })
    }

    componentDidMount() {
        this.getValues();
    }
    render () {
        return (
            <div 
                className = "SearchModule"
                style = {this.state.outside}>
                    {
                        this.state.searchType === "트렌드"
                            ? (
                            <Link 
                                style = {this.state.logo}
                                to = "/trend"> 
                                {this.state.searchType} 
                            </Link>
                            )
                            : (
                            <Link 
                                style = {this.state.logo}
                                to = "/alltime"> 
                                {this.state.searchType} 
                            </Link>
                            )
                    }
                
                <div style = {this.state.searchBar}> 검색어 </div>
                <button style = {this.state.logo}> 검색 </button>
                <CtgrResults>

                </CtgrResults>
            </div>
        );
    }
}

export default SearchModule;