
<h1 align="center">Java Web 书评网站</h1>

<p align="center">
<img src="https://img.shields.io/badge/Spring_WebMVC-5.2.6-informational" alt="Spring WebMVC">
<img src="https://img.shields.io/badge/Maven-3.6.3-orange" alt="Maven">
<img src="https://img.shields.io/badge/MyBatis-3.5.4-brightgreen" alt="MyBatis">
<img src="https://img.shields.io/badge/MySQL-5.7-yellowgreen" alt="MySQL">
<img src="https://img.shields.io/badge/Bootstrap-4.4.1-inactive" alt="Bootstrap">
<img src="https://img.shields.io/badge/MyBtis_Plus-3.3.2-black" alt="MyBatis Plus">
<img src="https://img.shields.io/badge/JSoup-1.12.1-red" alt="JSoup">
<img src="https://img.shields.io/badge/kaptcha-2.3.2-blueviolet" alt="kaptcha">
<img src="https://img.shields.io/badge/dependencies-up to date-ff69b4" alt="dev dependencies">
<p>

## 💬项目简介
使用 SSM + MyBatis-Plus 开发的一个兼用户登录注册、图书分类、图书详情、图书在线编辑、图书评分和评
论等功能的 Java Web 应用“书评网站”

## 📝 [接口文档](https://easydoc.xyz/p/56969226/HMHVcIIU)

![](https://image.yangxiansheng.top/img/20210314013536.png?imglist)

## 🚀 如何运行

1. 克隆项目

```Shell
git clone https://gitee.com/liulvhua/bookStore_admin.git
```
2. 导入 MySQl文件

![](https://image.yangxiansheng.top/img/20210314144008.png?imglist)

3. 使用Idea 导入该项目

4. 修改 Spring 与 MyBatis 整合配置文件 applicationContext.xml

![](https://image.yangxiansheng.top/img/20210314144906.png?imglist)

5. 将项目依赖发布到 WEB-INF/lib 中

![](https://image.yangxiansheng.top/img/20210314150143.png?imglist)

6. 配置 Tomcat 

![](https://image.yangxiansheng.top/img/20210314150845.png?imglist)
![](https://image.yangxiansheng.top/img/20210314150943.png?imglist)

7. 启动 Tomcat，运行项目

6. 使用 Chrome 浏览器，地址栏输入 localhost/ ，若出现以下页面则说明项目启动成功

![](https://image.yangxiansheng.top/img/20210314151806.png?imglist)

## :point_right: 技术栈

Spring、Spring WebMVC、MyBatis-Plus、MySQL 5.7、Bootstrap、wangEditor、Kaptcha、JSoup、Art-Template

## ✌️ 实现功能

- [x] 图书模块
    + [x] 首页
    + [x] 分页查看图书
    + [x] 图书详情页
- [x] 会员模块
    + [x] 获取验证码、登录/注册
    + [x] 查看图书阅读状态
    + [x] 修改图书阅读状态
    + [x] 评分、写短评
    + [x] 短评点赞
- [ ] 后台管理页
    + [x] 图书添加、删除、修改 
    + [ ] 短评管理
        + [ ] 短评禁用
        + [ ] 删除短评

## 💻 部分截图

<h3>网站首页</h3>

![](https://image.yangxiansheng.top/img/20210314155455.png?imglist)

<h3>加载下一页</h3>

![](https://image.yangxiansheng.top/img/20210314155604.png?imglist)
![](https://image.yangxiansheng.top/img/20210314155641.png?imglist)

<h3>阅读状态变更</h3>

![](https://image.yangxiansheng.top/img/20210314155810.png?imglist)

<h3>评分、写短评</h3>

![](https://image.yangxiansheng.top/img/20210314155942.png?imglist)

<h3>短评点赞</h3>

![](https://image.yangxiansheng.top/img/20210314160038.png?imglist)

<h3>后台管理首页</h3>

![](https://image.yangxiansheng.top/img/20210314154740.png?imglist)

<h3>新增图书</h3>

![](https://image.yangxiansheng.top/img/20210314155401.png?imglist)

<h3>修改、删除图书</h3>

![](https://image.yangxiansheng.top/img/20210314155007.png?imglist)

![](https://image.yangxiansheng.top/img/20210314155028.png?imglist)

##  👌 项目结构

![](https://image.yangxiansheng.top/img/20210314154520.png?imglist)

## 🤡 作者

**Liulvhua**


#### 参与贡献

1.  Fork 本仓库
2.  新建 分支
3.  提交代码
4.  新建 Pull Request
