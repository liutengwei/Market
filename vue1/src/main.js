import { createApp } from 'vue';
import App from './App.vue';
import router from './router/router.js';
import store from './store';
import axios from "axios";


const app = createApp(App);
axios.defaults.baseURL = process.env.NODE_ENV === 'development' ? 'http://localhost:8080' : '/';
axios.defaults.withCredentials = true;

app.use(router);
app.use(store);
app.mount('#app');//将整个应用挂载到页面上


