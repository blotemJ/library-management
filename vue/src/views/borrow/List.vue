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
      <el-table-column prop="status" label="借书状态"/>
      <el-table-column prop="days" label="借出天数"/>
      <el-table-column prop="createtime" label="借书时间" />
      <el-table-column prop="returnDate" label="还书时间" />
      <el-table-column prop="note" label="过期提醒">
        <template v-slot="scope">
          <el-tag type="success" v-if="scope.row.note === '正常状态'">{{scope.row.note}}</el-tag>
          <el-tag type="primary" v-if="scope.row.note === '即将到期'">{{scope.row.note}}</el-tag>
          <el-tag type="warning" v-if="scope.row.note === '已到期'">{{scope.row.note}}</el-tag>
          <el-tag type="danger" v-if="scope.row.note === '已过期'">{{scope.row.note}}</el-tag>
        </template>
      </el-table-column>
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
          <el-popconfirm
              style="margin-left: 5px"
              title="您确定归还图书吗？"
              @confirm="returnBook(scope.row)"
              v-if="scope.row.status ==='已借出'"
          >
            <el-button type="primary" slot="reference">还书</el-button>
          </el-popconfirm>
<!--          <el-button type="primary" style="margin-left: 5px" @click="returnBook(scope.row)" v-if="scope.row.status ==='已借出'">还书</el-button>-->

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
  name: 'BorrowList',
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
      request.get('/borrow/page',{params: this.params}).then(res => {
        this.tableData =  res.list
        this.total = res.total
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/borrow/delete/"+ id).then(res => {
        if (res.code === '200'){
          this.$notify.success('删除成功')
          this.load()
        }else {
          this.$notify.error('删除失败')
        }
      })
    },
    returnBook(row){
      request.post("/retur/save/",row).then(res => {
        if (res.code === '200'){
          this.$notify.success('还书成功')
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>
