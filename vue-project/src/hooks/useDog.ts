import axios from 'axios';
import { reactive, onMounted } from 'vue';

export default function () {
    let dogList = reactive([
        'https://images.dog.ceo/breeds/pembroke/n02113023_4373.jpg'
    ])
    
    async function getDog() {
        let result =await axios.get('https://dog.ceo/api/breed/pembroke/images/random')
        dogList.push(result.data.message)
    }
    onMounted(()=> {
        getDog()
    })
    return {dogList, getDog}
} 



