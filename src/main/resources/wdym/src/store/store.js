import {combineReducers} from "redux";
import authSlice from "./authSlice.js"
import {configureStore} from "@reduxjs/toolkit";




const rootReducer = combineReducers({
    // user: usersReducer,
    // period: periodReducer,
    // ui: uiReducer,
    auth: authSlice.reducer
})

const store = configureStore({
    reducer: rootReducer
})

export default store;