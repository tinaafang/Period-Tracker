<template>
  <Modal>
      <template #header>
          <h4>Reset Password</h4>
      </template>
      <template #body>
          <div>
          <label>Your Email</label>
          <input
                  class="form-control"
                  v-model="email"
          />
          </div>
          <div v-if="getResetPasswordRequest.token">
              <label>New Password</label>
              <input
                  class="form-control"
                  v-model="password"
              />
          </div>
      </template>
      <template #footer>
          <button class="btn btn-primary" @click="sendResetPasswordEmail">Send link to reset password</button>
          <button v-if="getResetPasswordRequest.token" class="btn btn-primary" @click="resetPassword">Reset password</button>
      </template>
  </Modal>

</template>

<script>
import Modal from "../Modal.vue";
import {mapActions, mapGetters} from "vuex";
import {createHelpers} from "vuex-map-fields";

const { mapFields } = createHelpers({
    getterType: `authentication/getField`,
    mutationType: `authentication/updateField`,
});

export default {
    name: "ResetPasswordModal",
    components: {Modal},
    computed:{
        ...mapGetters("authentication",[
            "getResetPasswordRequest"
        ]),
        ...mapFields([
            "resetPasswordRequest.email",
            "resetPasswordRequest.password",
        ])
    },
    methods:{
        ...mapActions("authentication",[
            "resetPassword",
            "sendResetPasswordEmail"
        ])
    }
}
</script>

<style scoped>

</style>