import axios from 'axios';
import { reactive, ref } from 'vue';
export default function() {
    let sum = ref(0)

    function add() {
        sum.value +=1
    }
    return {sum, add}
}

