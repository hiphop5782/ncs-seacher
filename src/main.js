import { createApp } from 'vue'
import App from './App.vue'

import axios from "@/ajax";

import "bootstrap";
// import "bootstrap/dist/css/bootstrap.min.css";
import "bootswatch/dist/spacelab/bootstrap.min.css";

import VueClipboard from 'vue-clipboard2';
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";

const app = createApp(App);

app.config.globalProperties.$http = axios;
app.config.globalProperties.$host = process.env.NODE_ENV === "production" ? "http://api.sysout.co.kr/ncs" : "http://localhost:8765/ncs"

app.use(VueClipboard)
        .use(Toast, {
            position:"bottom-right",
            pauseOnHover:false,
            hideProgressBar:true,
            timeout:2000,
        })
        .mount('#app')
