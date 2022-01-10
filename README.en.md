 **# dataprocess** 

 **#### description** 

 **项目涉及到的技术点：**  
        Mysql+Canel+RocketMQ+SpringBoot+ElasticSearch+Redis

 **项目初衷：** 
        随着业务系统的不断迭代，数据库的数据量也随着逐渐增大，就导致业务系统日常运行情况下很多接口查询请求缓慢，甚至读取超时 , 为了整体优化项目，提高系统性能 希望在系统中对于部分业务 充分利用缓存操作或使用ElasticSearch搜索引擎来高效率的查询 那么问题来了，数据如何同步到ES，Redis或其他的容器中呢

 **数据同步：** 
        既然是搜索业务，读取缓存 那么对数据的实时性要求一定很高，所以这里我们使用 阿里巴巴开源的Canal让它去监听我们关系型数据库的主库例如 MySql-Master，
    实时读取数据库操作日志的变更，Canal就相当于关系型数据库的从库 Slave，Canal经过很多次迭代已经很成熟了，Canal自身又对RocketMq和kafka做了集成 可以把数据库订阅的日志直接输送到我们MQ当中，
    使用者只需要去监听MQ的消息队列即可，本次项目集成的是RocketMQ
 **dataprocess角色：** 
        Mysql----》Canal----》RocketMQ-----》dataprocess过滤器处理数据到-------》{ 过滤器1处理ES 增删改 创建索引等..，过滤器2处理 Redis 增删改查...，过滤器N处理...等等.}
        dataprocess主要充当着 监听从RocketMQ中的消息然后帮助使用者解析 该消息具体的操作  例如 某个库中的某个表中的每一条记录 发生了  添加 修改 删除 等操作 或者某个表发生了 创建/删除索引 等操作，并以过滤链的形式给使用者
    提供了 添加/修改/删除 等接口方法，使用者只需要 专注去做自己想要做的添加功能 删除功能  修改功能 就可以了

 
**#### Software Architecture**
 
    com.dataprocess.core.common ### 核心工具类

    com.dataprocess.core.factory ### 核心工厂

    com.dataprocess.core.handler ### 核心处理模板

    com.dataprocess.databases. ### 你链接的数据库名字.表名

    com.dataprocess.databases.godnan.p_log ###

    com.dataprocess.databases.godnan.u_user_tracking ###

    com.dataprocess.es.api ###

    com.dataprocess.es.dao ###

    com.dataprocess.es.entity ###

    com.dataprocess.mq ###

    com.dataprocess.common ###

**#### Installation** 

1.  xxxx
2.  xxxx
3.  xxxx

**#### Instructions** 

1.  xxxx
2.  xxxx
3.  xxxx

**#### Contribution** 

1.  Fork the repository
2.  Create Feat_xxx branch
3.  Commit your code
4.  Create Pull Request


**#### Gitee Feature** 

1.  You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2.  Gitee blog [blog.gitee.com](https://blog.gitee.com)
3.  Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4.  The most valuable open source project [GVP](https://gitee.com/gvp)
5.  The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6.  The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
