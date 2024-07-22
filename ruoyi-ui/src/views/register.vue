<template>
  <div class="register">
    <div class="box">
      <div class="container b-container" id="b-container">
        <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form">
          <h2 class="title">工地信息管理系统</h2>
          <el-form-item prop="username">
            <el-input v-model="registerForm.username" type="text" auto-complete="off" placeholder="账号">
              <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="registerForm.password"
              type="password"
              auto-complete="off"
              placeholder="密码"
              @keyup.enter.native="handleRegister"
            >
              <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
          <el-form-item prop="confirmPassword">
            <el-input
              v-model="registerForm.confirmPassword"
              type="password"
              auto-complete="off"
              placeholder="确认密码"
              @keyup.enter.native="handleRegister"
            >
              <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
          <el-form-item prop="code" v-if="captchaEnabled">
            <el-input
              v-model="registerForm.code"
              auto-complete="off"
              placeholder="验证码"
              style="width: 63%"
              @keyup.enter.native="handleRegister"
            >
              <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
            </el-input>
            <div class="register-code">
              <img :src="codeUrl" @click="getCode" class="register-code-img"/>
            </div>
          </el-form-item>
          <el-form-item >
            <el-button  class="button-css"
              :loading="loading"
              size="medium"
              type="primary"
              @click.native.prevent="handleRegister"
            >
              <span v-if="!loading">注 册</span>
              <span v-else>注 册 中...</span>
            </el-button>
<!--            <div style="float: right;">-->
<!--              <router-link class="link-type" :to="'/login'">使用已有账户登录</router-link>-->
<!--            </div>-->
          </el-form-item>
        </el-form>
      </div>
      <div class="switch" id="switch-cnt">
        <div class="switch_circle"></div>
        <div class="switch_circle switch_circle-t"></div>
        <div class="switch_container is-hidden" id="switch-c2">
          <h2 class="switch_title title" style="letter-spacing: 0;">Welcome Back！</h2>
          <p class="switch_description description">已经有账号了嘛，去登入账号来进入工地信息管理系统吧</p>
          <div style="float: right;">
            <el-button class="button-css">
              <router-link  :to="'/login'">登录</router-link>
              <!--              删掉class="link-type"-->
            </el-button>
          </div>
        </div>
      </div>
    </div>
    <!--  底部  -->
    <div class="el-register-footer">
      <span>Copyright ©2024 工地信息管理系统 All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import { getCodeImg, register } from "@/api/login";

export default {
  name: "Register",
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.registerForm.password !== value) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    return {
      codeUrl: "",
      registerForm: {
        username: "",
        password: "",
        confirmPassword: "",
        code: "",
        uuid: ""
      },
      registerRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" },
          { min: 2, max: 20, message: '用户账号长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" },
          { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, trigger: "blur", message: "请再次输入您的密码" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]
      },
      loading: false,
      captchaEnabled: true
    };
  },
  created() {
    this.getCode();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.registerForm.uuid = res.uuid;
        }
      });
    },
    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.loading = true;
          register(this.registerForm).then(res => {
            const username = this.registerForm.username;
            this.$alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！</font>", '系统提示', {
              dangerouslyUseHTMLString: true,
              type: 'success'
            }).then(() => {
              this.$router.push("/login");
            }).catch(() => {});
          }).catch(() => {
            this.loading = false;
            if (this.captchaEnabled) {
              this.getCode();
            }
          })
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.register {
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
    .register-form {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      width: 100%;
      height: 100%;
    }
  }

}
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
//.register-form {
//  border-radius: 6px;
//  background: #ffffff;
//  width: 400px;
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
.register-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
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
.register-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-register-footer {
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
.register-code-img {
  height: 38px;
}
</style>
