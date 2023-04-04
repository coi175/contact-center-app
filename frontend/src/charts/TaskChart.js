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

const TaskChart = ({tasks: tasks, ftasks: ftasks, dates: dates}) => {

    useEffect(() => {
        console.log(tasks);
        console.log(dates);
    }, [])

    const options = {
        responsive: true,

        scales: {
            yAxes: {
                title: {
                    display: true,
                    text: "Успешные задачи",
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
                text: 'Статистика задач',
            },
        },
    };

    const data = {
        labels: dates,
        datasets: [
            {
                label: 'Кол-во выполненных задач по дате',
                data: tasks,
                borderColor: '#36A2EB',
                color: 'black',
                backgroundColor: '#9BD0F5',
                fill: true,
                tension: 0.5,
            },
            {
                label: 'Кол-во неудачных задач',
                data: ftasks,
                borderColor: '#eb3636',
                color: 'black',
                backgroundColor: '#f59b9b',
                fill: true,
                borderWidth: 5,
                tension: 0.5,
            },
        ],
    };


    return <Line className={'chart-style'} type='line' options={options} data={data} />;
}

export default TaskChart;