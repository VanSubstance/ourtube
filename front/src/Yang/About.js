import React, { Component } from 'react';
import { Bar } from 'react-chartjs-2';

class About extends Component {

    RandomInput = () => {

    } 



    render() {
        return (
            <div>
                <h2>준희 공부</h2>
                <Bar
                    data = {{
                        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                        datasets: [{
                            label: 'test',
                            data: [
                                Math.floor(Math.random()*100),
                                Math.floor(Math.random()*100),
                                Math.floor(Math.random()*100),
                                Math.floor(Math.random()*100),
                                Math.floor(Math.random()*100),
                                Math.floor(Math.random()*100)
                            ],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1
                        }]
                    }}
                    height = {400}
                    width = {400}
                    options={{
                        maintainAspectRatio: false,
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }}
                />
                <button>
                    test
                </button>
                
        </div>
        


        );
    }
}

export default About;