import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);


const router = new Router({
  routes: [
    {
      path: "/",
      name: "login",
      component: () =>
        import("./views/Login.vue")
    },
    {
      path: "/register",
      name: "register",
      component: () =>
        import("./views/Register.vue")
    },
    {
      path: "/index",
      name: "index",
      component: () =>
        import("./views/Index.vue")
    },
    {
      path: "/forget",
      name: "forget",
      component: () =>
        import("./views/Forget.vue")
    }
  ]
});
//路由守卫
router.beforeEach((to,from,next)=>{
  const isLogin = localStorage.Token ? true : false;
  if(to.path == "/"){
    next();
  }else{
    isLogin ? next() : next("/");
  }
})
export default router;
