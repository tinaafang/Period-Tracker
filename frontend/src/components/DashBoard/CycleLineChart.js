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
    SubTitle,
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
    SubTitle,
    Tooltip,
    Legend
);




const skipped = (ctx, value) => ctx.p1.raw.prediction === true ? value : undefined;

function CycleLineChart() {
    const cycleStats = useSelector(state => state.period.cycleStats);
    const cycleLengths = _.map(cycleStats,"cycleLength");
    const stepSize = Math.ceil((_.max(cycleLengths)-_.min(cycleLengths))/4);
    const maxTick = _.max(cycleLengths) + stepSize - (_.max(cycleLengths) % stepSize === 0 ? stepSize : _.max(cycleLengths) % stepSize);
    const minTick = _.min(cycleLengths) - _.min(cycleLengths) % stepSize;
    const labels = _.map(cycleStats,(cycleStat) => {
        return `cycle #${cycleStat.x+1}`;
    });
    const options = {
        fill: false,
        responsive: true,
        interaction: {
            intersect: false
        },
        radius: 0,
        scales: {
            y: {
                min: minTick,
                max: maxTick,
                ticks: {
                    stepSize: stepSize,
                    padding: 10,
                    callback: (value, index) => value === maxTick ? ["(Days)",value] : value,
                    font: {
                        size: 14
                    },
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
                ticks: {
                    font: {
                        size: 14
                    }
                },
            },
        },
        plugins: {
            tooltip: {
                callbacks: {
                    title: (context) => {
                        const cycleStat = context[0].dataset.data[context[0].dataIndex];
                        return `${cycleStat.cycleLength} Days`
                    },
                    label: (context) => {
                        const cycleStat = context.dataset.data[context.dataIndex];
                        return [`start: ${cycleStat.cycleStart}`,`end : ${cycleStat.cycleEnd}`];
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
                    size: 18,
                    family:'Inclusive Sans'

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
            interaction: {
                mode: 'nearest'
            },
        },
    };

    const data = {
        labels,
        datasets: [
            {
                label: 'cycleStats',
                data: cycleStats,
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

export default CycleLineChart;