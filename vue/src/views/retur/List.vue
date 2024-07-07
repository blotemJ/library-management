<template>
  <div>
    <div style="margin-bottom: 2px">
      <el-input style="width: 240px" placeholder="请输入图书名称" v-model="params.name"></el-input>
      <el-input style="width: 240px" placeholder="请输入书号" v-model="params.bookNo"></el-input>
      <el-input style="width: 240px" placeholder="请输入用户名称" v-model="params.userName"></el-input>
      <el-button style="margin-left: 5px" type="primary"><i class="el-icon-search" @click="load"></i>查找 </el-button>
    </div>
    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号" width="50px"/>
      <el-table-column prop="bookName" label="图书名称"/>
      <el-table-column prop="bookNo" label="书号"/>
      <el-table-column prop="userNo" label="会员号" />
      <el-table-column prop="userName" label="用户名"/>
      <el-table-column prop="userPhone" label="用户联系方式"/>
      <el-table-column prop="score" label="所用积分"/>
      <el-table-column prop="days" label="借出天数"/>
      <el-table-column prop="status" label="借书状态"/>
      <el-table-column prop="createtime" label="借书时间" />
      <el-table-column prop="returnDate" label="还书时间" />
      <el-table-column prop="realDate" label="实际还书时间" />

      <el-table-column label="操作" width="150px">
        <template v-slot="scope">
<!--          <el-button type="primary" @click="$router.push('/editBorrow?id=' + scope.row.id)">编辑</el-button>-->
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

  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'ReturList',
  data(){
    return{
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        bookName: '',
        bookNo: '',
        username:''
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get('/retur/page',{params: this.params}).then(res => {
        this.tableData =  res.list
        this.total = res.total
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/retur/delete/"+ id).then(res => {
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
