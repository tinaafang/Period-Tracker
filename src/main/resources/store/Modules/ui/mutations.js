import _ from 'lodash';
import * as path from "path";
export default {
    OPEN_MODAL(state,modalData) {
        state.activeModal = modalData;
    },
    CLOSE_MODAL(state) {
        state.activeModal = {};
    },
    OPEN_ALERT(state,alertData) {
        state.activeAlert = alertData;
    },
    CLOSE_ALERT(state) {
        state.activeAlert = {};
    },
    UPDATE_UI(state,data) {
        const selectedState = _.get(state,_.initial(data.path));
        _.set(selectedState,_.last(data.path),data.data)
    }
}