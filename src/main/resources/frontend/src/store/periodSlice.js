import {createAsyncThunk, createSlice, current} from '@reduxjs/toolkit'
import _ from "lodash"
import helper from "../helper";
import {DateObject} from "react-multi-date-picker";

export const fetchPeriods = createAsyncThunk('period/fetchPeriods', async (periodsSearch) => {
    return helper.api("POST", "/api/period/search", periodsSearch);
});

export const fetchStats = createAsyncThunk('period/fetchStats', async (userId) => {
    return helper.api("GET", "/api/period/stats/"+userId);
});

export const createPeriods = createAsyncThunk('period/createPeriods', async (userId,{ getState }) => {
    const periodsCreate = {
        user: {id:userId},
        periods: _.map(getState().period.periods, (period) => {
            return {
                startDate: period[0].format(),
                endDate:period[1].format()
            }
        })
    };
    return helper.api("POST", "/api/period", periodsCreate)
        .then((response) => {
            if (response) {
                return response;
            }
        })
});

export const periodSlice = createSlice({
    name: 'period',
    initialState: {
        periods: [],
        cycleStats:[],
        periodStats:[],
        stats: {
            daysUntilNextPeriod:null,
            avgCycleLength:null,
            avgPeriodLength:null,
            cycleLengthRanges:{min: null, max: null}
        },
        latestPeriod:null
    },
    reducers: {
        setPeriods:(state,action) => {
            state.periods = action.payload;
            // if user double clicks the same date, remove that period
            const currPeriod = _.last(action.payload);
            if(currPeriod?.length === 2) {
                const start = currPeriod[0].toDate().getTime();
                const end = currPeriod[1].toDate().getTime();
                if(start === end) {
                    state.periods.pop();
                }
            }
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchPeriods.fulfilled, (state, action) => {
                if(action.payload) {
                    state.periods = [];
                    _.forEach(action.payload, (period) => {
                        state.periods.push([
                            new DateObject({
                            date: period.startDate,
                            format: "YYYY-MM-DD"
                            }),
                            new DateObject({
                                date: period.endDate,
                                format: "YYYY-MM-DD"
                            })
                        ]);
                    });
                    state.latestPeriod = state.periods.slice(-1)[0];
                }
            })
            .addCase(fetchStats.fulfilled, (state, action) => {
                if(action.payload) {
                    let estimatedNextCycleLength = 28;
                    state.stats = {};
                    if (current(state.periods) && action.payload.periodStats) {
                        state.periodStats = action.payload.periodStats;
                        const avgPeriodLengthStats = _.reduce(action.payload.periodStats, (acc, periodStat) => {
                            // acc is [total period length, total # of periods]
                            // periodStat is [x, # of periods of length x]
                            acc[0] += periodStat[0] * periodStat[1];
                            acc[1] += periodStat[1];
                            return acc;
                        }, [0, 0]);
                        state.stats.avgPeriodLength = avgPeriodLengthStats[0]/avgPeriodLengthStats[1];
                    }
                    const cycleLenghts = _.map(action.payload.cycleStats, "cycleLength");
                    state.stats.cycleLengthRanges = {min: _.min(cycleLenghts), max: _.max(cycleLenghts)};
                    state.stats.avgCycleLength = _.meanBy(action.payload.cycleStats, "cycleLength");
                    if (current(state.periods) && action.payload.cycleStats) {
                            state.cycleStats = [];
                            _.forEach(action.payload.cycleStats, (cycle, index) => {
                                state.cycleStats.push(
                                    {
                                        ...cycle,
                                        x: index
                                    }
                                );
                            });
                            estimatedNextCycleLength = _.meanBy(action.payload.cycleStats.slice(-5), "cycleLength");
                        }
                        const lastCycleStartDate = state.latestPeriod[0].toDate();
                        const estimatedNextPeriodStartDate = lastCycleStartDate.setDate(lastCycleStartDate.getDate() + estimatedNextCycleLength);
                        state.stats.daysUntilNextPeriod = Math.floor((new Date(estimatedNextPeriodStartDate) - new Date()) / (1000 * 60 * 60 * 24));
                        state.cycleStats.push(
                            {
                                cycleStart: state.latestPeriod[0].format().toString().replaceAll("/", "-"),
                                cycleEnd: new Date(estimatedNextPeriodStartDate).toISOString().split('T')[0],
                                cycleLength: Math.round(estimatedNextCycleLength),
                                x: action.payload.cycleStats.length,
                                prediction: true
                            }
                        );
                    }

            })
    },
})

export const {
    setPeriods
} = periodSlice.actions

export default periodSlice;