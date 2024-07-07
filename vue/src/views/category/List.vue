<template>
  <div>
    <div style="margin-bottom: 2px">
      <el-input style="width: 240px" placeholder="请输入名称" v-model="params.name"></el-input>
      <el-button style="margin-left: 5px" type="primary"><i class="el-icon-search" @click="load"></i>查找 </el-button>
    </div>
    <el-table :data="tableData" stripe row-key="id" default-expand-all>
      <el-table-column prop="id" label="编号" width="120"></el-table-column>
      <el-table-column prop="name" label="类别名称" width="180"></el-table-column>
      <el-table-column prop="remark" label="图书备注" ></el-table-column>
      <el-table-column prop="createtime" label="添加日期"></el-table-column>
      <el-table-column prop="updatetime" label="修改日期"></el-table-column>
      <el-table-column label="操作" width="300px">
        <template v-slot="scope">
          <el-button type="success" v-if="!scope.row.pid" @click="handleAdd(scope.row)">添加二级分类</el-button>
          <el-button type="primary" @click="$router.push('/editCategory?id=' + scope.row.id)">编辑</el-button>
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

    <el-dialog title="添加二级分类" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" label-width="100px" ref="ruleForm" :rules="rules">
        <el-form-item label="图书类别" prop="name">
          <el-input v-model="form.name" auto-complete="off" ></el-input>
        </el-form-item>
        <el-form-item label="类别备注" prop="remark">
          <el-input v-model="form.remark" auto-complete="off" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'CategoryList',
  data(){
    return{
      tableData: [],
      total: 0,
      dialogFormVisible:false,
      form: {},
      pid:null,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
      },
      rules: {
        name: [
          {required: true, message: '请输图书类别', trigger: 'blur'},
        ],
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    save(){
      this.$refs['ruleForm'].validate((valid) => {
        if (valid){
          this.form.pid = this.pid
          request.post('/category/save',this.form).then(res => {
            if (res.code === '200'){
              this.$notify.success('新增二级分类成功')
              this.$refs['ruleForm'].resetFields()
              this.dialogFormVisible = false
              this.load()
            }else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    },
    handleAdd(row){
      this.pid = row.id
      this.dialogFormVisible = true
    },
    load(){
      request.get('/category/page',{params: this.params}).then(res => {
        this.tableData =  res.list
        this.total = res.total
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/category/delete/"+ id).then(res => {
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