import axios from 'axios';
import { reactive, ref, onMounted, computed } from 'vue';
export default function() {
    let sum = ref(0)

    function add() {
        sum.value +=1
    }
    onMounted(() => {
        add()
    })
    return {sum, add}
}

