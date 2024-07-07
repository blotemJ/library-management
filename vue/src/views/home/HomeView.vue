<template>
  <div class="bg">
    <div class="item" >用户名：{{admin.username}}</div>
    <div class="item">联系电话：{{admin.phone}}</div>
    <div class="item">电子邮箱：{{admin.email}}</div>
  </div>
</template>
<script>
import Cookies from "js-cookie";
export default {
  data(){
    return{
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) :{}
    }
  },
  created() {
  }
}
</script>
<style>
.item{
  margin: 10px 0;
}
.bg{
  background:url('@/assets/back.jpg');
  color: lavender;
  width: 90% ;
  height:90%;
  text-align: center;
  margin-left: 10px;
}


</style>