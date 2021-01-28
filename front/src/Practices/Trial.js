import React, { Component } from 'react';
class Trial extends Component {
    state = {};
    render() {
        return (
            <ul className="trial">
                {
                    this.props.items.foreach((value, index) => {
                        return (
                        <li>{value}</li>
                        );
                    })
                }
            </ul>
        );
    }
}
export default Trial;