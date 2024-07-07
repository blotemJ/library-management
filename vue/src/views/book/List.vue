<template>
  <div>
    <div style="margin-bottom: 2px">
      <el-input style="width: 240px" placeholder="请输入图书名称" v-model="params.name"></el-input>
      <el-input style="width: 240px" placeholder="请输入书号" v-model="params.bookNo"></el-input>
      <el-button style="margin-left: 5px" type="primary"><i class="el-icon-search" @click="load"></i>查找 </el-button>
    </div>
    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号" width="50px"/>
      <el-table-column prop="name" label="图书名称"/>
      <el-table-column prop=bookNo label="书号" />
      <el-table-column prop="description" label="描述"/>
      <el-table-column prop="publishDate" label="出版时间" />
      <el-table-column prop="author" label="作者"/>
      <el-table-column prop="publisher" label="出版社"/>
      <el-table-column prop="category" label="分类"/>
      <el-table-column prop=score label="积分" />
      <el-table-column prop=nums label="数量" />
      <el-table-column prop="createtime" label="创建时间" />
      <el-table-column prop="updatetime" label="修改日期"/>
      <el-table-column prop="cover" label="封面">
        <template v-slot="scope">
          <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150px">
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editBook?id=' + scope.row.id)">编辑</el-button>
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
  name: 'BookList',
  data(){
    return{
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        bookNo: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get('/book/page',{params: this.params}).then(res => {
        this.tableData =  res.list
        this.total = res.total
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/book/delete/"+ id).then(res => {
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
