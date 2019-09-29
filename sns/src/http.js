import axios from 'axios'
// import router from './router/index'


// 请求拦截  
axios.interceptors.request.use(config => {
    // 加载
    // if(localStorage.eleToken){
    //     //设置统一header
    //     config.headers.Authorization = localStorage.eleToken;
    // }
    return config
}, error => {
    return Promise.reject(error)
});
//响应拦截 
// 响应拦截  401 token过期处理
axios.interceptors.response.use(response => {
    return response
}, error => {
    // 错误提醒
    // const { status } = error.response
    // if(status == 401){
    //     Message.error("token失效，请重新登陆")
    //     localStorage.removeItem("eleToken")
    //     router.push('/login');
    // }
    return Promise.reject(error)
})
export default axios;