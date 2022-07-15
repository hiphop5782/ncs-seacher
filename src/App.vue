<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-10 offset-md-1">
        <module-search></module-search>
      </div>
    </div>
  </div>
</template>

<script>
import ModuleSearchVue from './components/ModuleSearch.vue'

export default {
  name: 'App',
  components: {
    "module-search": ModuleSearchVue
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
