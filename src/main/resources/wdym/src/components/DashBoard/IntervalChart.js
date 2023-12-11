import React from 'react';
import { Filler } from "chart.js";
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
    Legend,
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
    Legend,
    Filler
);


const labels = ['January', 'February',"3","4","5","6"];

const genericOptions = {
    fill: false,
    interaction: {
        intersect: false
    },
    radius: 0,
    scales: {
            y: {
                beginAtZero: false
            }

    },
    plugins: {
        tooltip: {
            callbacks: {
                title: (context) => {
                    const intervalObject = context[0].dataset.data[context[0].dataIndex];
                    return `${intervalObject.interval} days`
                },
                label: (context) => {
                    const intervalObject = context.dataset.data[context.dataIndex];
                    return `${intervalObject.intervalStart} - ${intervalObject.intervalEnd}`
                },
            }
        },
        datalabels: {
            anchor: 'end',
            align: 'top',
            formatter: function(value, context) {
                return value.interval;
            }
        }
    },
};

const skipped = (ctx, value) => ctx.p0.skip || ctx.p1.skip ? value : undefined;

function IntervalChart() {
    const intervals = useSelector(state => state.period.intervals);
    const test = {
        labels,
        datasets: [
            {
                label: 'Intervals',
                data: intervals,
                borderColor: 'rgb(255, 99, 132)',
                backgroundColor: 'rgba(255, 255, 255)',
                segment: {
                    borderDash: ctx => skipped(ctx, [6, 6]),
                },
                spanGaps: true,
                parsing: {
                    yAxisKey: 'interval'
                }
            }]
    };
    return <div className={'interval-chart-container'}><Line
        options={genericOptions}
        data={test}
        plugins={[ChartDataLabels]}
    />
    </div>
}

export default IntervalChart;