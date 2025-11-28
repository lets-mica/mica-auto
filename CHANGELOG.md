# 变更记录

## 发行版本

## [4.0.1] - 2025-11-28
- fix(deps): 移除 spring-boot-configuration-processor 和 spring-boot-autoconfigure-processor 依赖的 optional 标记

## [4.0.0] - 2025-11-27
- :sparkles: 支持 Spring boot 4.0.0

## [3.1.6] - 2025-11-28
- fix(deps): 移除 spring-boot-configuration-processor 和 spring-boot-autoconfigure-processor 依赖的 optional 标记

## [3.1.5] - 2024-01-16
- :sparkles: 从 Gradle 构建迁移到 Maven
- :arrow_up: 升级到 Spring boot 3.5.5

## [3.1.3] - 2024-01-16
- :sparkles: 支持 Kotlin Spring boot starter。
- :arrow_up: 升级到 Spring boot 3.2.1

## [3.1.2] - 2023-10-15
- :sparkles: 支持 Kotlin 元注解，github #8 感谢 `@Sunrise66` 反馈
- :arrow_up: 升级到 Spring boot 3.1.4

## [3.1.1] - 2023-01-02
- :arrow_up: 升级到 Spring boot 3.1.1

## [3.0.1] - 2023-01-02
- :sparkles: Spring boot 2.7.x+ spi 生成改为直接支持 `@Component`
- :arrow_up: 升级到 Spring boot 3.0.1

## [3.0.0] - 2022-11-29
- :sparkles: 支持 Spring boot 3.0.0 aot native 
- :arrow_up: 升级到 Spring boot 3.0.0
- :arrow_up: 升级到 java17

## [2.3.4] - 2024-01-16
- :sparkles: 支持 Kotlin Spring boot starter。
- :arrow_up: 升级到 Spring boot 2.7.18

## [2.3.3] - 2023-10-15
- :sparkles: 支持 Kotlin 元注解，github #8 感谢 `@Sunrise66` 反馈
- :arrow_up: 升级到 Spring boot 2.7.16

## [2.3.2] - 2023-01-02
- :sparkles: Spring boot 2.7.x+ spi 生成改为直接支持 `@Component`
- :arrow_up: 升级到 Spring boot 2.7.7

## [2.3.1] - 2022-06-02
- :sparkles: 支持生成的 Spring boot 2.7.0 向下兼容。感谢 `@冷冷` 提出的需求。

## [2.3.0] - 2022-05-30
- :sparkles: 支持 Spring boot 2.7.0 新特性 `@AutoConfiguration`。 
- :arrow_up: 升级 Spring boot 到 2.7.0

## [2.2.2] - 2021-12-26
- :sparkles: 支持合并用户手写的 `spring.factories` 配置
- :arrow_up: 升级 Spring boot 到 2.6.2

## [2.2.1] - 2021-12-10
- :arrow_up: 升级 Spring boot 到 2.6.1

## [2.2.0] - 2021-11-20
- :arrow_up: 升级 gradle 到 7.3
- :arrow_up: 升级 lombok 到 1.18.22
- :arrow_up: 升级 google auto 到 1.0.1
- :arrow_up: 升级 Spring boot 到 2.6.0

## [2.1.3] - 2021-08-05
- :sparkles: 代码优化，优化流关闭。
- :sparkles: 优化 github actions。
- :bug: 修复 spi，去除注释。
- :bug: 修复 gitee #I4193Q idea 增量编译 bug。
- :bug: 修复 spring-devtools.properties 匹配 bug。

## [2.1.2] - 2021-07-26
### 新功能
- :sparkles: `@AutoService` 支持 gradle 增量编译
- :arrow_up: 升级 Spring boot 到 2.5.3

## [2.1.1] - 2021-07-02
### 新功能
- :sparkles: 添加 DatabaseInitializer 相关支持
- :sparkles: 优化 jar manifest
- :white_check_mark: Adding google compile-testing.
- :arrow_up: 升级 Spring boot 到 2.5.2

## [2.1.0] - 2021-05-21
### 新功能
- :sparkles: 支持 gradle 增量编译
- :sparkles: 优化 jar manifest
- :arrow_up: 升级 Spring boot 到 2.5.0
- :arrow_up: 升级 lombok 到 1.18.20
- :arrow_up: 升级 google auto 到 1.0

## [2.0.4] - 2021-03-20
### 新功能
- :arrow_up: 升级 Spring boot 到 2.4.4
- :arrow_up: 升级 lombok 到 1.18.18

## [2.0.3] - 2020-12-28
### 新功能
- 添加 AutoConfigDataLocationResolver。
- 添加 AutoConfigDataLoader

## [2.0.1] - 2020-12-24
### 新功能
- 添加 AutoEnableCircuitBreaker。

## [2.0.0] - 2020-11-15
### 依赖升级
- :arrow_up: 升级 spring boot 到 2.4.1。

## [1.3.0] - 2020-11-15
### 依赖升级
- :arrow_up: 升级 spring boot 到 2.4.0。
- :arrow_up: gradle 版本和 mica 保持一致。
- :arrow_up: 升级 lombok 到 1.18.16。

## [1.2.5] - 2020-07-25
### 修复
- :bug: 修复编译的 jdk 版本，使用 jdk8.

## [1.2.4] - 2020-07-25
### 依赖升级
- :arrow_up: 升级 spring boot 到 2.3.2.RELEASE。

## [1.2.3] - 2020-05-30
### 新功能
- 添加 `AutoConfigurationImportFilter` 的 `@AutoConfigImportFilter`。
- 添加 `TemplateAvailabilityProvider` 的 `@AutoTemplateProvider`。

### 依赖升级
- :arrow_up: 升级 spring bootVersion 到 2.3.0.RELEASE。
- :arrow_up: 升级 google auto 到 1.0-rc7。

## [1.2.2] - 2020-04-27
### 新功能
- 添加可用于忽略生成到 spring.factories 的 `@AutoIgnore`。

### 依赖升级
- lombok 升级到 `1.18.12`。

## [1.2.2] - 2020-04-27
### 添加
- 添加可用于忽略生成到 spring.factories 的 `@AutoIgnore`。
- lombok 升级到 `1.18.12`。

## [1.2.1] - 2020-04-09
### 添加
- 添加对 spring boot SpringApplicationRunListener 的支持。
- 添加对 spring boot EnvironmentPostProcessor 的支持。
- Spring boot 升级到 `2.2.6.RELEASE`。

## [1.2.0] - 2019-12-06
### 添加
- 添加对 java spi 的支持，并精简代码。
- lombok、spring boot、google auto 依赖升级。

## [1.1.0] - 2019-05-24
### 添加
- 添加 `@AutoContextInitializer` 支持 `org.springframework.context.ApplicationContextInitializer`。
- 添加 `@AutoListener` 支持 `org.springframework.context.ApplicationListener`。
- 添加 `@AutoFailureAnalyzer` 支持 `org.springframework.boot.diagnostics.FailureAnalyzer`。

### 依赖升级
- 升级 `gradle` 到 `5.4.1`。
- 升级 `google auto` 到 `1.0-rc5`。
- 升级 `lombok` 到 `1.18.8`，
- 升级 `Spring Boot` 到 `2.1.5.RELEASE`。

## [1.0.1] - 2019-01-27
### 调整
- 扫描注解由 `@Configuration` 调整为 `@Component`，支持 `@Service`、`@Controller` 等组合有 `@Component` 的注解。

### 修复
- ✅调整 process 返回 false，支持其他注解处理器。

## [1.0.0] - 2019-01-21
### 修复
- ✅修复组合注解递归存在 `bug`。

## [0.0.3] - 2019-01-20
### 添加
- 😘支持 `Spring` 组合注解。

## [0.0.2] - 2019-01-20
### 添加
- 组合 `spring-boot-configuration-processor` 避免项目中引入过多依赖。
- 组合 `spring-boot-autoconfigure-processor` 避免项目中引入过多依赖。
- 添加对 `Feign` 自动配置的生成，`mica-cloud` autoconfigure 中完成初始化。不需要使用 `@EnableFeignClients` 开启和指定 feign 包目录。

## [0.0.1] - 2019-01-12
### 添加
- 生成 `spring.factories`
- 生成 `spring-devtools.properties`