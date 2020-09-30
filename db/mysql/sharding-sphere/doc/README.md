# ShardingSphere

## 一、Sharding-JDBC

### a. shardingjdbc读写分离 单机Docker安装mysql集群(一主两从)

* #### 安装mysql主从集群

   1. docker 安装三台mysql 并查看ip
      ```
      docker pull centos/mysql-57-centos7
      
      docker run -d --name shardingjdbc_mysql_master -e MYSQL_USER=wjj -e MYSQL_PASSWORD=wzzst310 -e MYSQL_DATABASE=shardingjdbc -e MYSQL_ROOT_PASSWORD=Wzzst310@163.com -p 3307:3306 centos/mysql-57-centos7
      
      docker run -d --name shardingjdbc_mysql_slave1 -e MYSQL_USER=wjj -e MYSQL_PASSWORD=wzzst310 -e MYSQL_DATABASE=shardingjdbc -e MYSQL_ROOT_PASSWORD=Wzzst310@163.com -p 3308:3306 centos/mysql-57-centos7
      
      docker run -d --name shardingjdbc_mysql_slave2 -e MYSQL_USER=wjj -e MYSQL_PASSWORD=wzzst310 -e MYSQL_DATABASE=shardingjdbc -e MYSQL_ROOT_PASSWORD=Wzzst310@163.com -p 3309:3306 centos/mysql-57-centos7
      
      docker inspect --format='{{.NetworkSettings.IPAddress}}' shardingjdbc_mysql_master
      docker inspect --format='{{.NetworkSettings.IPAddress}}' shardingjdbc_mysql_slave1
      docker inspect --format='{{.NetworkSettings.IPAddress}}' shardingjdbc_mysql_slave2
       
      shardingjdbc_mysql_master 172.17.0.2
      shardingjdbc_mysql_slave1 172.17.0.3
      shardingjdbc_mysql_slave2 172.17.0.4
      
      PS: 切记 docker restart 重启可能会改变ip!
      ```

   2. 配置mysql master-slave模式

      ```
      ① 配置master
          docker exec -it --user root shardingjdbc_mysql_master /bin/bash
          查看mysql使用my.cnf文件
          ps aux|grep mysql|grep 'my.cnf'
          可以看到 --defaults-file=/etc/my.cnf 
          vi /etc/my.cnf
          在末尾处加上
      
          log-bin=mysql-bin
          # 二进制日志自动删除的天数
          expire_logs_days=5
          #给主节点设置一个id 随便设置 等下集群内不冲突就行了
          server_id=1
          #需要同步的二进制数据库名；
          binlog-do-db=shardingjdbc
          #不同步的二进制数据库名,如果不设置可以将其注释掉;
          binlog-ignore-db=information_schema
          binlog-ignore-db=mysql
          binlog-ignore-db=performance_schema
          binlog-ignore-db=sys
      
          可能出现中文乱码  无视或者去掉中文注释就行了
      
          保存退出vi
          退出容器exit
          通过重启docker容器是配置生效   ** 直接在容器内重启mysql有各种问题
          docker restart shardingjdbc_mysql_master
      
          docker exec -it --user root shardingjdbc_mysql_master /bin/bash
      
          执行命令 
          给master上增加用于和slave1进行同步的账户 账户slave1 密码123456
          给master上增加用于和slave2进行同步的账户 账户slave2 密码123456
          mysql
          grant replication slave,super,reload on *.* to 'slave1'@'172.17.0.3' identified by '123456';
          grant replication slave,super,reload on *.* to 'slave2'@'172.17.0.4' identified by '123456';
          flush privileges;
          show master status\G
          show slave status\G
          记下当前的
          File mysql-bin.000006 
          Position 1410
      
          退出mysql exit
          退出容器exit
      ② 配置slave1
          docker exec -it --user root shardingjdbc_mysql_slave1 /bin/bash
          vi /etc/my.cnf
          在末尾处加上
      
          log-bin=mysql-bin
          server_id=101
          expire_logs_days=5
      
          保存退出vi
          退出容器exit
          通过重启docker容器是配置生效
          docker restart shardingjdbc_mysql_slave1
          
          再次进入docker
          docker exec -it --user root shardingjdbc_mysql_slave1 /bin/bash
          mysql 
          执行命令
          change master to master_host='172.17.0.2', master_user='slave1', master_password='123456', master_log_file='mysql-bin.000006', master_log_pos=1410;
          start slave;
          show slave status\G
          show master status\G
          exit
          exit
          
      ③ 配置slave2
          docker exec -it --user root shardingjdbc_mysql_slave2 /bin/bash
          vi /etc/my.cnf
          在末尾处加上
      
          log-bin=mysql-bin
          server_id=102
          expire_logs_days=5
      
          保存退出vi
          退出容器exit
          通过重启docker容器是配置生效
          docker restart shardingjdbc_mysql_slave2
          
          再次进入docker
          执行命令
          change master to master_host='172.17.0.2', master_user='slave2', master_password='123456', master_log_file='mysql-bin.000006', master_log_pos=1410;
          start slave;
          show slave status\G
          show master status\G
          exit
          exit
      ```

   3. 连接mysql查看主从同步情况

      ```
      三种方式进入mysql命令行
      进入容器 输入mysql 此种方式免输入密码
        docker exec -it --user root shardingjdbc_mysql_master /bin/bash
        mysql
      或者
        密码是启动docker容器使用的Wzzst310@163.com
        以宿主机的ip进入换端口
        mysql -u root -h 宿主机ip -P 3307 -p
      或者
        以容器的docker0桥的ip进入   
        mysql -u root -h 172.17.0.2 -P 3306 -p 
      
      或者用msyql可视化工具navicat datagrip sqlyog workbench等 操作主节点 查看从节点同步情况
      ```


* #### shardingjdbc验证

  1. 查看项目中application-readwritesplitting.properties文件
  
     关键配置
  
     ```
     此处配置t_user表只插入到m0 也就是master中
     # 主库从库逻辑数据源定义 ds1为user_db
     spring.shardingsphere.sharding.master-slave-rules.ds1.master-data-source-name=m0
     spring.shardingsphere.sharding.master-slave-rules.ds1.slave-data-source-names[0]=s1
     spring.shardingsphere.sharding.master-slave-rules.ds1.slave-data-source-names[1]=s2
     
     # 配置user_db数据库里面t_user 专库专表
     #spring.shardingsphere.sharding.tables.t_user.actual-data-nodes=m$->{0}.t_user
     # t_user分表策略，固定分配至ds1的t_user真实表
     spring.shardingsphere.sharding.tables.t_user.actual-data-nodes=ds1.t_user
     ```
     
  2. 测试类ShardingJDBCApplicationTests
     
     ```java
     //======================测试垂直分库==================
         //添加操作
         @Test
         public void addUserDb() {
             for (int i = 0; i < 10; i++) {
                 User user = new User();
                 user.setUsername("lucymary");
                 user.setUstatus("a");
                 userMapper.insert(user);
             }
         }
         //查询操作
         @Test
         public void findUserDb() {
             QueryWrapper<User>  wrapper = new QueryWrapper<>();
             //设置userid值
             wrapper.eq("user_id",1310271995765641218L);
             User user = userMapper.selectOne(wrapper);
             System.out.println(user);
         }
     ```
     
     > 新增
     > ![image-20200928022050971](./img/readwrite_pic1.jpg)
     
     > 读取
     > ![image-20200928022345545](./img/readwrite_pic2.jpg)



## 二、Sharding-Proxy

### a. 下载安装

> wget http://apache.mirrors.hoobly.com/shardingsphere/4.1.1/apache-shardingsphere-4.1.1-sharding-proxy-bin.tar.gz
>
> tar xvf apache-shardingsphere-4.1.1-sharding-proxy-bin.tar.gz

### b. 进入conf目录下编辑配置文件	

1. #### 配置server.yaml

   ```yaml
   # 把注释放开 修改用户 密码
   authentication:
    users:
      root:
        password: Wzzst310@163.com
      wjj:
        password: wzzst310 
        authorizedSchemas: shardingjdbc
   
   props:
    max.connections.size.per.query: 1
    acceptor.size: 4  # The default value is available processors count * 2.
    executor.size: 4  # Infinite by default.
    proxy.frontend.flush.threshold: 128  # The default value is 128.
      # LOCAL: Proxy will run with LOCAL transaction.
      # XA: Proxy will run with XA transaction.
      # BASE: Proxy will run with B.A.S.E transaction.
    proxy.transaction.type: LOCAL
    proxy.opentracing.enabled: false
    proxy.hint.enabled: false
    query.with.cipher.column: true
    sql.show: false
    allow.range.query.with.inline.sharding: false
   
   ```
2. #### 配置config-sharding.yaml

   **优先复制mysql的驱动jar包到lib目录下,我上传了mysql-connector-java-8.0.21.jar包** 

   * proxy单表t_order映射master两个表t_order_0 t_order_1

     ```yaml
     schemaName: shardingjdbc
     
     dataSources:
      ds_0:
        url: jdbc:mysql://127.0.0.1:3307/shardingjdbc?serverTimezone=UTC&useSSL=false
        username: root
        password: Wzzst310@163.com
        connectionTimeoutMilliseconds: 30000
        idleTimeoutMilliseconds: 60000
        maxLifetimeMilliseconds: 1800000
        maxPoolSize: 50
     
     shardingRule:
      tables:
        t_order:
          actualDataNodes: ds_${0}.t_order_${0..1}
          tableStrategy:
            inline:
              shardingColumn: order_id
              algorithmExpression: t_order_${order_id % 2}
          keyGenerator:
            type: SNOWFLAKE
            column: order_id
      bindingTables:
        - t_order
      defaultDatabaseStrategy:
        inline:
          shardingColumn: user_id
          algorithmExpression: ds_${0}
      defaultTableStrategy:
        none:
     
     ```

     > 指定端口启动 bin/start.sh 3336
     >
     > 查看日志 tail -f  logs/stdout.log
     
* 分库配置
  
     ```
     ① master slave1 slave2 分别创建两个数据库shardingjdbc_1 shardingjdbc_2  好像创建数据库不会binlog同步
     		docker exec -it --user root shardingjdbc_mysql_master /bin/bash
     		vi /etc/my.cnf
     		加上
     		binlog-do-db=shardingjdbc_1
     		binlog-do-db=shardingjdbc_2
     		docker restart shardingjdbc_mysql_master
     ② slave1
     		docker exec -it --user root shardingjdbc_mysql_slave1 /bin/bash
     		mysql
     		stop slave;
     		start slave;
     		show slave status\G
     		exit
     		exit
     ③ slave2
     		docker exec -it --user root shardingjdbc_mysql_slave2 /bin/bash
     		mysql
     		stop slave;
     		start slave;
     		show slave status\G
     		exit
     		exit
     ④ master中新建shardingjdbc_1 和shardingjdbc_2库 均创建t_order0 t_order1表
     ```
   
     * 修改config-sharding.yaml
     
       ```
       schemaName: shardingjdbc
       
       dataSources:
        ds_1:
          url: jdbc:mysql://127.0.0.1:3307/shardingjdbc_1?serverTimezone=UTC&useSSL=false
          username: root
          password: Wzzst310@163.com
          connectionTimeoutMilliseconds: 30000
          idleTimeoutMilliseconds: 60000
          maxLifetimeMilliseconds: 1800000
          maxPoolSize: 50
        ds_2:
          url: jdbc:mysql://127.0.0.1:3307/shardingjdbc_2?serverTimezone=UTC&useSSL=false
          username: root
          password: Wzzst310@163.com
          connectionTimeoutMilliseconds: 30000
          idleTimeoutMilliseconds: 60000
          maxLifetimeMilliseconds: 1800000
          maxPoolSize: 50
       
       shardingRule:
        tables:
          t_order:
            actualDataNodes: ds_${1..2}.t_order_${0..1}
            tableStrategy:
              inline:
                shardingColumn: order_id
                algorithmExpression: t_order_${order_id % 2}
            keyGenerator:
              type: SNOWFLAKE
              column: order_id
        bindingTables:
          - t_order
        defaultDatabaseStrategy:
          inline:
            shardingColumn: user_id
            algorithmExpression: ds_${user_id % 2 +1 }
        defaultTableStrategy:
          none:
       ```
     
       > 指定端口重新启动 bin/start.sh 3336
       >
       > 查看日志 tail -f  logs/stdout.log 
       >
       > 通过 jps -m  找到刚才的sharding-proxy进程   
       >
       > 27455 Bootstrap 3336
       >
       > kill -9 27455      停止sharding-jdbc
       >
       > 指定端口重新启动 bin/start.sh 3336
       >
       > 查看日志 tail -f  logs/stdout.log
     
       >指定规则
       >
       >user_id 为偶数插入shardingjdbc_2库 user_id 为奇数插入shardingjdbc_1库
       >
       >order_id 为偶数插入t_order_0表 order_id 为奇数插入t_order_1表
     
       > 连接sharding-proxy
       >
       > 执行脚本
     
       ```sql
       use shardingjdbc;
       INSERT INTO `shardingjdbc`.`t_order`(`order_id`, `user_id`, `status`) 
       VALUES 
       (1, 1, 'init'),
       (2, 1, 'init'),
       (3, 2, 'destory'),
       (4, 2, 'destory');
       ```
     
       > 可以看到shardingjdbc里面有四条记录
       >
       > master中  shardingjdbc_1 shardingjdbc_2库中 t_order_0 t_order_1 均有一条记录
     
   * 读写分离
   
     * 修改config-master_slave.yaml文件
     
       ```
       schemaName: shardingjdbc_master_slave_db
       
       dataSources:
        master_ds:
          url: jdbc:mysql://127.0.0.1:3307/shardingjdbc?serverTimezone=UTC&useSSL=false
          username: root
          password: Wzzst310@163.com
          connectionTimeoutMilliseconds: 30000
          idleTimeoutMilliseconds: 60000
          maxLifetimeMilliseconds: 1800000
          maxPoolSize: 50
        slave_ds_0:
          url: jdbc:mysql://127.0.0.1:3308/shardingjdbc?serverTimezone=UTC&useSSL=false
          username: root
          password: Wzzst310@163.com
          connectionTimeoutMilliseconds: 30000
          idleTimeoutMilliseconds: 60000
          maxLifetimeMilliseconds: 1800000
          maxPoolSize: 50
        slave_ds_1:
          url: jdbc:mysql://127.0.0.1:3309/shardingjdbc?serverTimezone=UTC&useSSL=false
          username: root
          password: Wzzst310@163.com
          connectionTimeoutMilliseconds: 30000
          idleTimeoutMilliseconds: 60000
          maxLifetimeMilliseconds: 1800000
          maxPoolSize: 50
       
       masterSlaveRule:
        name: ms_ds
        masterDataSourceName: master_ds
        slaveDataSourceNames:
          - slave_ds_0
          - slave_ds_1
       ```
     
       **还是mysql -uroot -P3306 -p进入命令行查看  mysql客户端都有点问题**
     
       可以编辑server.yaml  sql.show设置为 true  再进行DML和DQL 查看具体的执行的sql
   
* 读写分离&分库分表 终极版
  
        * **将之前的config-sharding.yaml config-master_slave.yaml文件全部注释或删掉**
     * **新建config-sharding_master_slave_db.yaml文件 一定要已config-开头**
    
     ```
     schemaName: sharding_master_slave_db
     
     dataSources:
       ds1:
         url: jdbc:mysql://127.0.0.1:3307/shardingjdbc_1?serverTimezone=UTC&useSSL=false
         username: root
         password: Wzzst310@163.com
         connectionTimeoutMilliseconds: 30000
         idleTimeoutMilliseconds: 60000
         maxLifetimeMilliseconds: 1800000
         maxPoolSize: 50
       ds1_slave0:
         url: jdbc:mysql://127.0.0.1:3308/shardingjdbc_1?serverTimezone=UTC&useSSL=false
         username: root
         password: Wzzst310@163.com
         connectionTimeoutMilliseconds: 30000
         idleTimeoutMilliseconds: 60000
         maxLifetimeMilliseconds: 1800000
         maxPoolSize: 50
       ds1_slave1:
         url: jdbc:mysql://127.0.0.1:3309/shardingjdbc_1?serverTimezone=UTC&useSSL=false
         username: root
         password: Wzzst310@163.com
         connectionTimeoutMilliseconds: 30000
         idleTimeoutMilliseconds: 60000
         maxLifetimeMilliseconds: 1800000
         maxPoolSize: 50
       ds2:
         url: jdbc:mysql://127.0.0.1:3307/shardingjdbc_2?serverTimezone=UTC&useSSL=false
         username: root
         password: Wzzst310@163.com
         connectionTimeoutMilliseconds: 30000
         idleTimeoutMilliseconds: 60000
         maxLifetimeMilliseconds: 1800000
         maxPoolSize: 50
       ds2_slave0:
         url: jdbc:mysql://127.0.0.1:3308/shardingjdbc_2?serverTimezone=UTC&useSSL=false
         username: root
         password: Wzzst310@163.com
         connectionTimeoutMilliseconds: 30000
         idleTimeoutMilliseconds: 60000
         maxLifetimeMilliseconds: 1800000
         maxPoolSize: 50
       ds2_slave1:
         url: jdbc:mysql://127.0.0.1:3309/shardingjdbc_2?serverTimezone=UTC&useSSL=false
         username: root
         password: Wzzst310@163.com
         connectionTimeoutMilliseconds: 30000
         idleTimeoutMilliseconds: 60000
         maxLifetimeMilliseconds: 1800000
         maxPoolSize: 50
     
     shardingRule:
       tables:
         t_order:
           actualDataNodes: ms_ds${1..2}.t_order_${0..1}
           databaseStrategy:
             inline:
               shardingColumn: user_id
               algorithmExpression: ms_ds${user_id % 2 + 1}
           tableStrategy:
             inline:
               shardingColumn: order_id
               algorithmExpression: t_order_${order_id % 2}
           keyGenerator:
             type: SNOWFLAKE
             column: order_id
       bindingTables:
         - t_order
       defaultTableStrategy:
         none:
       masterSlaveRules:
         ms_ds1:
           masterDataSourceName: ds1
           slaveDataSourceNames:
             - ds1_slave0
             - ds1_slave1
           loadBalanceAlgorithmType: ROUND_ROBIN
         ms_ds2:
           masterDataSourceName: ds2
           slaveDataSourceNames: 
             - ds2_slave0
             - ds2_slave1
           loadBalanceAlgorithmType: ROUND_ROBIN
    ```
  
  > 连接sharding-proxy
  
  >  执行脚本
  
     ```
     TRUNCATE `sharding_master_slave_db`.`t_order`;
     INSERT INTO `sharding_master_slave_db`.`t_order`(`order_id`, `user_id`, `status`) 
     VALUES 
     (1, 1, 'init'),
     (2, 1, 'init'),
     (3, 2, 'destory'),
     (4, 2, 'destory');
     
     select * from sharding_master_slave_db.t_order where order_id = 1 and user_id = 1;
     select * from sharding_master_slave_db.t_order where order_id = 2 and user_id = 1;
     select * from sharding_master_slave_db.t_order where order_id = 3 and user_id = 2;
     select * from sharding_master_slave_db.t_order where order_id = 4 and user_id = 2;
     ```
  
   3 最终的conf配置文件单独提取出来了
  
  ![image-20200930034006868](./img/conf.jpg)


