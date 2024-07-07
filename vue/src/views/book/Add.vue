<template>
  <div style="width: 80%">
    <h2 style="margin-bottom: 30px; text-align: center"><i class="el-icon-user"></i> 新增图书</h2>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleFrom" label-width="100px">
      <el-form-item label="书名" prop="name">
        <el-input v-model="form.name" placeholder="请输入书名"/>
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input type="textarea" style="width: 400px" v-model="form.description" placeholder="请输入描述"/>
      </el-form-item>
      <el-form-item label="出版日期" prop="publishDate">
        <el-date-picker
            v-model="form.publishDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出版日期"/>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="form.author" placeholder="请输入作者"/>
      </el-form-item>
      <el-form-item label="出版社" prop="publisher">
        <el-input v-model="form.publisher" placeholder="请输入出版社"/>
      </el-form-item>
      <el-form-item label="分类" prop="category">
        <el-cascader
            :props="{ value:'name', label:'name'}"
            v-model="form.categories"
            :options="categories"
            ></el-cascader>
      </el-form-item>
      <el-form-item label="书号" prop="bookNo">
        <el-input v-model="form.bookNo" placeholder="请输入书号"/>
      </el-form-item>
      <el-form-item label="借书积分" prop="score">
        <el-input v-model="form.score" placeholder="请输入借书积分"/>
      </el-form-item>
      <el-form-item label="数量" prop="nums">
        <el-input v-model="form.nums" placeholder="请输入数量"/>
      </el-form-item>
      <br>
      <el-form-item label="封面" prop="cover">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:9090/api/book/file/upload?token=' + this.admin.token"
            :show-file-list="false"
            :on-success="handleCoverSuccess"
        >
          <img v-if="form.cover" :src="form.cover" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>

    </el-form>

    <div style="text-align: center">
      <el-button type="primary" @click="save">提交</el-button>

    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";
export default {
  name: "AddBook",
  data(){
    const checkNums = (rule,value,callback) => {
      value = parseInt(value)
      if ( value < 0){
        callback(new Error('请输入大于等于0的整数'));
      }
      callback()
    }
    return{
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      form: { cover: ''},
      categories:[],
      rules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        bookNo: [
          {required: true, message: '请输入书号', trigger: 'blur'},
        ],
        score: [
          {validator: checkNums, trigger: 'blur'},
        ],
        nums: [
          {validator: checkNums, trigger: 'blur'},
        ]
      }
    }
  },
  created() {
    request.get('/category/tree').then(res => {
      this.categories = res.data
    })
  },
  methods: {
    handleCoverSuccess(res){
      //this.form = {cover: ''}
      if (res.code === '200'){
        //this.$set(this.form, 'cover', res.data)
        this.form.cover = res.data
      }
    },
    save(){
      this.$refs['ruleFrom'].validate((valid) => {
        if (valid){
          request.post('/book/save',this.form).then(res => {
            if (res.code === '200'){
              this.$notify.success('新增成功')
              this.$router.push("/bookList")
            }else {
              this.$notify.error('新增失败')
            }
          })
        }
      })
    },
  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
