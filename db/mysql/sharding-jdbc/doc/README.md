# ShardingSphere

## 一、ShardingJDBC

### 1. shardingjdbc读写分离 单机Docker安装mysql集群(一主两从)

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
          
      ② 配置slave2
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
     > ![image-20200928022345545](/Users/wjj/IdeaProjects/wjjjavalearn/db/mysql/sharding-jdbc/doc/img/readwrite_pic2.jpg)