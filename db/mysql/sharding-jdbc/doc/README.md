# ShardingSphere

## ShardingJDBC

### Docker安装mysql集群验证shardingjdbc

#### 安装mysql主从集群

* docker run -d --name shardingjdbc_mysql_master -e MYSQL_USER=wjj -e MYSQL_PASSWORD=wzzst310 -e MYSQL_DATABASE=shardingjdbc -e MYSQL_ROOT_PASSWORD=Wzzst310@163.com -p 3307:3306 centos/mysql-57-centos7

* docker run -d --name shardingjdbc_mysql_slave1 -e MYSQL_USER=wjj -e MYSQL_PASSWORD=wzzst310 -e MYSQL_DATABASE=shardingjdbc -e MYSQL_ROOT_PASSWORD=Wzzst310@163.com -p 3308:3306 centos/mysql-57-centos7
* docker run -d --name shardingjdbc_mysql_slave2 -e MYSQL_USER=wjj -e MYSQL_PASSWORD=wzzst310 -e MYSQL_DATABASE=shardingjdbc -e MYSQL_ROOT_PASSWORD=Wzzst310@163.com -p 3309:3306 centos/mysql-57-centos7

#### 配置mysql master-slave模式

* 查看mysql容器ip
  ```
  docker inspect --format='{{.NetworkSettings.IPAddress}}' shardingjdbc_mysql_master
  docker inspect --format='{{.NetworkSettings.IPAddress}}' shardingjdbc_mysql_slave1
  docker inspect --format='{{.NetworkSettings.IPAddress}}' shardingjdbc_mysql_slave2
    
  shardingjdbc_mysql_master 172.17.0.3
  shardingjdbc_mysql_slave1 172.17.0.4
  shardingjdbc_mysql_slave2 172.17.0.5
  ```

  
  
* 配置主从同步

  * 配置master

    ```
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
    grant replication slave,super,reload on *.* to 'slave1'@'172.17.0.4' identified by '123456';
    grant replication slave,super,reload on *.* to 'slave2'@'172.17.0.5' identified by '123456';
    flush privileges;
    show master status\G
    show slave status\G
    记下当前的
    File mysql-bin.000002 
    Position 2312
    
    退出mysql exit
    退出容器exit
    ```
  
  * 配置slave1
  
    ```
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
    ```
    
   * 配置slave2
  
     ```
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
     ```
  
  * 进入slave1命令行进行设置
  
    ```
    mysql -u root -h 172.17.0.4 -P 3306 -p
    执行命令
    change master to master_host='172.17.0.3', master_user='slave1', master_password='123456', master_log_file='mysql-bin.000002', master_log_pos=2312;
    
    start slave;
    show slave status\G
    show master status\G
    exit
    ```
    
   * 进入slave2命令行进行设置
     
     ```
     mysql -u root -h 172.17.0.5 -P 3306 -p
     执行命令
     change master to master_host='172.17.0.3', master_user='slave2', master_password='123456', master_log_file='mysql-bin.000002', master_log_pos=2312;
     
     start slave;
     show slave status\G
     show master status\G
     exit
     ```
     
  * 进入master命令行进行设置
  
    ```
    密码是启动docker容器使用的Wzzst310@163.com
    以宿主机的ip进入 换端口
    mysql -u root -h 宿主机ip -P 3307 -p
    或者
    以容器的docker0桥 ip进入   
    mysql -u root -h 172.17.0.3 -P 3306 -p 
    ```
    
   * 其他一些命令
  
     ```
     停止从节点拷贝
     stop slave;  或者reset slave;
     change master to master_host='172.17.0.3', master_user='slave2', master_password='123456';
     查看从节点状态
     start slave;
     查看主节点状态
     show master status;
     查看bin-log日志是否开启
     show variables like '%log_bin%';
     ```
  
     
  
       
  
    

