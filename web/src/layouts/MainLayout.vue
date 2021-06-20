<template>
  <q-layout view="hHh Lpr lFf" class="bg-grey-3">
    <q-header elevated class="bg-grey-3 text-primary">
      <q-toolbar>
        <q-toolbar-title class="text-uppercase text-grey">
          <router-link
            to="/"
            custom
            v-slot="{ navigate }"
          >
            <span class="cursor-pointer" @click="navigate">
              <span >med</span>
              <span class="text-weight-light">p</span>
              <q-icon name="img:favicon.ico" class="q-mb-xs" />
              <span class="text-weight-light">d</span>
            </span>
          </router-link>
        </q-toolbar-title>
        <q-input
          dense
          v-model="searchPatient"
          input-class="text-right"
          class="q-ml-md"
          rounded
          outlined
          @keypress.enter="getPatient"
          mask="###-###-###-##"
          unmasked-value
          label="Поиск пациента по СНИЛС"
          style="width: 240px"
        >
          <template v-slot:append>
            <q-icon v-if="searchPatient === ''" name="mdi-magnify" />
            <q-icon v-else name="mdi-close" class="cursor-pointer" @click="searchPatient = ''" />
          </template>
        </q-input>
        <q-btn-dropdown
          split
          flat
          color="grey"
          label="А.Я.Врач"
          dropdown-icon="mdi-menu-down"
        >
          <q-list bordered class="bg-grey-3">
            <q-item
              clickable
              tag="div"
            >
              <q-item-section avatar>
                <q-icon name="mdi-logout" />
              </q-item-section>
              <q-item-section>
                <q-item-label
                  class="text-weight-bold"
                >Выйти</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>

      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view v-slot="{ Component }">
        <component :is="Component" />
      </router-view>
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "Layout",
  components: {
  },
  data: () => ({
    searchPatient: "",
  }),
  computed: {
    searchInputIsValid() {
      return this.searchPatient.length === 11
    }
  },
  methods: {
    toggleLeftDrawer() {
      this.leftDrawerOpen = !this.leftDrawerOpen
    },
    async getPatient(e) {
      if (this.searchInputIsValid) {
        // this.$router.push("/patient/" + this.searchPatient)
      }
    }
  }
}
</script>
