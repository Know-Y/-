module.exports = {
    css: {
        loaderOptions: {
          css: {},
          postcss: {
            plugins: [
              require('postcss-px2rem')({
                remUnit: 37.5
              })
            ]
          }
        }
    },
    devServer: {
      open: true,
      host: 'localhost',
      port: 8080,
      https: false,
      hotOnly: false,
      proxy: { // 配置跨域
          '/api': {
              target: 'http://localhost:9102/api/',
              ws: true,
              changOrigin: true,
              pathRewrite: {
                  '^/api': ''
              }
          }
      },
      before: app => { }
  }
}