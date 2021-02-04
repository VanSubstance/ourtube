import React, { Component } from 'react';
import CtgrResults from './CtgrResults';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import Paper from '@material-ui/core/Paper';

class SearchModule extends Component {

    state = {
        currentType: "트렌드",
        selectedCtgr: "",
        ctgrs: ["카테고리 예시 1", "카테고리 예시 2", "예시 3", "예시 4", "카테고리 예시 5"],
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

    }
    getValues = () => {
        this.setState({
            searchType: this.props.searchType,
        })
    }
    changeType = () => {
        var target = "";
        var color = "green";
        var ctgrss = ["트렌드 1", "트렌드 2", "트렌드 3", "트렌드 4", "트렌드 5"];
        if (this.state.currentType == "트렌드") {
            target = "올타임";
            color = "green";
            ctgrss = ["올타임 1", "올타임 2", "올타임 3", "올타임 4", "올타임 5"];
        } else {
            target = "트렌드";
            color = "red";
            ctgrss = ["트렌드 1", "트렌드 2", "트렌드 3", "트렌드 4", "트렌드 5"];
        }
        this.setState({
            currentType: target,
            logo: {
                width: "80px",
                height: "40px",
                background: color,
                color: "white",
                display: "inline",
            },
        })
    }
    selectCtgr = (element) => {
        this.state.selectedCtgr = element;
        console.log(this.state.selectedCtgr);
    }

    componentDidMount() {
        this.getValues();
        this.setState({
            selectedCtgr: this.state.ctgrs[0]
        });
    }

    render() {
        return (
            <div
                className="SearchModule"
                style={this.state.outside}>
                {
                    this.state.currentType === "트렌드"
                        ? (
                            <button
                                style={this.state.logo}
                                onClick={this.changeType}>
                                {this.state.currentType}
                            </button>
                        )
                        : (
                            <button
                                style={this.state.logo}
                                onClick={this.changeType}>
                                {this.state.currentType}
                            </button>
                        )
                }

                <div style={this.state.searchBar}> 검색하고자 하는 카테고리 </div>
                <button style={this.state.logo}> 검색 </button>

                {
                    // 
                    this.state.searchType === "트렌드"
                        ? (
                            <Paper>
                                {
                                    this.state.ctgrs.map((element) => {
                                        return (
                                            <Chip
                                                label={element}
                                                clickable
                                                component = "button"
                                                onClick={() => this.selectCtgr(element)}>
                                            </Chip>
                                        );
                                    })
                                }
                            </Paper>
                        )
                        : (
                            <Paper>
                                {
                                    this.state.ctgrs.map((element) => {
                                        const path = "/alltime/" + element;
                                        return (
                                            <Chip
                                                label={element}
                                                clickable
                                                component = "button"
                                                onClick={() => this.selectCtgr(element)}>
                                            </Chip>
                                        );
                                    })
                                }
                            </Paper>
                        )
                }
                <CtgrResults
                    func = {this.state.selectedCtgr}>
        
                </CtgrResults>
            </div>
        );
    }
}

export default SearchModule;