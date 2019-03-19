![https://img.shields.io/badge/springboot-2.0.4-yellow.svg?longCache=true&style=popout-square](https://img.shields.io/badge/springboot-2.1.0-yellow.svg?longCache=true&style=popout-square)
![https://img.shields.io/badge/spring%20security-5.0.7-green.svg?longCache=true&style=popout-square](https://img.shields.io/badge/spring%20security-5.0.7-green.svg?longCache=true&style=popout-square)

halcyon是一个综合应用平台。项目基础框架采用全新的Java Web开发框架 —— Spring Boot2.1.0，消除了繁杂的XML配置，使得二次开发更为简单；数据访问层采用Mybatis，同时引入了通用Mapper和PageHelper插件，可快速高效的对单表进行增删改查操作，消除了大量传统XML配置SQL的代码；安全框架采用Spring Security，可实现对按钮级别的权限控制。


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
│  └─功能管理
├─计划任务
│  ├─计划任务工作台
│  └─任务执行记录
├─工作流
│  ├─定时任务
│  └─调度日志
└─邮件
    └─邮件发送测试
```


## 技术选型

### 后端

- 基础框架：Spring Boot
- 持久层框架：Mybatis
- 安全框架：Spring Security
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
- IDE：IDEA 2018.1.4
- 依赖管理：Maven
- 数据库：MySQL5.7
- 版本管理：git

## 模块说明
系统分为以下五个模块：
<table>
<tr>
	<th>模块</th>
	<th>说明</th>
</tr>
<tr>
	<td>halcyon-core</td>
	<td>基础模块，主要包含一些工具类，基础配置</td>
</tr>	
<tr>
	<td>halcyon-base</td>
	<td>系统模块，增删改查服务</td>
</tr>
<tr>
	<td>halcyon-quartz</td>
	<td>任务调度模块，处理定时任务</td>
</tr>
<tr>
	<td>halcyon-security</td>
	<td>安全模块，和安全有关的都在这个模块里</td>
</tr>
<tr>
	<td>halcyon-web</td>
	<td>web模块，包含前端部分</td>
</tr>
</table>	

## todo
- [ ]  整合JWT
- [ ]  SSO

## 系统预览


