import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import _ from "lodash"
import helper from "../helper";

export const fetchPeriods = createAsyncThunk('period/fetchPeriods', async (periodsSearch) => {
    return helper.api("GET", "/api/period/search", periodsSearch);
});

export const createPeriods = createAsyncThunk('period/createPeriods', async (periodsCreate) => {
    return helper.api("POST", "/api/period", periodsCreate)
        .then((response) => {
            if (response) {
                debugger;
                return response;
            }
        })
});

export const periodSlice = createSlice({
    name: 'period',
    initialState: {
    },
    reducers: {
        updateState: (state, action) => {
            const stateValue = _.get(state, _.initial(action.payload.path));
            stateValue[_.last(action.payload.path)] = action.payload.data;
        }
    }
})

export const {
    updateState
} = periodSlice.actions
// in the slice file. For example: `useSelector((state) => state.counter.value)`

export default periodSlice;