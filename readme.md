![https://img.shields.io/badge/springboot-2.0.4-yellow.svg?longCache=true&style=popout-square](https://img.shields.io/badge/springboot-2.1.0-yellow.svg?longCache=true&style=popout-square)
![https://img.shields.io/badge/spring%20security-5.0.7-green.svg?longCache=true&style=popout-square](https://img.shields.io/badge/spring%20security-5.0.7-green.svg?longCache=true&style=popout-square)

## 欢迎Fork和star!

halcyon是一个基础开发框架。项目基础框架采用Spring Boot2.1.0，消除了繁杂的XML配置，同时集成了代码生成器，能够一键生成后端代码，使得二次开发更为简单；
数据访问层采用Mybatis，同时引入了通用Mapper和PageHelper插件，可快速高效的对单表进行增删改查操作，消除了大量传统XML配置SQL的代码；安全框架采用Spring Security。


## 功能模块

```
|-------------------------------------------------------|
| _               _                                     |
|| |__     __ _  | |   ___   _   _    ___    _ __       |
|| '_ \   / _` | | |  / __| | | | |  / _ \  | '_ \      |
|| | | | | (_| | | | | (__  | |_| | | (_) | | | | |     |
||_| |_|  \__,_| |_|  \___|  \__, |  \___/  |_| |_|     |
|                            |___/                      |
|                                                       |
|   Spring-Boot-Version: 2.1.0.RELEASE                  |
|-------------------------------------------------------|
├─系统管理
│  ├─账户管理
│  ├─功能管理
│  ├─权限管理
│  └─代码生成
├─组织架构
│  ├─公司属性
│  ├─部门定义
│  ├─岗位定义
│  └─员工定义
├─任务调度
│  ├─任务调度
│  └─调度日志
|─邮件
|  └─邮件发送测试
├─工作流
│  ├─流程设计
│  ├─流程部署
│  └─流程监控
├─我的流程
│  ├─我的待办
│  ├─我发起的
│  └─已办事项
├─服务授权
│  ├─客户端管理
│  └─授权管理
├─报表管理
│  └─报表设计
```


## 技术选型

### 后端

- 基础框架：Spring Boot
- 持久层框架：Mybatis
- 安全框架：Spring Security
- 工作流引擎：activiti
- 摸板引擎：Thymeleaf
- 数据库连接池：Hikari
- 缓存框架：Redis
- 日志打印：logback
- 其他：fastjson，poi，quartz等

### 前端
- 基础框架：Bootstrap 3,AngularJS
- JavaScript框架：jQuery
- 提示框插件：SweetAlert2
- 多选下拉框插件：multiple-select

### 开发环境
- 语言：Java 8
- IDE：IDEA 2019.1
- 依赖管理：Maven
- 数据库：MySQL5.7
- 版本管理：git


## 系统预览


