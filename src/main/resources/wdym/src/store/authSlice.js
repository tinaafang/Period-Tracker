import { createSlice } from '@reduxjs/toolkit'
import _ from "lodash"

export const authSlice = createSlice({
    name: 'auth',
    initialState: {
        register: {
            email: "",
            password:  ""
        },
        login: null,
        forgotPassword: null,
        resetPassword: null
    },
    reducers: {
        updateState: (state, action) => {
            const stateValue = _.get(state, _.initial(action.payload.path));
            stateValue[_.last(action.payload.path)] = action.payload.data;
        },
        loginSuccess: state => {
            // Redux Toolkit allows us to write "mutating" logic in reducers. It
            // doesn't actually mutate the state because it uses the immer library,
            // which detects changes to a "draft state" and produces a brand new
            // immutable state based off those changes
            state.value += 1
        },
        registerSuccess: state => {
            state.value -= 1
        },
    }
})

export const {
    updateState,
    loginSuccess,
    registerSuccess
} = authSlice.actions
// in the slice file. For example: `useSelector((state) => state.counter.value)`
export const registerState = state => state.auth.register;
export const loginState = state => state.auth.register;
export const forgotPasswordState = state => state.auth.register;
export const resetPasswordState = state => state.auth.register;
export default authSlice