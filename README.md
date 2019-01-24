# mica-auto 代码自动生成
[![mica-auto Maven](https://img.shields.io/maven-central/v/net.dreamlu/mica-auto.svg?style=flat-square)](https://mvnrepository.com/artifact/net.dreamlu/mica-bom)

`mica-auto` 是 `Spring cloud` 微服务框架 `Mica` 中的一个基础组件，用来生成 `Spring boot` 的一些基础配置。 

[版本更新记录](CHANGELOG.md)

想要了解更多可加入【如梦技术】QQ群：479710041

## 推荐
`mica 微服务框架`：https://github.com/lets-mica/mica

## 功能
1. 生成 `spring.factories`
2. 生成 `spring-devtools.properties`
3. 生成 `FeignClient` 到 `spring.factories` 中，供 `mica` 中完成 `Feign` 自动化配置。

## 使用
### maven
```xml
<dependency>
  <groupId>net.dreamlu</groupId>
  <artifactId>mica-auto</artifactId>
  <version>1.0.0</version>
  <scope>provided</scope>
</dependency>
```

### gradle >= 5.x
```groovy
annotationProcessor("net.dreamlu:mica-auto:1.0.0")
```

### gradle < 5.x
```groovy
compileOnly "net.dreamlu:mica-auto:1.0.0"
```

## 原理
扫描 `@Configuration`，自动生成相应的配置。

`注意：` v1.0.0 已经支持组合注解。

## 开源协议
LGPL（[GNU Lesser General Public License](http://www.gnu.org/licenses/lgpl.html)）

LGPL是GPL的一个为主要为类库使用设计的开源协议。和GPL要求任何使用/修改/衍生之GPL类库的的软件必须采用GPL协议不同。LGPL允许商业软件通过类库引用(link)方式使用LGPL类库而不需要开源商业软件的代码。这使得采用LGPL协议的开源代码可以被商业软件作为类库引用并发布和销售。

但是如果修改LGPL协议的代码或者衍生，则所有修改的代码，涉及修改部分的额外代码和衍生的代码都必须采用LGPL协议。因此LGPL协议的开源代码很适合作为第三方类库被商业软件引用，但不适合希望以LGPL协议代码为基础，通过修改和衍生的方式做二次开发的商业软件采用。

## 用户权益
* 允许以引入不改源码的形式免费用于学习、毕设、公司项目、私活等。
* 特殊情况修改代码，但仍然想闭源需经过作者同意。
* 参考请注明：参考 mica-atuo：https://github.com/lets-mica/mica-auto

## 参考
Google Auto: https://github.com/google/auto

Spring 5 - spring-context-indexer：https://github.com/spring-projects/spring-framework/tree/master/spring-context-indexer

