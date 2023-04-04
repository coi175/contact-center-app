import {
    Chart as ChartJS,
    ArcElement,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    LineElement,
    PointElement,
    Tooltip,
    Legend,
} from 'chart.js';
import { Line } from "react-chartjs-2";
import React, {useEffect, useState} from "react";

ChartJS.register(
    CategoryScale,
    ArcElement,
    PointElement,
    LineElement,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
);

const EffectChart = ({values: values, dates: dates}) => {

    useEffect(() => {
        console.log("EFF1: " + values);
        console.log("EFF2: " + dates);
    }, [])

    const options = {
        responsive: true,

        scales: {
            yAxes: {
                title: {
                    display: true,
                    text: "Эффективность",
                    color: 'black',
                    font: {
                        size: 15
                    }
                },
                ticks: {
                    precision: 0,
                }

            },
            xAxes: {
                title: {
                    display: true,
                    text: "Дата",
                    color: 'black',
                    font: {
                        size: 15
                    }
                },

            }
        },
        plugins: {
            legend: {
                position: 'top',
                labels: {
                    // переопределение цвета шрифта
                    fontColor: 'black'
                },
            },
            title: {
                color: 'black',
                display: true,
                font: {
                    size: 25
                },
                text: 'Эффективность',
            },
        },
    };

    const data = {
        labels: dates,
        datasets: [
            {
                label: 'Эффективность',
                data: values,
                borderColor: '#36A2EB',
                color: 'black',
                backgroundColor: '#9BD0F5',
                fill: true,
                tension: 0.5,
            },
        ],
    };


    return <Line className={'chart-style'} type='line' options={options} data={data} />;
}

export default EffectChart;