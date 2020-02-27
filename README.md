# Listener监听器

## 1、监听器概述

监视某一对象，当该对象发生某些特定的行为时对其采取响应的措施。

\* 事件监听机制

​		* 事件	：一件事情

​		* 事件源 ：事件发生的地方

​		* 监听器 ：一个对象

​		* 注册监听：将事件、事件源、监听器绑定在一起。 当事件源上发生某个事件后，执行监听器代码

## 2、WEB中的监听器

监听对象：

* ServletContext  

* HttpSession  

* HttpServletRequest

包括监听对象本身 还包括监听对象的属性的变化

提供的监听器：

1. ServletContext对象：ServletContextListener  SerlvetContextAttributeListener

2. HttpSession对象 ：HttpSessionListener  HttpSessionAttributeListener

3. HttpServletRequest对象 ServletRequestListener  ServletRequestAttributListener

**ServletContextListener**  

![img](E:\YouDaoYun\m15234512314@163.com\82122e89d1db4a31a8cceac16caf0ba7\clipboard.png)

**SerlvetContextAttributeListener**

![img](E:\YouDaoYun\m15234512314@163.com\8008e2eb11e4487ca8db4091f265840c\clipboard.png)

**HttpSessionListener**

![img](E:\YouDaoYun\m15234512314@163.com\4bb1425931a849a8a696d6c268db3343\clipboard.png)

**HttpSessionAttributeListener**

![img](E:\YouDaoYun\m15234512314@163.com\a1c5547fe0f7473bb809735765f6352f\clipboard.png)

**ServletRequestListener**

![img](E:\YouDaoYun\m15234512314@163.com\481171e739a8453786248bc9fe154a31\clipboard.png)

**ServletRequestAttributListener**

![img](E:\YouDaoYun\m15234512314@163.com\d136cef976854ed8a6f9422bb7f4e888\clipboard.png)

## 2、监听器的应用

需求：监听当前在线人数。

监听对象Session

