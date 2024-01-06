import React from 'react';
import _ from "lodash";
import ChartDataLabels from 'chartjs-plugin-datalabels';

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    Title,
    Tooltip,
    Legend,
    BarElement, SubTitle
} from 'chart.js';
import { Bar } from 'react-chartjs-2';
import {useSelector} from "react-redux";
ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    SubTitle,
    Tooltip,
    Legend
);



function PeriodBarChart() {
    const periodStats = useSelector(state => state.period.periodStats);

    const periodCounts =  _.map(periodStats,(periodStats) => {
        return periodStats[1];
    });
    let labels = _.map(periodStats,(periodStats) => {
        return periodStats[0].toString() + " Days";
    });
    const maxPeriodCount = _.max(periodCounts);
    const options = {
        fill: false,
        responsive: true,
        plugins: {
            title: {
                fullSize: true,
                display: true,
                text: 'Period Duration Counts',
                font: {
                    size: 18,
                },
                padding: {
                    top: 20
                },
                align:'start'
            },
            subtitle: {
                display: true,
                text: 'based on data for the past 1 year',
                padding: {
                    bottom: 35
                },
                align:'end'

            },
            legend: {
                display: false
            },
            datalabels: {
                anchor: 'end',
                align: 'end'
            },
        },
            scales: {
                y: {
                    max: maxPeriodCount + 1,
                    ticks: {
                        stepSize: 1,
                        padding: 10,
                        font: {
                            size: 14
                        },
                    },
                    grid: {
                        drawTicks: false,
                    },
                    border: {
                        dash: [5, 10],
                    },
                },
                x: {
                    grid: {
                        display: false,
                    },
                    ticks: {
                        font: {
                            size: 14
                        },
                    }
                }

        }
    };

    const data = {
        labels,
        datasets: [
            {
                label: 'periodStats',
                data: periodCounts,
                backgroundColor: 'hotpink'
            }]
    };
    return <div className={'period-bar-chart'}><Bar
        options={options}
        data={data}
        plugins={[ChartDataLabels]}
    />
    </div>
}

export default PeriodBarChart;