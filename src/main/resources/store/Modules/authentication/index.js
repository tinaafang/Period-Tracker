import state from "./state";
import actions from "./actions";
import mutations from "./mutations";
import getters from "./getters";
import {getField, updateField} from "vuex-map-fields";

export default {
    namespaced:true,
    state: () => state,
    actions,
    getters: { ...getters, getField },
    mutations: { ...mutations, updateField },
};