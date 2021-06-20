import VueApexCharts from "vue3-apexcharts";

import { boot } from "quasar/wrappers";

export default boot(({ app }) => {
  app.use(VueApexCharts);
  console.log(VueApexCharts);
  console.log(VueApexCharts.chart);
});
