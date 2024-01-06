import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import _ from "lodash"
import helper from "../helper";
export const fetchUserByToken = createAsyncThunk('user/fetchUserByToken', async () => {
    return helper.api("GET", `/auth/${localStorage.getItem("jwt")}`);
});
export const userSlice = createSlice({
    name: 'user',
    initialState: {
    },
    reducers: {
        updateState: (state, action) => {
            const stateValue = _.get(state, _.initial(action.payload.path));
            stateValue[_.last(action.payload.path)] = action.payload.data;
        },
        loginSuccess: (state, action) => {
            state.currentUser = action.payload;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchUserByToken.fulfilled, (state, action) => {
                state.currentUser = action.payload;
            })
    },
})



export const { loginSuccess } = userSlice.actions;
export default userSlice;
