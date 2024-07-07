import axios from "axios"
import router from "@/router";
import Cookies from "js-cookie";
const request = axios.create({
    baseURL: 'http://localhost:9090/api',
    timeout: 50000
})

//request拦截器
//可以在请求前发送对请求做一些处理
//比如统一token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    const adminJSON = Cookies.get('admin')
    if (adminJSON){
        //设置请求头
        config.headers['token'] = JSON.parse(adminJSON).token
    }
    return config
}, error => {
    return Promise.reject(error)
});

//response拦截器
//可以在接口响应后统一处理结果
request.interceptors.response.use(response => {
        let res = response.data;
        //兼容服务端返回字符串数据
        if (typeof res == 'string'){
            res = res ? JSON.parse(res) : res
        }
        if (res.code === '401'){
            router.push('/login')
        }
        return res;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)

export default request