<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px; text-align: center"><i class="el-icon-user"></i>新增借书记录</h2>
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
        <el-input v-model="form.bookName" disabled placeholder="请输入描述"/>
      </el-form-item>
      <el-form-item label="图书数量" prop="nums">
        <el-input v-model="form.nums" disabled placeholder="请输入描述"/>
      </el-form-item>
      <el-form-item label="所需积分"  prop="score">
        <el-input v-model="form.score" disabled/>
      </el-form-item>
      <br>
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
      <el-form-item label="账户积分" prop="account">
        <el-input v-model="form.account" disabled/>
      </el-form-item>
      <el-form-item label="用户联系方式" prop="userPhone">
        <el-input v-model="form.userPhone" disabled/>
      </el-form-item>
      <el-form-item label="借出天数" prop="days">

        <el-input-number v-model="form.days" :min="1" :max="30" label="借出天数"/>
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
  name: "AddBorrow",
  data(){

    return{
      form: {days: 1},
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
  },
  methods: {
    save(){
      this.$refs['ruleFrom'].validate((valid) => {
        if (valid){
          request.post('/borrow/save',this.form).then(res => {
            if (res.code === '200'){
              this.$notify.success('新增成功')
              this.$router.push("/borrowList")
            }else {
              this.$notify.error(res.msg)
            }
          })
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
      this.form.account = user.account
      this.form.userPhone = user.phone
    },
  }
}
</script>

<style scoped>

</style>