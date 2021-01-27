import React, { Component } from 'react';
import Chart from 'chart.js';

class About extends Component {
    render() {
        const ctx = document.getElementById('myChart');
        new Chart(ctx, {
            type: "pie",
            data: {
              labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
              datasets: [
                {
                  label: "# of Votes",
                  data: [12, 19, 3, 5, 2, 3],
                  backgroundColor: [
                    "Red",
                    "Blue",
                    "Yellow",
                    "Green",
                    "Purple",
                    "Orange"
                  ],
                  borderColor: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
                  borderWidth: 1
                }
              ]
            }
          });
      
        return (
            <div>
                <h2>준희 공부</h2>
                <h1>TEST</h1>
                <canvas id="myChart" width="400" height="400"></canvas>
        </div>


        );
    }
}

export default About;