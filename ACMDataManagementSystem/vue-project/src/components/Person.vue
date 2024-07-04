<template>
    <div class = 'Person'>
        姓: <input type="text" v-model = "firstName"> <br>
        名: <input type="text" v-model = "lastName"> <br>
        全名: <span>{{fullName}}</span> <br>
        <button @click="changeFullName">改全名</button>
    </div>
</template>
   
<script lang = "ts">
       export default {
           name : 'Person',
       }
</script>

<script lang = "ts" setup>
        import {ref, computed} from 'vue'
        let firstName = ref('赵')
        let lastName = ref('飞')
        let fullName = computed({
            get() {
                return firstName.value.slice(0, 1).toUpperCase() + firstName.value.slice(1) + '-' + lastName.value;
            },
            set(val) {
                const[firstTempName, lastTempName] = val.split('-')
                firstName.value = firstTempName
                lastName.value = lastTempName
            }
            
        })
        function changeFullName() {
            fullName.value = 'li-si'
        }

</script>

<style>
    .person {
           background-color: #9a5757;
           box-shadow: 0 0 10px;
           border-radius: 10px;
           padding: 20px;
    }
    button {
        margin: 0 5px;
    }
    li {
        font-size: 30px;
    }
</style>
   