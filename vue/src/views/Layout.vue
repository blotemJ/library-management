<template>
  <div>
    <div style="height: 50px; line-height: 50px; background-color: lavenderblush; margin-bottom: 2px; display: flex" >
      <div style="width: 300px">
        <img src="@/assets/logo.png" style=" width: 35px; position: relative; top: 10px; left: 20px" >
        <span style="margin-left: 30px; font-size: 25px">图书管理系统</span>
      </div>
      <div style="flex: 1; text-align: right; padding-right: 30px">
        <el-dropdown size="medium">
          <span class="el-dropdown-link" style="cursor: pointer">
            {{ admin.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="margin-top: -8px">
            <el-dropdown-item>
              <div style="width: 50px; text-align: center" @click="logout">退出</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <div style="display: flex">
      <!--  侧边栏  -->
      <div style="width: 180px; min-height: calc(100vh - 52px);overflow: hidden; margin-right: 2px; background-color: white">
        <el-menu :default-active="$router.path" router  class="el-menu-demo" >
          <el-menu-item index="/">
            <i class="el-icon-s-home"></i>
            <span>首页</span>
          </el-menu-item>

          <el-submenu index="/user">
            <template slot="title">
              <i class="el-icon-question"></i>
              <span>会员管理</span>
            </template>
            <el-menu-item index="/userList">会员列表</el-menu-item>
            <el-menu-item index="/addUser">会员添加</el-menu-item>
          </el-submenu>
          <el-submenu index="/category">
            <template slot="title">
              <i class="el-icon-s-operation"></i>
              <span>图书类别管理</span>
            </template>
            <el-menu-item index="/categoryList">图书类别列表</el-menu-item>
            <el-menu-item index="/addCategory">图书类别添加</el-menu-item>
          </el-submenu>
          <el-submenu index="/book">
            <template slot="title">
              <i class="el-icon-notebook-1"></i>
              <span>图书管理</span>
            </template>
            <el-menu-item index="/bookList">图书列表</el-menu-item>
            <el-menu-item index="/addBook">图书添加</el-menu-item>
          </el-submenu>
          <el-submenu index="/borrow">
            <template slot="title">
              <i class="el-icon-reading"></i>
              <span>借书管理</span>
            </template>
            <el-menu-item index="/borrowList">借书列表</el-menu-item>
            <el-menu-item index="/addBorrow">借书添加</el-menu-item>
          </el-submenu>
          <el-submenu index="/retur">
            <template slot="title">
              <i class="el-icon-reading"></i>
              <span>还书管理</span>
            </template>
            <el-menu-item index="/returList">还书列表</el-menu-item>
          </el-submenu>
          <el-submenu index="/admin">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>管理员管理</span>
            </template>
            <el-menu-item index="/adminList">管理员列表</el-menu-item>
            <el-menu-item index="/addAdmin">管理员添加</el-menu-item>
          </el-submenu>


        </el-menu>
      </div>

      <!-- 主体  -->
      <div style="flex: 1; width: 0; padding: 10px" >
        <router-view/>
      </div>

    </div>
  </div>
</template>

<script>
import Cookies from "js-cookie";
export default {
  name: "Layout",
  data(){
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
    }
  },
  methods:{
    logout(){
      Cookies.remove('admin')
      this.$router.push('/login')

    }
  }
}
</script>

<style scoped>

</style>