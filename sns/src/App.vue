<template>
  <div id="app">
      <transition :name="transitionName">
          <router-view class="Router" ></router-view>
      </transition>
  </div>
</template>
<script>
import jwt_decode from 'jwt-decode';
export default {
  data() {
      return {
        transitionName: 'slide-right', //初始过渡动画方向
      }
    },
    watch: {
      $route(to, from) {
        // 切换动画
        let isBack = this.$router.isBack // 监听路由变化时的状态为前进还是后退
        if (isBack) {
          this.transitionName = 'slide-left'
        } else {
          this.transitionName = 'slide-right'
        }
        this.$router.isBack = false
      }
    },
    created(){
      if(localStorage.Token){
          const decoded = jwt_decode(localStorage.Token)
          //toke存储到vuex中
          this.$store.dispatch("setIsAutnenticated",!this.isEmpty(decoded))
          this.$store.dispatch("setUser", decoded);
      }
    },
    methods: {
        isEmpty(value) {
            return (
                value === undefined ||
                value === null ||
                (typeof value === "object" && Object.keys(value).length === 0) ||
                (typeof value === "string" && value.trim().length === 0)
            );
        }
    }
}
</script>
<style scoped>
.Router {
  position: absolute;
  height: 100%;
  transition: all .377s ease;
  will-change: transform;
  top: 0;
  backface-visibility: hidden;
  perspective: 1000;
}
.slide-left-enter,
.slide-right-leave-active {
  opacity: 0;
  transform: translate3d(-100%, 0, 0);
}

.slide-left-leave-active,
.slide-right-enter {
  opacity: 0;
  transform: translate3d(100%, 0 ,0);
}
</style>
