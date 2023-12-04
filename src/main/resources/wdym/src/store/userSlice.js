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
            debugger;
            state.currentUser = action.payload;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchUserByToken.fulfilled, (state, action) => {
                // Mutate the state with the fetched data
                console.log("fulfilled");
                state.currentUser = action.payload;
            })
    },
})


// in the slice file. For example: `useSelector((state) => state.counter.value)`

export const { loginSuccess } = userSlice.actions;
export default userSlice;
