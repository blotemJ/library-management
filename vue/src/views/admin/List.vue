<template>
  <div>
    <div style="margin-bottom: 2px">
      <el-input style="width: 240px" placeholder="请输入用户名" v-model="params.username"></el-input>
      <el-input style="width: 240px" placeholder="请输入联系方式" v-model="params.phone"></el-input>
      <el-input style="width: 240px" placeholder="请输入邮箱" v-model="params.email"></el-input>
      <el-button style="margin-left: 5px" type="primary"><i class="el-icon-search" @click="load"></i>查找 </el-button>
    </div>
    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号" width="80px"></el-table-column>
      <el-table-column prop="username" label="用户名" width="160px"></el-table-column>
      <el-table-column prop="phone" label="电话" width="240px" ></el-table-column>
      <el-table-column prop="email" label="邮箱" width="240px"></el-table-column>
      <el-table-column prop="updatetime" label="修改日期" width="150px"></el-table-column>
      <el-table-column label="状态" prop="status">
        <template v-slot="scope" style="width: 230px">
          <el-switch
              v-model="scope.row.status"
              @change="changStatus(scope.row)"
              active-color="#13ce66"
              inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="300px">
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editAdmin?id=' + scope.row.id)">编辑</el-button>
          <el-popconfirm
              style="margin-left: 5px"
              title="您确定删除这行数据吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
            <el-button type="warning" @click="handleChangePass(scope.row)" style="margin-left: 5px">修改密码</el-button>
        </template>

      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div style="margin-top: 10px">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" label-width="100px" ref="formRef" :rules="rules">
        <el-form-item label="新密码" prop="newPass">
          <el-input v-model="form.newPass" auto-complete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="savePass ">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'AdminList',
  data(){
    return{
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData: [],
      total: 0,
      form: {},
      dialogFormVisible: false,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        phone: '',
        email:''
      },
      rules: {
        newPass: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {min:3, max: 10, message: '长度在3-10个字符', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    changStatus(row){
      if (this.admin.id === row.id && !row.status){
        row.status = true
        this.$notify.warning("您的操作不合法")
        return
      }
        request.put('/admin/update',row).then(res => {
          if (res.code === '200'){
            this.$notify.success('操作成功')
            this.load()
          }else {
            this.$notify.error('操作失败')
          }
        })
    },
    handleChangePass(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    savePass(){
      this.$refs['formRef'].validate((valid) => {
        if (valid){
          request.put('/admin/password',this.form).then(res => {
            if (res.code === '200'){
              this.$notify.success("修改成功")
              if (this.form.id === this.admin.id){
                Cookies.remove('admin')
                this.$router.push('/login')
              }else {
                this.load()
                this.dialogFormVisible = false
              }
            }else{
              this.$notify.error("修改失败")
            }
          })
        }
      })
    },
    load(){
      request.get('/admin/page',{params: this.params}).then(res => {
        this.tableData =  res.list
        this.total = res.total
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/admin/delete/"+ id).then(res => {
        if (res.code === '200'){
          this.$notify.success('删除成功')
          this.load()
        }else {
          this.$notify.error('删除失败')
        }
      })
    },
  }
}
</script>
