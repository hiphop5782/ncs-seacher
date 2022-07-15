const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  qpublicPath:process.env.NODE_ENV === "production" ? "http://api.sysout.co.kr/ncs/" : "",
})
