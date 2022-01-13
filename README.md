 **dataprocess简介** 
 
   **项目初衷：** 
        随着业务系统的不断迭代，数据库的数据量也随着逐渐增大，就导致业务系统日常运行情况下很多接口查询请求缓慢，
     甚至读取超时 ,为了整体优化项目，提高系统性能 希望在系统中对于部分业务充分利用缓存操作或使用ElasticSearch
     搜索引擎来高效率的查询 那么问题来了，数据如何同步到ES，Redis或其他的容器中呢

   **数据同步：** 
        既然是搜索业务，读取缓存 那么对数据的实时性要求一定很高，所以这里我们使用 阿里巴巴开源的Canal让它去监听
    我们关系型数据库的主库例如 MySql-Master，实时读取数据库操作日志的变更，Canal就相当于关系型数据库的从库Slave，
    Canal经过很多次迭代已经很成熟了，Canal自身又对RocketMq和kafka做了集成 可以把数据库订阅的日志直接输送到我们MQ当中，
    使用者只需要去监听MQ的消息队列即可，本次项目集成的是RocketMQ
   
   **dataprocess角色：** 
        Mysql-》Canal-》RocketMQ-》dataproces处理{过滤器1处理ES 增删改--过滤器2处理 Redis 增删改...}
    dataprocess主要充当着 监听从RocketMQ中的消息然后帮助使用者解析 例如:某个库中的某个表中的某一批记录发生了
    添加 修改 删除 等操作 或者某个表发生了 创建/删除索引 等操作，并以过滤链的形式给使用者提供了 添加/修改/删除 
    等接口方法，使用者只需要 专注去做自己想要做的添加功能 删除功能  修改功能 就可以了

 **项目涉及到的技术点：**  
  1. 基础环境 jdk1.8
  2. MySQL 5.5
  3. Canal.deployer-1.1.5  
  4. RocketMQ-all-4.3.0-bin-release
  5. ElasticSearch-7.16.2-windows-x86_64     
  6. Redis-x64-5.0.14
  7. SpringBoot2.6 集成ElasticSearch & Redis做CRUD
  8. SpringBoot2.6 集成ElasticSearch 复杂查询案例

 
**系统结构**
    
    com.dataprocess.common ###  业务工具类 使用者可在该包下创建工具类
 
    com.dataprocess.core  ### 核心包 如果不是对项目结构，架构层面修改的代码  不建议修改该包下的代码

    com.dataprocess.core.common ### 核心工具类

    com.dataprocess.core.factory ### 核心工厂

    com.dataprocess.core.handler ### 核心处理模板

    com.dataprocess.databases ### 数据业务处理包 该包是专门为使用者提供的

    ### 该包下的第一个*包是数据库名称 第二个*包是数据表名称，使用者应 按照规范
    ### 在当前表名包下创建过滤器类 实现CoreHandler约束 进行后续业务操作
    com.dataprocess.databases.*.*  

    ###demo案例 godnan包是MySql中godnan库，u_user_tracking包是godnan库下的u_user_tracking表 
    com.dataprocess.databases.godnan.u_user_tracking 

    com.dataprocess.es.api ### es接口对外提供的查询

    com.dataprocess.es.dao ### es 到层面的操作

    com.dataprocess.es.entity ### es 涉及到实体 也叫文档
    
    com.dataprocess.es.index  ### es 文档索引
    
    com.dataprocess.init ###初始化 es索引

    com.dataprocess.mq ### 监听rocketmq推送的消息

    com.dataprocess.redis  ###redis 配置相关信息
**系统架构图** 

 ![](image/jiagou.png)
**系统安装依赖** 
1.  相关的资源地址:
2.  准备环境 必备条件本地应安装好jdk1.8 并且配置好环境变量
3.  以下安装链接是我个人博客园提供，描述比较简单，不保证您也能这么畅通，相信你也能遇到问题解决问题
3.  下载ElasticSearch并配置 https://www.cnblogs.com/langjunnan/articles/15798379.html
4.  下载Canal到本地并配置 https://www.cnblogs.com/langjunnan/articles/15798583.html
5.  下载RocketMQ并配置 https://www.cnblogs.com/langjunnan/articles/15798518.html
6.  下载Redis并启动 https://www.cnblogs.com/langjunnan/articles/15798670.html
7.  下载dataprocess也就是当前项目到本地，并且找到SpringBoot的运行类 点击运行
8.   成功案例介绍:    https://www.cnblogs.com/langjunnan/articles/15799285.html

**使用说明说** 

1.  在resources文件夹下 配置修改application.properties，application.yml两个文件的信息RocketMQ，Redis，Es配置
2.  com.dataprocess.databases.*.*  第一个*包 换成你想要链接数据库的名称 第二个*包换成库下的表名，然后随便创建一个类
该类需要继承 CoreHandler 类并且 该类要加上 @Component注解 由Spring容器管理 ，使用者可以重写 CoreHandler父类 下的 
insert update delete 方法 实现自己的逻辑，只要实在该包下创建的类，会形成一条链条 执行 ，配合完成使用者想要实现的
业务逻辑，
3. es 相关的查询服务 可以在 com.dataprocess.es 下编写
4. 找到DataProcessApplication类启动  确保项目能启动并且控制台不报错

**贡献代码（欢迎您的加入）** 

如果你有兴趣参与项目开发，请联系我个人邮箱: 1419052183@qq.com


**交流社区群** 

![](image/jiaoliu.png)
 