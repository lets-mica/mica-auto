# 变更记录

## 发行版本
## [0.0.2] - 2019-01-20
### 添加
- 组合 `spring-boot-configuration-processor` 避免项目中引入过多依赖。
- 组合 `spring-boot-autoconfigure-processor` 避免项目中引入过多依赖。
- 添加对 `Feign` 自动配置的生成，`mica-cloud` autoconfigure 中完成初始化。不需要使用 `@EnableFeignClients` 开启和指定 feign 包目录。

## [0.0.1] - 2019-01-12
### 添加
- 生成 `spring.factories`
- 生成 `spring-devtools.properties`