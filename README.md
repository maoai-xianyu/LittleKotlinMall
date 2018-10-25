## LittleKotlinMall
学习kotlin mvp 项目  

### 模块化
相对独立业务拆分成"块"，单独开发调试，拼接业务模块，组装App

why
1. 业务分离，解耦
2. 通用化，代码复用

实现模块化
1. 公共模块抽取
2. 业务模块抽取
3. 主工程组装业务模块

模块之间通讯
1. 跨模块跳转
2. 跨模块接口调用
3. ARouter路由框架（阿里巴巴）

### Mvp  view -- presenter - model

### 学习流程
1. 建立BaseLibrary -- 存放基础库
2. 建立Provider -- 存放公有属性
