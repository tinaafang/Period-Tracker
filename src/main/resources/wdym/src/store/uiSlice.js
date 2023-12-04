import {createSlice} from '@reduxjs/toolkit'
import _ from "lodash"

export const uiSlice = createSlice({
    name: 'ui',
    initialState: {
    },
    reducers: {
        updateState: (state, action) => {
            const stateValue = _.get(state, _.initial(action.payload.path));
            stateValue[_.last(action.payload.path)] = action.payload.data;
        },
        closeAlert:(state, action) => {
            state.activeAlert = null;
        },
        openAlert:(state,action) => {
            debugger;
            state.activeAlert = action.payload;
        }

    }
})



export const { updateState,closeAlert, openAlert } = uiSlice.actions;
export default uiSlice;
