import React, { Component } from 'react';
class Trial extends Component {
    render() {
        return (
            <ul className = "trial">
                {this.props.items.map((item) => (
                    <li>{item.name} | {item.address}</li>
                ))}
            </ul>
        );
    }
}
export default Trial;