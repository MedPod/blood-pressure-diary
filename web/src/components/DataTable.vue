<template>
  <div class="q-pa-md">
    <q-table
      color="primary"
      flat

      title="Данные по гипертензии"
      :rows="rows"
      :columns="columns"
      row-key="name"
      hide-bottom
      :rows-per-page-options=[0]
    >
      <template v-slot:body-cell-lower="props">
        <q-td :props="props">
          <div v-if="isNormalBP(props.value, 'lower')">
            <q-badge color="red-12" :label="props.value" />
          </div>
          <div v-else>
            {{ props.value }}
          </div>
        </q-td>
      </template>
      <template v-slot:body-cell-upper="props">
        <q-td :props="props">
          <div v-if="isNormalBP(props.value, 'upper')">
            <q-badge color="red-12" :label="props.value" />
          </div>
          <div v-else>
            {{ props.value }}
          </div>
        </q-td>
      </template>
      <template v-slot:body-cell-pulse="props">
        <q-td :props="props">
          <div v-if="isNormalBP(props.value, 'pulse')">
            <q-badge color="red-12" :label="props.value" />
          </div>
          <div v-else>
            {{ props.value }}
          </div>
        </q-td>
      </template>
    </q-table>
  </div>
</template>

<script>
const cols = [
  {
    "name": "timestamp",
    "label": "Дата снятия показаний",
    "align": "left",
    "field": "timestamp",
    "sortable": true
  },
  {
    "name": "lower",
    "label": "Систолическое",
    "align": "left",
    "field": "lower",
    "sortable": true
  },
  {
    "name": "upper",
    "label": "Диастолическое",
    "align": "left",
    "field": "upper",
    "sortable": true
  },
  {
    "name": "pulse",
    "label": "Пульс",
    "align": "left",
    "field": "pulse",
    "sortable": true
  },
  {
    "name": "complaints",
    "label": "Жалобы",
    "align": "left",
    "field": "complaints",
    "sortable": true
  },
  {
    "name": "medication",
    "label": "Прием лекарств",
    "align": "left",
    "field": "medication",
    "sortable": true
  },
  {
    "name": "feeling",
    "label": "Самочувствие",
    "align": "left",
    "field": "feeling",
    "sortable": true
  },
]
export default {
  props: {
    rows: {
      type: Array,
      default: ()=> ([])
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
    columns: cols
  }),
  computed: {
    rows_() {
      return this.rows.forEach(row => {
        console.log(row)
      })
    }
  },
  methods: {
    isNormalBP(currentBP, BPType) {
      return currentBP > (this.nominalBP[BPType] + 19) ? true : false
    }
  }
}
</script>
