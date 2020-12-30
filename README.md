# mica-auto java SPI、Spring boot stater 利器
[![Java CI](https://github.com/lets-mica/mica-auto/workflows/Java%20CI/badge.svg)](https://github.com/lets-mica/mica-auto/actions)
![JAVA 8](https://img.shields.io/badge/JDK-1.8+-brightgreen.svg)
[![mica-auto Maven](https://img.shields.io/maven-central/v/net.dreamlu/mica-auto.svg?style=flat-square)](https://mvnrepository.com/artifact/net.dreamlu/mica-auto)

`mica-auto` 是 `Spring cloud` 微服务框架 `Mica` 中的一个基础组件，用来生成 `Spring boot starter` 的一些基础配置。 

[版本更新记录](CHANGELOG.md)

## 原理

mica auto 采用 Annotation Processor，她不是什么新技术，在 jdk 1.6 就存在了。 Annotation Processor 是 javac 的一个工具，它用来在编译时扫描和处理注解。通过 Annotation Processor 可以获取到注解和被注解对象的相关信息，然后根据注解自动生成Java代码，省去了手动编写，提高了编码效率。大名鼎鼎的 Lombok 也是采用的该技术。

## 使用场景
主要是用来避免 Spring boot 主项目包同 `子项目`或者`子模块` 包不一致，避免包扫描不到的问题。 

- 自定义 `spring boot starter`。
- 多模块项目中的`子项目`（不建议主项目添加 `mica-auto`）。

## 功能
- 自动生成 spring.factories、spring-devtools.properties 配置（Spring boot starter 开发利器）。
- 生成 FeignClient 到 spring.factories 中，供 mica-cloud 中完成 Feign 自动化配置。
- 生成 java spi 配置，需要添加 `@AutoService` 注解。

| 注解                            | spring.factories 或 Spi key     |
| ------------------------------- | ------------------------------- |
| @AutoContextInitializer         | ApplicationContextInitializer   |
| @AutoListener                   | ApplicationListener             |
| @AutoRunListener                | SpringApplicationRunListener    |
| @AutoEnvPostProcessor           | EnvironmentPostProcessor        |
| @AutoFailureAnalyzer            | FailureAnalyzer                 |
| @AutoConfigImportFilter         | AutoConfigurationImportFilter   |
| @AutoTemplateProvider           | TemplateAvailabilityProvider    |
| @AutoEnableCircuitBreaker       | EnableCircuitBreaker            |
| @AutoConfigDataLocationResolver | ConfigDataLocationResolver      |
| @AutoConfigDataLoader           | ConfigDataLoader                |
| @Component                      | EnableAutoConfiguration         |
| @AutoIgnore                     | 忽略，不生成到 spring.factories |
| @AutoService                    | java Spi 生成配置               |

## 使用
`注意：` 如果你项目中使用了 `Lombok` 请将 `mica-auto` 的依赖放置到 `Lombok` 后面。

### maven
```xml
<dependency>
  <groupId>net.dreamlu</groupId>
  <artifactId>mica-auto</artifactId>
  <version>2.0.3</version>
  <scope>provided</scope>
</dependency>
```

### gradle >= 5.x
```groovy
annotationProcessor("net.dreamlu:mica-auto:2.0.3")
```

### gradle < 5.x
```groovy
compileOnly "net.dreamlu:mica-auto:2.0.3"
```

## 原理
扫描 `@Component`，自动生成相应的配置，支持组合注解。

## mica 生态

- mica (Spring cloud 微服务组件集): https://gitee.com/596392912/mica
- mica-auto (Spring boot starter 利器): https://gitee.com/596392912/mica-auto
- mica-weixin（jfinal weixin 的 spring boot starter）：https://gitee.com/596392912/mica-weixin
- mica-mqtt（基于 t-io 实现的 mqtt组件）：https://gitee.com/596392912/mica-mqtt
- Spring cloud 微服务 http2 方案（h2c）: https://gitee.com/596392912/spring-cloud-java11

## 开源协议
LGPL（[GNU Lesser General Public License](http://www.gnu.org/licenses/lgpl.html)）

LGPL是GPL的一个为主要为类库使用设计的开源协议。和GPL要求任何使用/修改/衍生之GPL类库的的软件必须采用GPL协议不同。LGPL允许商业软件通过类库引用(link)方式使用LGPL类库而不需要开源商业软件的代码。这使得采用LGPL协议的开源代码可以被商业软件作为类库引用并发布和销售。

但是如果修改LGPL协议的代码或者衍生，则所有修改的代码，涉及修改部分的额外代码和衍生的代码都必须采用LGPL协议。因此LGPL协议的开源代码很适合作为第三方类库被商业软件引用，但不适合希望以LGPL协议代码为基础，通过修改和衍生的方式做二次开发的商业软件采用。

## 用户权益
* 允许以引入不改源码的形式免费用于学习、毕设、公司项目、私活等。
* 特殊情况修改代码，但仍然想闭源需经过作者同意。
* 参考请注明：参考 mica-auto: https://github.com/lets-mica/mica-auto

## 参考
Google Auto: https://github.com/google/auto

Spring 5 - spring-context-indexer：https://github.com/spring-projects/spring-framework/tree/master/spring-context-indexer

## 微信公众号

![如梦技术](docs/dreamlu-weixin.jpg)

精彩内容每日推荐！
