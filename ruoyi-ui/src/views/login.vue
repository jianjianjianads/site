<template>
  <div class="login">
    <div class="box">
      <div class="container b-container" id="b-container">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
          <h2 class="title">工地信息管理系统</h2>
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              type="text"
              auto-complete="off"
              placeholder="账号"
            >
              <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              auto-complete="off"
              placeholder="密码"
              @keyup.enter.native="handleLogin"
            >
              <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
          <el-form-item prop="code" v-if="captchaEnabled">
            <el-input
              v-model="loginForm.code"
              auto-complete="off"
              placeholder="验证码"
              style="width: 63%"
              @keyup.enter.native="handleLogin"
            >
              <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
            </el-input>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" class="login-code-img"/>
            </div>
          </el-form-item>
          <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
          <el-form-item >
<!--            // style="width:100%;"去掉了-->
            <el-button class="button-css"
              :loading="loading"
              size="medium"
              type="primary"
              @click.native.prevent="handleLogin"
            >
              <span v-if="!loading">登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
<!--            <div style="float: right;" v-if="register">-->
<!--              <router-link class="link-type" :to="'/register'">立即注册</router-link>-->
<!--            </div>-->
          </el-form-item>
        </el-form>
      </div>
      <div class="switch" id="switch-cnt">
        <div class="switch_circle"></div>
        <div class="switch_circle switch_circle-t"></div>
        <div class="switch_container is-hidden" id="switch-c2">
          <h2 class="switch_title title" style="letter-spacing: 0;">Hello Friend！</h2>
          <p class="switch_description description">去注册一个账号，成为工地信息管理系统的用户。</p>
          <div style="float: right;" v-if="register">
            <el-button class="button-css">
              <router-link  :to="'/register'">注册</router-link>
<!--              删掉class="link-type"-->
            </el-button>
          </div>
        </div>
      </div>
    </div>

    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright ©2024 工地信息管理系统 All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import { getCodeImg } from "@/api/login";
// @当前根路径
import Cookies from "js-cookie";
import { encrypt, decrypt } from '@/utils/jsencrypt'

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" }
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]
      },
      loading: false,
      // 验证码开关
      captchaEnabled: true,
      // 注册开关
      register: true,
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
    this.getCookie();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          //验证码对应key值
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || "/" }).catch(()=>{});//路由重定向
          }).catch(() => {
            this.loading = false;
            if (this.captchaEnabled) {
              this.getCode();
            }
          });
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
  .box{
    position: relative;
    width: 1000px;
    min-width: 1000px;
    min-height: 600px;
    height: 600px;
    padding: 25px;
    background-color: #ecf0f3;
    box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
    border-radius: 12px;
    overflow: hidden;
    .container {
      display: flex;
      justify-content: center;
      align-items: center;
      position: absolute;
      top: 0;
      //width: 600px;
      width: 66%;
      height: 100%;
      padding: 25px;
      background-color: #ecf0f3;
      transition: 1.25s;
      .el-input{
          width: 350px;
          height: 40px;
          margin: 4px 0;
          padding-left: 25px;
          font-size: 13px;
          letter-spacing: 0.15px;
          border: none;
          outline: none;
          background-color: #ecf0f3;
          transition: 0.25s ease;
          border-radius: 8px;
          box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
      }
    }
    .switch {
      display: flex;
      justify-content: center;
      align-items: center;
      position: absolute;
      top: 0;
      left: 66%;
      height: 100%;
      width: 400px;
      width: 34%;
      padding: 50px;
      z-index: 200;
      transition: 1.25s;
      background-color: #ecf0f3;
      overflow: hidden;
      box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #d1d9e6;
    }
    .switch_circle {
      position: absolute;
      width: 500px;
      height: 500px;
      border-radius: 50%;
      background-color: #ecf0f3;
      box-shadow: inset 8px 8px 12px #b8bec7, inset -8px -8px 12px #fff;
      bottom: -60%;
      left: -60%;
      transition: 1.25s;
    }
    .switch_circle-t {
      top: -30%;
      left: 60%;
      width: 300px;
      height: 300px;
    }
    .switch_container {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      position: absolute;
      width: 400px;
      padding: 50px 55px;
      transition: 1.25s;
    }
    .switch_button {
      cursor: pointer;
    }
    .login-form {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      width: 100%;
      height: 100%;
    }
  }


}
//.title {
//  margin: 0px auto 30px auto;
//  text-align: center;
//  color: #707070;
//}
.title {
  font-size: 34px;
  font-weight: 700;
  line-height: 3;
  color: #181818;
  letter-spacing: 10px;
}
.h2{
  font-size: 34px;
  font-weight: 700;
  line-height: 3;
  color: #181818;
  letter-spacing: 10px;
}
.button-css {
  width: 150px;
  height: 50px;
  align-items: center;
  border-radius: 25px;
  margin-top: 50px;
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1.15px;
  background-color: #4B70E2;
  color: #f9f9f9;
  box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  border: none;
  outline: none;
}

//.login-form {
//  border-radius: 6px;
//  background: #ffffff;
//  width: 360px;
//  height: 360px;
//  padding: 25px 25px 5px 25px;
//  .el-input {
//    height: 38px;
//    input {
//      height: 38px;
//    }
//  }
//  .input-icon {
//    height: 39px;
//    width: 14px;
//    margin-left: 2px;
//  }
//}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.login-code-img {
  height: 38px;
}
</style>
