<template>
  <div id="app">
    <navbar />
    <router-view/>
  </div>
</template>

<script>
import Navbar from './components/navigator.vue'

export default {
  name: 'App',
  components: {
    Navbar
  }
}
</script>
