<template>
  <div>
    <div style="margin-bottom: 2px">
      <el-input style="width: 240px" placeholder="请输入名称" v-model="params.name"></el-input>
      <el-input style="width: 240px" placeholder="请输入联系方式" v-model="params.phone"></el-input>
      <el-button style="margin-left: 5px" type="primary"><i class="el-icon-search" @click="load"></i>查找 </el-button>
    </div>
    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号" width="70"></el-table-column>
      <el-table-column prop="name" label="名称" ></el-table-column>
      <el-table-column prop="username" label="卡号" width="120"></el-table-column>
      <el-table-column prop="age" label="年龄" width="80"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
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
      <el-table-column prop="account" label="账户余额"></el-table-column>
      <el-table-column prop="sex" label="性别" width="80"></el-table-column>
      <el-table-column prop="createtime" label="添加日期"></el-table-column>
      <el-table-column prop="updatetime" label="修改日期"></el-table-column>
      <el-table-column label="操作" width="220">
        <template v-slot="scope">
          <el-button type="warning" @click="handleAccountAdd(scope.row)">充值</el-button>
          <el-button type="primary" @click="$router.push('/editUser?id=' + scope.row.id)">编辑</el-button>
          <el-popconfirm
              style="margin-left: 5px"
              title="您确定删除这行数据吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
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
    <el-dialog title="账户积分充值" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" label-width="100px" ref="ruleForm" :rules="rules">
        <el-form-item disabled label="当前账户积分" prop="account">
          <el-input v-model="form.account" auto-complete="off"  disabled></el-input>
        </el-form-item>

        <el-form-item label="积分" prop="score">
          <el-input v-model="form.score" auto-complete="off" ></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addAccount">确定</el-button>
      </div>
    </el-dialog>




  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'UserList',
  data(){
    const checkNums = (rule,value,callback) => {
      value = parseInt(value)
      if (value < 0 || value >= 200){
        callback(new Error('请输入大于等于0且小于等于200的整数'));
      }
      callback()
    }
    return{
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        phone: ''
      },
      dialogFormVisible: false,
      form:{},
      rules: {
        score: [
          { required: true, message: '请输入充值积分', trigger: 'blur'},
          {validator: checkNums, trigger: 'blur'},
        ]
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    changStatus(row){
      request.put('/user/update',row).then(res => {
        if (res.code === '200'){
          this.$notify.success('操作成功')
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    },
    load(){
      request.get('/user/page',{params: this.params}).then(res => {
        this.tableData =  res.list
        this.total = res.total
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/user/delete/"+ id).then(res => {
        if (res.code === '200'){
          this.$notify.success('删除成功')
          this.load()
        }else {
          this.$notify.error('删除失败')
        }
      })
    },
    handleAccountAdd(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    addAccount(){
      this.$refs['ruleForm'].validate((valid) => {
        if (valid){
          request.post('/user/account',this.form).then(res => {
            if (res.code === '200'){
              this.$notify.success('充值成功')
              this.dialogFormVisible = false
              this.load()
            }else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>
