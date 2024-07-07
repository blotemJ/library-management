<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px; text-align: center"><i class="el-icon-user"></i>编辑图书类别</h2>
    <el-form :inline="true" :model="form"  rules="rules" ref="ruleFrom" label-width="100px">
      <el-form-item label="类别名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入类别名称"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" placeholder="请输入图书备注"></el-input>
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
  name: "EditCategory",
  data(){
    return{
      form: {},
      rules: {
        name: [
          {required: true, message: '请输入图书类别名称', trigger: 'blur'}
        ]
      }
    }
  },

  created() {
    const url = window.location.href ;
    const lastIndex = url.lastIndexOf("=")
    const id = url.substring(lastIndex+1)
    request.get("/category/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save(){
      request.put('/category/update',this.form).then(res => {
        if (res.code === '200'){
          this.$notify.success('修改成功')
          this.$router.push("/categoryList")
        }else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>