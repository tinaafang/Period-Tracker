const state = {
    items: [],
};

const getters = {
    cartItems: (state) => state.items,
};

const mutations = {
    addToCart: (state, product) => {
        state.items.push(product);
    },
};

const actions = {
    addToCart: ({ commit }, product) => {
        commit('addToCart', product);
    },
};

export default {
    state,
    getters,
    mutations,
    actions,
};