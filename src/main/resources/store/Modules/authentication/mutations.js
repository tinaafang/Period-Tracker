export default {
    SEND_RESET_PASSWORD_EMAIL_SUCCESS(state,token) {
        state.resetPasswordRequest.token = token;
    },
    SEND_ACCOUNT_REGISTRATION_EMAIL_SUCCESS(state,token) {
        state.registerRequest.token = token;
    }
}