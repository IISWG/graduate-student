package com.example.graduatestudent.test;

import redis.clients.jedis.Jedis;

public class ConnectRedis {
     public static void main(String[] args) {
         //连接本地的 Redis 服务
        Jedis jedis = new Jedis("47.120.9.82",6379);
         // 如果 Redis 服务设置了密码，需要用下面这行代码输入密码
         jedis.auth("ZQBT1314.");
         System.out.println("连接成功");
         //查看服务是否运行
         System.out.println("服务正在运行: "+jedis.ping());
     }
 }