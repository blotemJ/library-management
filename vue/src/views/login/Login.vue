<template>
  <div>
    <div style="width: 400px; height: 300px; background-color: papayawhip; border-radius: 10px;
    margin: 150px auto; padding: 10px; "  >
      <div style="margin: 30px; text-align: center; font-size: 30px; font-weight: bold; color: cornflowerblue">登 录</div>
      <el-form :model="admin" ref="loginForm" :rules="rules">
        <el-form-item prop="username" style="width: 220px; margin-left: 80px">
          <el-input placeholder="请输入账号" prefix-icon="el-icon-user" size="medium" v-model="admin.username"></el-input>
        </el-form-item>
        <el-form-item prop="password" style="width: 220px; margin-left: 80px ">
          <el-input placeholder="请输入密码"show-password prefix-icon="el-icon-lock" size="medium" v-model="admin.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="margin-left: 150px" size = "medium" type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import request from "@/utils/request";
import Cookies from 'js-cookie'
export default {
  name:'LOGIN',
  data(){
    return{
      loginAdmin: {},
      admin: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min:3, max:10,message: '长度在3-10字符',trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min:3, max:10,message: '长度在3-10字符',trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    login(){
      this.$refs['loginForm'].validate((valid) =>{
        if (valid){
          request.post('/admin/login',this.admin).then(res => {
            if (res.code === '200'){
              this.$notify.success("登录成功")
              if (res.data !== null){
                Cookies.set('admin',JSON.stringify(res.data))
              }
              this.$router.push('/')
            }else{
              this.$notify.error(res.msg)
            }
          })
        }
      })
    },
  }
}
</script>

<style>
.cover{
  width: fit-content;
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  z-index: 1000;
}
</style>