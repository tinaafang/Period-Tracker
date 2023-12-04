import {combineReducers} from "redux";
import authSlice from "./authSlice.js"
import {configureStore} from "@reduxjs/toolkit";
import {userSlice} from "./userSlice";
import periodSlice from "./periodSlice";
import uiSlice from "./uiSlice";




const rootReducer = combineReducers({
    user: userSlice.reducer,
    period: periodSlice.reducer,
    ui: uiSlice.reducer,
    auth: authSlice.reducer
})

const store = configureStore({
    reducer: rootReducer
})

export default store;