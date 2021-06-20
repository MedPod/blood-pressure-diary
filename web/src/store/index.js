import { store } from "quasar/wrappers";
import { createStore } from "vuex";

import example from "./patient";

export default store(function () {
  const Store = createStore({
    modules: {
      example,
    },
    strict: process.env.DEBUGGING,
  });

  return Store;
});
