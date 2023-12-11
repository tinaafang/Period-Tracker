import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import _ from "lodash"
import helper from "../helper";
import {DateObject} from "react-multi-date-picker";

export const fetchPeriods = createAsyncThunk('period/fetchPeriods', async (periodsSearch) => {
    return helper.api("POST", "/api/period/search", periodsSearch);
});


export const fetchIntervals = createAsyncThunk('period/fetchIntervals', async (userId) => {
    return helper.api("GET", "/api/period/intervals/"+userId);
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
        intervals:[]
    },
    reducers: {
        updateState: (state, action) => {
            const stateValue = _.get(state, _.initial(action.payload.path));
            stateValue[_.last(action.payload.path)] = action.payload.data;
        },
        setPeriods:(state,action) => {
            state.periods = action.payload;
            // if double clicking the same date, remove that period
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
                }
            })
            .addCase(fetchIntervals.fulfilled, (state, action) => {
                if(action.payload) {
                    state.intervals = [];
                    console.log(action.payload)
                    _.forEach(action.payload, (interval,index) => {

                        state.intervals.push(
                            {...interval,
                            x:index}
                        );
                    });
                    // console.log(state.intervals)
                }
            })
    },
})

export const {
    setPeriods,
    handleFocusDateChange
} = periodSlice.actions
// in the slice file. For example: `useSelector((state) => state.counter.value)`

export default periodSlice;