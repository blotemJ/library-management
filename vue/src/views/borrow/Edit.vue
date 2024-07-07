<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px; text-align: center"><i class="el-icon-user"></i> 编辑图书</h2>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleFrom" label-width="100px">
      <el-form-item label="标准书号" prop="bookNo">
        <el-select v-model="form.bookNo" filterable placeholder="请选择" @change="selBook">
          <el-option
              v-for="item in books"
              :key="item.id"
              :label="item.bookNo"
              :value="item.bookNo">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="书名" prop="bookName">
        <el-input v-model="form.bookName" disabled />
      </el-form-item>
      <el-form-item label="图书数量" prop="nums">
        <el-input v-model="form.nums" disabled />
      </el-form-item>
      <el-form-item label="所需积分"  prop="score">
        <el-input v-model="form.score" disabled/>
      </el-form-item>
      <el-form-item label="会员号" prop="userNo">
        <el-select v-model="form.userNo" filterable placeholder="请选择"  @change="selUser">
          <el-option
              v-for="item in users"
              :key="item.id"
              :label="item.username"
              :value="item.username">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="form.userName" disabled/>
      </el-form-item>
      <el-form-item label="用户联系方式" prop="userPhone">
        <el-input v-model="form.userPhone" disabled/>
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
  name: "EditBorrow",
  data(){
    return{
      form: {},
      books:[],
      users:[],
      rules: {
        bookNo: [
          {required: true, message: '请选择书号', trigger: 'blur'},
        ],
        userNo: [
          {required: true, message: '请选择会员号', trigger: 'blur'},
        ],
      }
    }
  },

  created() {

    request.get('/book/list').then(res => {
      this.books = res.data
    })
    request.get('/user/list').then(res => {
      this.users = res.data.filter(v => v.status)
    })

    const url = window.location.href ;
    const lastIndex = url.lastIndexOf("=")
    const id = url.substring(lastIndex+1)
    request.get("/borrow/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save(){
      request.put('/borrow/update',this.form).then(res => {
        if (res.code === '200'){
          this.$notify.success('修改成功')
          this.$router.push("/borrowList")
        }else {
          this.$notify.error('修改失败')
        }
      })
    },
    selBook(){
      const book = this.books.find(v => v.bookNo === this.form.bookNo)
      this.form.bookName = book.name
      this.form.score = book.score
      this.form.nums = book.nums
    },
    selUser(){
      const user = this.users.find(v => v.username === this.form.userNo)
      this.form.userName = user.name
      this.form.userPhone = user.phone
    },
  }
}
</script>

<style scoped>

</style>