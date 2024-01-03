import React from 'react';
import _ from "lodash";
import ChartDataLabels from 'chartjs-plugin-datalabels';

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
} from 'chart.js';
import { Line } from 'react-chartjs-2';
import {useSelector} from "react-redux";
ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);




const skipped = (ctx, value) => ctx.p1.raw.prediction === true ? value : undefined;

function CycleChart() {
    const cycles = useSelector(state => state.period.cycles).slice(-12);
    const cycleLengths = _.map(cycles,"cycleLength");
    const minCycleLength = _.min(cycleLengths);
    const maxCycleLength = _.max(cycleLengths);
    console.log(cycleLengths);
    const labels = _.map(cycles,(cycle) => {
        return `cycle #${cycle.x+1}`;
    });
    const options = {
        fill: false,
        interaction: {
            intersect: false
        },
        radius: 0,
        scales: {
            y: {
                min: minCycleLength-2,
                max: maxCycleLength+2,
                ticks: {
                    stepSize: Math.ceil((maxCycleLength-minCycleLength)/6),
                    padding: 10,
                    callback: (value, index) => value === maxCycleLength+2 ? ["(Days)",value] : value
                },
                grid: {
                    drawTicks: false,
                },
                border: {
                    display: false,
                    dash: [5, 10],
                },
                },
            x: {
                grid: {
                    display: false,
                },
                border: {
                    display: false,
                },
            },
        },
        plugins: {
            tooltip: {
                callbacks: {
                    title: (context) => {
                        const cycleObject = context[0].dataset.data[context[0].dataIndex];
                        return `${cycleObject.cycleLength} Days`
                    },
                    label: (context) => {
                        const cycleObject = context.dataset.data[context.dataIndex];
                        return [`start: ${cycleObject.cycleStart}`,`end : ${cycleObject.cycleEnd}`];
                    },
                }
            },
            datalabels: {
                anchor: 'end',
                align: 'top',
                formatter: function(value, context) {
                    return value.cycleLength;
                }
            },
            title: {
                fullSize:true,
                display: true,
                text: 'Cycle Duration Trend Line',
                font: {
                    size:15,
                },
                padding: {
                    top: 20,
                    bottom: 20
                }
            },
            legend: {
                display: false
            },
            interaction: {
                mode: 'nearest'
            },
        },
    };

    const data = {
        labels,
        datasets: [
            {
                label: 'cycles',
                data: cycles,
                borderColor: 'rgb(255, 105, 180)',
                backgroundColor: 'rgba(255, 255, 255)',
                segment: {
                    borderDash: ctx => skipped(ctx, [6, 6]),
                },
                spanGaps: true,
                tension: 0.15,
                parsing: {
                    yAxisKey: 'cycleLength'
                }
            }]
    };
    return <div className={'cycle-line-chart'}><Line
        options={options}
        data={data}
        plugins={[ChartDataLabels]}
    />
    </div>
}

export default CycleChart;