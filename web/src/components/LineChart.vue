<template>
    <div id="chart">
      <apexchart type="line" height="350" :options="options" :series="series"></apexchart>
    </div>
    <div class="row q-col-gutter-xs q-pa-md">
      <div class="text-h6">Интерпретация графика и расчет данных</div>
    </div>
    <div class="row q-col-gutter-xs q-px-md">
      <div class="col-4">
        <div class="text-h6">Максимальные значения</div>
        <div class="text-h6">Минимальные значения</div>
        <div class="text-h6">Количество превышений номинальных показателей более чем на 20 пунктов</div>
      </div>
      <div class="col">
        <div class="text-h6">Диастолическое: {{ maxdia }}, Систолическое: {{ maxsys }}</div>
        <div class="text-h6">Диастолическое: {{ mindia }}, Систолическое: {{ minsys }}</div>
        <div class="text-h6">Диастолическое: {{ overdia }}, Систолическое: {{ oversys }}</div>
      </div>
    </div>
</template>

<script>
import {apex_locale} from "/src/utils/locale.js"

export default {
  props: {
    series: {
      type: Array,
      default: () => ([])
    },
    categories: {
      type: Array,
      default: () => ([])
    },
    nominalBP: {
      type: Object,
      default: () => ({
        upper: 120,
        lower: 80,
        pulse: 80
      }),
    }
  },
  data: () => ({
    chartOptions: {
      title: {
        text: "График показателей артериального давления"
      },
      chart: {
        locales: [apex_locale],
        defaultLocale: 'ru',
        height: 350,
        type: 'line',
        toolbar: {
          show: true,
          offsetY: -10,
          tools: {
            zoom: true,
            zoomin: true,
            zoomout: true,
            reset: true,
            download: false,
          }
        },
        zoom: {
          autoScaleYaxis: true
        },
      },
      colors: ['#77B6EA', '#ef534f', '#9e9e9e'],
      dataLabels: {
        enabled: true,
      },
      stroke: {
        curve: 'smooth'
      },
      grid: {
        borderColor: '#e7e7e7',
        row: {
          colors: ['#f3f3f3'],
          opacity: 0.5
        },
      },
      markers: {
        size: 1
      },
      xaxis: {
        type: 'datetime',
        labels: {
          format: 'dd.MM',
        },
      },
      yaxis: {
        title: {
          text: 'Артериальное давление'
        },
        min: 50,
        max: 170
      },
      legend: {
        position: 'top',
        horizontalAlign: 'right',
        floating: true,
        offsetY: -25,
        offsetX: -5
      },
      annotations: {
        yaxis: [{
          y: 120,
          borderColor: '#FF0000',
          label: {
            borderColor: '#333',
            style: {
              fontSize: '10px',
              color: '#333',
              background: '#FEB019',
            },
            // text: 'Систолическое',
          }
        }, {
          y: 80,
          borderColor: '#004DFF',
          fillColor: '#FEB019',
          opacity: 0.2,
          label: {
            borderColor: '#333',
            style: {
              fontSize: '10px',
              color: '#333',
              background: '#FEB019',
            },
            // text: 'Диастолическое',
          }
        }],
      },
    }
  }),
  computed: {
    mindia() {
      return Math.min(...Object.values(this.series[1].data).filter(a => !!a))
    },
    maxdia() {
      return Math.max(...Object.values(this.series[1].data))
    },
    overdia() {
      return Object.values(this.series[1].data).filter(upper => upper > (this.nominalBP.upper + 20)).length
    },
    minsys() {
      return Math.min(...Object.values(this.series[0].data).filter(a => !!a))
    },
    maxsys() {
      return Math.max(...Object.values(this.series[0].data))
    },
    oversys() {
      return Object.values(this.series[0].data).filter(lower => lower > (this.nominalBP.lower + 20)).length
    },
    options() {
      const opt = this.chartOptions
      opt.xaxis.categories = this.categories
      return opt
    }
  }
}
</script>
