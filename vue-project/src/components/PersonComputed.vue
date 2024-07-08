<template>
    <div class = 'Person'>
        <h2>当前求和为：{{ sum }}</h2>
        <button @click = "add">sum + 1</button>
        <hr>
        <img v-for="(dog, index) in dogList" :src = "dog" :key="index">
        <br>
        <button @click="getDog">dog again</button>
    </div>
</template>

<script lang = "ts" setup name = Person>
   import useSum from '@/hooks/useSum';
   import useDog from '@/hooks/useDog';
   const {sum, add} = useSum()
   const {dogList, getDog} = useDog()

</script>

<style scoped>
    .Person {
           background-color: #4bb88b;
           box-shadow: 0 0 20px;
           border-radius: 10px;
           padding: 30px;
    }
    button {
        margin: 0 35px;
        font-size: large;
    }
    li {
        font-size: 30px;
    }
    img {
        height: 100px;
        margin-right: 10px;
    }
</style>
   