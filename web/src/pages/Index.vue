<template>
  <q-page class="container">
    <div class="q-pa-md">
      <q-card flat class="my-card bg-grey-1">
        <q-card-section>
          <div class="text-h6">Карта пациента: {{ fullname }}</div>
        </q-card-section>
        <q-separator inset />
        <div class="row q-col-gutter-xs q-pa-md">
          <div class="text-h6">Основная информация</div>
        </div>
        <div class="row q-col-gutter-xs q-px-md">
          <div class="col-3">
            <div class="text-h6">Диагноз</div>
            <div class="text-h6">Дата рождения</div>
            <div class="text-h6">Возраст</div>
            <div class="text-h6">Пол</div>
            <div class="text-h6">Вес</div>
            <div class="text-h6">Рост</div>
          </div>
          <div class="col-3">
            <div class="text-h6">Гипертония</div>
            <div class="text-h6">{{ patient.birthDate }}</div>
            <div class="text-h6">{{ age }}</div>
            <div class="text-h6">{{ sex }}</div>
            <div class="text-h6">{{ patient.weight }}</div>
            <div class="text-h6">{{ patient.height }}</div>
          </div>
          <div class="col">
            <div class="text-h6">Наличие сердечно сосудистых заболеваний у родственников: Отсутсвует</div>
            <div class="text-h6">Хронические заболевания: Отсутсвуют</div>
          </div>
        </div>
        <q-separator inset />
        <q-expansion-item
          expand-separator
          expand-icon="mdi-menu-down"
          label="Нормальные показатели"
        >
        <div class="row q-col-gutter-xs q-pa-md">
          <div class="col-3">
            <div class="text-subtitle1">Систолическое давление</div>
            <div class="text-subtitle1">Диастолическое давление</div>
            <div class="text-subtitle1">Пульс</div>
          </div>
          <div class="col-3">
            <div class="text-subtitle1">{{ patient.optimalBloodPressure.upper }}</div>
            <div class="text-subtitle1">{{ patient.optimalBloodPressure.lower }}</div>
            <div class="text-subtitle1">{{ patient.optimalBloodPressure.lower }}</div>
          </div>
        </div>
        </q-expansion-item>
        <q-separator inset />
        <q-expansion-item
          expand-separator
          expand-icon="mdi-menu-down"
          label="Назначения лечащего врача"
        >
        <div class="row q-col-gutter-xs q-pa-md">
          <div class="col-12">
            <q-list
              separator
              v-for="dest in destinations"
              :key=dest
            >
              <q-item>
                <q-item-section>– {{ dest }};</q-item-section>
              </q-item>
            </q-list>
          </div>
        </div>
        </q-expansion-item>
        <q-separator inset />
        <div class="row q-col-gutter-xs q-pa-md">
          <div class="col-12">
            <LineChart :series="series" :categories="categories" />
          </div>
        </div>
        <q-separator inset />
        <div class="row q-col-gutter-xs q-pa-md">
          <div class="col-12">
            <DataTable :rows="measurements" :nominalBP="patient.optimalBloodPressure" />
          </div>
        </div>
      </q-card>
    </div>
  </q-page>
</template>

<script>

import LineChart from 'components/LineChart.vue'
import DataTable from 'components/DataTable.vue'

export default {
  name: 'PageIndex',
  components: {
    LineChart,
    DataTable,
  },
  data: () => ({
    date: {
      from: '',
      to: ''
    },
    patient: {
      id: 1,
      optimalBloodPressure: {
        lower: 85,
        upper: 110,
        pulse: 73,
      },
      sex: "male",
      birthDate: "1990-09-16",
      name: "Иван",
      lastName: "Иванов",
      middleName: "Иванович",
      height: 183,
      weight: 89
    },
    measurements: [{'timestamp': '2021-05-01', 'lower': 73, 'upper': 149, 'pulse': 93, 'feeling': 7, 'medication': 1, 'complaints': 3}, {'timestamp': '2021-05-02', 'lower': 85, 'upper': 114, 'pulse': 100, 'feeling': 9, 'medication': 1, 'complaints': 2}, {'timestamp': '2021-05-03', 'lower': 81, 'upper': 110, 'pulse': 59, 'feeling': 7, 'medication': 1, 'complaints': 1}, {'timestamp': '2021-05-04', 'lower': 63, 'upper': 116, 'pulse': 73, 'feeling': 8, 'medication': 1, 'complaints': 3}, {'timestamp': '2021-05-05', 'lower': 90, 'upper': 154, 'pulse': 116, 'feeling': 7, 'medication': 1, 'complaints': 0}, {'timestamp': '2021-05-06', 'lower': 94, 'upper': 143, 'pulse': 57, 'feeling': 9, 'medication': 1, 'complaints': 0}, {'timestamp': '2021-05-07', 'lower': 99, 'upper': 114, 'pulse': 93, 'feeling': 7, 'medication': 1, 'complaints': 1}, {'timestamp': '2021-05-08', 'lower': 79, 'upper': 144, 'pulse': 71, 'feeling': 7, 'medication': 1, 'complaints': 7}, {'timestamp': '2021-05-09', 'lower': 78, 'upper': 126, 'pulse': 57, 'feeling': 7, 'medication': 1, 'complaints': 6}, {'timestamp': '2021-05-10', 'lower': 97, 'upper': 140, 'pulse': 97, 'feeling': 9, 'medication': 1, 'complaints': 2}, {'timestamp': '2021-05-11', 'lower': 69, 'upper': 136, 'pulse': 67, 'feeling': 9, 'medication': 1, 'complaints': 2}, {'timestamp': '2021-05-12', 'lower': 81, 'upper': 119, 'pulse': 74, 'feeling': 10, 'medication': 1, 'complaints': 7}, {'timestamp': '2021-05-13', 'lower': 63, 'upper': 114, 'pulse': 81, 'feeling': 7, 'medication': 1, 'complaints': 5}, {'timestamp': '2021-05-14', 'lower': 67, 'upper': 103, 'pulse': 78, 'feeling': 10, 'medication': 1, 'complaints': 7}, {'timestamp': '2021-05-15', 'lower': 97, 'upper': 159, 'pulse': 56, 'feeling': 7, 'medication': 1, 'complaints': 4}, {'timestamp': '2021-05-16', 'lower': 80, 'upper': 112, 'pulse': 113, 'feeling': 7, 'medication': 1, 'complaints': 1}, {'timestamp': '2021-05-17', 'lower': 99, 'upper': 108, 'pulse': 112, 'feeling': 7, 'medication': 1, 'complaints': 5}, {'timestamp': '2021-05-18', 'lower': 66, 'upper': 106, 'pulse': 58, 'feeling': 8, 'medication': 1, 'complaints': 4}, {'timestamp': '2021-05-19', 'lower': 91, 'upper': 117, 'pulse': 77, 'feeling': 7, 'medication': 1, 'complaints': 5}, {'timestamp': '2021-05-20', 'lower': 64, 'upper': 120, 'pulse': 104, 'feeling': 7, 'medication': 1, 'complaints': 3}, {'timestamp': '2021-05-21', 'lower': 67, 'upper': 152, 'pulse': 106, 'feeling': 10, 'medication': 1, 'complaints': 0}, {'timestamp': '2021-05-22', 'lower': 66, 'upper': 140, 'pulse': 114, 'feeling': 10, 'medication': 1, 'complaints': 6}, {'timestamp': '2021-05-23', 'lower': 100, 'upper': 148, 'pulse': 52, 'feeling': 9, 'medication': 1, 'complaints': 6}, {'timestamp': '2021-05-24', 'lower': 87, 'upper': 152, 'pulse': 65, 'feeling': 9, 'medication': 1, 'complaints': 2}, {'timestamp': '2021-05-25', 'lower': 71, 'upper': 119, 'pulse': 85, 'feeling': 9, 'medication': 1, 'complaints': 4}, {'timestamp': '2021-05-26', 'lower': 75, 'upper': 109, 'pulse': 83, 'feeling': 10, 'medication': 1, 'complaints': 0}, {'timestamp': '2021-05-27', 'lower': 61, 'upper': 135, 'pulse': 101, 'feeling': 8, 'medication': 0, 'complaints': 2}, {'timestamp': '2021-05-28', 'lower': 82, 'upper': 116, 'pulse': 88, 'feeling': 10, 'medication': 0, 'complaints': 0}, {'timestamp': '2021-05-29', 'lower': 71, 'upper': 143, 'pulse': 95, 'feeling': 9, 'medication': 0, 'complaints': 3}, {'timestamp': '2021-05-30', 'lower': 69, 'upper': 142, 'pulse': 103, 'feeling': 7, 'medication': 0, 'complaints': 2}],
    destinations: [
      "отказ от курения",
      "низкосолевая диета",
      "регулярные физические упражнения на свежем воздухе",
      "снижение массы тела не менее чем на 5 кг",
      "отлично провести отпуск",
      "сменить работу",
      "принять участие в хакатоне"
    ]
  }),
  computed: {
    series() {
      const series = [
        {name: "Систолическое", data: []},
        {name: "Диастолическое", data: []},
        {name: "Пульс", data: []},
      ]
      this.measurements.forEach(item => {
        series[0].data.push(item.lower)
        series[1].data.push(item.upper)
        series[2].data.push(item.pulse)
      })
      return series
    },
    categories () {
      return this.measurements.map(item => item.timestamp)
    },
    fullname() {
      return `${this.patient.lastName} ${this.patient.name} ${this.patient.middleName}`
    },
    age() {
      const now = new Date()
      const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
      const dob =  new Date(this.patient.birthDate)
      const dobnow = new Date(today.getFullYear(), dob.getMonth(), dob.getDate())
      let age = today.getFullYear() - dob.getFullYear()
      return today < dobnow ? age - 1 : age
    },
    sex() {
      return this.patient.sex === "male" ? "Мужской" : "Женский"
    },
  },
}
</script>
