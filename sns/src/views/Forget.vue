<template>
     <div class="homepage-hero-module">
        <div class="video-container">
            <div :style="fixStyle" class="filter"></div>
            <video :style="fixStyle" muted autoplay="autoplay" loop="loop" class="fillWidth" v-on:canplay="canplay">
                <source src="../assets/matukad.mp4"  type="video/mp4" />
                浏览器不支持 video 标签，建议升级浏览器。
            </video>
            <div class="poster hidden" v-if="!vedioCanPlay">
                <img :style="fixStyle" src="../assets/matukad.jpg" alt="">
            </div>
        </div>
        <div class="forget">
            <span>Reset</span>
            <div class="forget-form">
                <div class="phone">
                    <input type="text" placeholder="输入11位手机号" v-model="phone">
                    <button @click="getCode">{{text}}</button>
                </div>
                <div class="code">
                    <input type="text" placeholder="验证码" v-model="code">
                </div>
                 <div class="pass">
                    <input type="password" placeholder="输入新密码" v-model="pass">
                </div>
                <button class="forget-btn" @click="reset">确认</button>
                <p class="ope">
                    <router-link to='/register'>
                        <span>立即注册</span>
                    </router-link>
                    <span>|</span>
                    <router-link to='/'>
                        <span>立即登录</span>
                    </router-link>
                </p>
            </div>
        </div>
    </div> 
</template>
<script>
import { Toast } from 'vant';
export default {
    name: 'forget',
    data() {
      return {
        vedioCanPlay: false,
        fixStyle: '',
        phone:'',
        code:'',
        pass:'',
        text:'获取验证码',
        verCode:''
      }
    },
    methods: {
      canplay() {
        this.vedioCanPlay = true
      },
      getCode(){
        if(this.verCode == ""){
         this.$axios.get(`api/user/validates?phone=${this.phone}`).then(res=>{ 
            this.verCode = res.data;
            Toast.success('已成功发送验证码请注意查收');
            this.text = '已发送';
          }).catch(err => console.log(err));
        }else{
          Toast.success('已成功发送验证码请注意查收');
          return;
        }
      },
        reset(){
            if(this.phone.trim()!=""){
                var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
                if(!myreg.test(this.phone)){
                return Toast.fail('请输入正确的手机号');
                }
            }else{
                return Toast.fail('请输入手机号');
            }
        if(this.code.trim()==''){
            return Toast.fail('请输入手机验证码');
            }else{
            if(this.code!=this.verCode){
                return Toast.fail('验证码错误');
            }
            }
            if(this.pass.trim()==''){
            return Toast.fail('请输入新密码');
            }
        let data = {
           phone_number:this.phone,
           password:this.pass,
         }
         this.$axios.patch('api/user/findpass',data).then(res=>{
                const toast = Toast.loading({
                  duration: 0,       // 持续展示 toast
                  forbidClick: true, // 禁用背景点击
                  loadingType: 'spinner',
                  message: '跳转到登录页面'
                });

                let second = 3;
                const timer = setInterval(() => {
                  second--;
                  if (second) {
                    toast.message = `跳转到登录页面`;
                  } else {
                    clearInterval(timer);
                    Toast.clear();
                    this.$router.push({path:'/'});  
                  }
                }, 1000);
              
         }).catch(err => console.log(err));
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
  input::-webkit-input-placeholder {
    color: #fff;
}
.forget{
    width: 8rem;
    height: 8.6rem;
    color:#fff;
    position: absolute;
    z-index: 999;
    top:50%;
    left: 50%;
    -webkit-font-smoothing:antialiased;
    transform: translate(-50%,-50%);
}
.forget > span{
    font-size: 36px;
    width: 100%;
    text-align: center;
    display: inline-block
}
.forget-form{
    margin-top: 30px;
}
.forget-form input{
    width:100%;
    height: 35px;
    border-radius: 25px;
    background: transparent;
    outline: none;
    border:1px solid #fff;
    color: #fff;
    text-indent: 14px;
}
.forget-form div{
    margin-bottom: 15px;
}
.forget-btn{
    width:100%;
    height: 35px;
    border-radius: 25px;
    background: transparent;
    outline: none;
    border:1px solid #fff;
    color: #fff;
}
.phone{
    position: relative;
}
.phone > button{
    position: absolute;
    right: 5px;
    top: 2px;
    background: transparent;
    color: #fff;
    border:none;
    height: 33px;
    outline: none;
    cursor: pointer;
    line-height: 33px;
}
.ope{
    margin-top: 25px;
    text-align: center
}
.ope a{
    color: #fff;
}
.ope a:nth-child(1) {
    margin-right: 10px;
}
.ope a:nth-child(3) {
    margin-left: 10px;
}
</style>