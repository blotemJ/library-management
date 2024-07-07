<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px; text-align: center"><i class="el-icon-user"></i>新增管理员</h2>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleFrom" label-width="100px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系电话"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center">
      <el-button type="primary" @click="save">提交</el-button>

    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "AddAdmin",
  data(){
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('号码不能为空'));
      }
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)){
        callback(new Error('请输入合法的手机号'));
      }
      callback()
    };

    return{
      form:{},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min:3, max:10,message: '长度在3-10字符',trigger: 'blur'}
        ],
        phone: [
          {validator:checkPhone, trigger: 'blur'},
        ]
      }
    }
  },
  methods: {
    save(){
      this.$refs['ruleFrom'].validate((valid) => {
        if (valid){
          request.post('/admin/save',this.form).then(res => {
            if (res.code === '200'){
              this.$notify.success('新增成功')
              this.$router.push("/adminList")
            }else {
              this.$notify.error('新增失败')
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>