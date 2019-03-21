### 项目结构
***
```
wlpay-master
├── wlpay4dubbo -- spring-boot-dubbo架构实现
|    ├── wlpay4dubbo-api -- 接口定义
|    ├── wlpay4dubbo-service -- 服务生产者
|    ├── wlpay4dubbo-web -- 服务消费者
├── wlpay-common -- 公共模块
├── wlpay-dal -- 数据持久层
├── wlpay-mgr -- 运营管理平台
├── wlpay-shop -- 演示商城
```

#### wlpay-master
| 项目  | 端口 | 描述
|---|---|---
|wlpay-common |  | 公共模块(常量、工具类等)，jar发布
|wlpay-dal |  | 支付数据持久层，jar发布
|wlpay-mgr | 8092 | 支付运营平台
|wlpay-shop | 8081 | 支付商城演示系统
|wlpay4dubbo |  | 支付中心spring-boot-dubbo架构实现

#### wlpay4dubbo
| 项目  | 端口 | 描述
|---|---|---
|wlpay4dubbo-api |  | API接口定义
|wlpay4dubbo-service | 20880 | 支付服务生产者
|wlpay4dubbo-web | 3020 | 支付服务消费者
项目启动顺序：
```
wlpay4dubbo-service > wlpay4dubbo-web
