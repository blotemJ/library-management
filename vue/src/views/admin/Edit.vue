<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px; text-align: center"><i class="el-icon-user"></i> 编辑管理员</h2>
    <el-form :inline="true" :model="form" label-width="100px">
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
      <el-button type="primary" @click="save" >提交</el-button>

    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "EditAdmin",
  data(){
    return{
      form: {}
    }
  },

  created() {
    const url = window.location.href ;
    const lastIndex = url.lastIndexOf("=")
    const id = url.substring(lastIndex+1)
    request.get("/admin/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save(){
      request.put('/admin/update',this.form).then(res => {
        if (res.code === '200'){
          this.$notify.success('修改成功')
          this.$router.push("/adminList")
        }else {
          this.$notify.error('修改失败')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>