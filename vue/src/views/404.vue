<template>
  <div style="text-align: center">
    <div style="margin-top: 150px; font-size: 100px; ">404</div>
    <div style="font-size: 50px">未找到页面</div>
    <div><el-button style="margin-top: 20px; font-size: 20px" type="text" @click="$router.push('/')">返回正确的页面</el-button></div>
  </div>

</template>

<script>
export default {
  name: "404"
}
</script>

<style scoped>

</style>