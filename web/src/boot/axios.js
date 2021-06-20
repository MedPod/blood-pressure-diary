import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import { boot } from "quasar/wrappers";

export default boot(({ app }) => {
  const axios_ = axios.create({
    baseURL: process.env.API_URL,
  });
  app.use(VueAxios, axios_);
});
