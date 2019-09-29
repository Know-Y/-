<template>
  <div class="homepage-hero-module">
        <div class="video-container">
            <div :style="fixStyle" class="filter"></div>
            <video :style="fixStyle" muted autoplay="autoplay" loop="loop" class="fillWidth" v-on:canplay="canplay">
                <source src="../assets/coverr1.mp4"  type="video/mp4" />
                浏览器不支持 video 标签，建议升级浏览器。
            </video>
            <div class="poster hidden" v-if="!vedioCanPlay">
                <img :style="fixStyle" src="../assets/coverr1.jpg" alt="">
            </div>
        </div>
        <div class="login">
            <span>Welcome</span>
            <div class="login-form">
                <div class="account">
                    <input type="text" placeholder="手机号" v-model="user">
                </div>
                <div class="password">
                    <input type="password" placeholder="密码" v-model="pass">
                </div>
                <button class="login-btn" @click="login">立即登录</button>
                <p class="login-ope">
                    <router-link to='/register'>
                        <span>立即注册</span>
                    </router-link>
                    <span>|</span>
                    <router-link to='/forget'>
                        <span>忘记密码？</span>
                    </router-link>
                </p>
            </div>
        </div>
    </div>
</template>
<script>
import jwt_decode from 'jwt-decode';
import { Toast } from 'vant';
export default {
    name: 'login',
    data() {
      return {
        vedioCanPlay: false,
        fixStyle: '',
        user:'',
        pass:'',
      }
    },
    methods: {
      canplay() {
        this.vedioCanPlay = true
      },
      login(){
          if(this.user.trim()==""){
              return Toast.fail('请输入登录手机号'); 
          }
          if(this.user.trim()==""){
              return Toast.fail('请输入密码'); 
          }
          this.$axios.get(`api/user/login?user=${this.user}&password=${this.pass}`).then(res=>{ 
              if(res.data.length!=0){
                const token = res.data;
                localStorage.setItem('Token',token);
                const decoded = jwt_decode(token);
                this.$store.dispatch("setIsAutnenticated",!this.isEmpty(decoded))
                this.$store.dispatch("setUser", decoded);
                this.$router.push({path: '/index'})
              }else{
                  Toast.fail('该账号不存在'); 
              }
          });
      },
      isEmpty(value) {
            return (
                value === undefined ||
                value === null ||
                (typeof value === "object" && Object.keys(value).length === 0) ||
                (typeof value === "string" && value.trim().length === 0)
            );
        }
    },
    mounted() {
      window.onresize = () => {
        const windowWidth = document.body.clientWidth
        const windowHeight = document.body.clientHeight
        const windowAspectRatio = windowHeight / windowWidth
        let videoWidth
        let videoHeight
        if (windowAspectRatio < 0.5625) {
          videoWidth = windowWidth
          videoHeight = videoWidth * 0.5625
          this.fixStyle = {
            height: windowWidth * 0.5625 + 'px',
            width: windowWidth + 'px',
            'margin-bottom': (windowHeight - videoHeight) / 2 + 'px',
            'margin-left': 'initial'
          }
        } else {
          videoHeight = windowHeight
          videoWidth = videoHeight / 0.5625
          this.fixStyle = {
            height: windowHeight + 'px',
            width: windowHeight / 0.5625 + 'px',
            'margin-left': (windowWidth - videoWidth) / 2 + 'px',
            'margin-bottom': 'initial'
          }
        }
      }
      window.onresize()
    }
}
</script>
<style scoped>
 .homepage-hero-module,
  .video-container {
    position: relative;
    height: 100vh;
    overflow: hidden;
  }
 
  .video-container .poster img,
  .video-container video {
    z-index: 0;
    position: absolute;
  }
 
  .video-container .filter {
    z-index: 1;
    position: absolute;
    background: rgba(0, 0, 0, 0.1);
  }
.login{
    width: 8rem;
    height: 6.6rem;
    color:#fff;
    position: absolute;
    z-index: 999;
    top:50%;
    left: 50%;
    -webkit-font-smoothing:antialiased;
    transform: translate(-50%,-50%);
}
.login > span{
    font-size: 36px;
    width: 100%;
    text-align: center;
    display: inline-block
}
.login-form{
    margin-top: 30px;
}
.login-form input{
    width:100%;
    height: 35px;
    border-radius: 25px;
    background: transparent;
    outline: none;
    border:1px solid #fff;
    color: #fff;
    text-indent: 14px;
}
.login-form div{
    margin-bottom: 15px;
}
input::-webkit-input-placeholder {
    color: #fff;
}
.login-btn{
    width:100%;
    height: 35px;
    border-radius: 25px;
    background: transparent;
    outline: none;
    border:1px solid #fff;
    color: #fff;
}
.login-ope{
    margin-top: 25px;
    text-align: center
}
.login-ope a{
    color: #fff;
}
.login-ope a:nth-child(1) {
    margin-right: 10px;
}
.login-ope a:nth-child(3) {
    margin-left: 10px;
}
</style>