本说明文档以启动graphiql项目为例【项目位于gitlab上summerIntern群组下，项目clone链接http://192.168.103.190：10080/SummerIntern/graphiql.git】

正文：

附：bistoury项目快速启动的github网址 https://github.com/qunarcorp/bistoury/blob/master/docs/cn/quick_start.md 

1. 下载bistoury的release版本 并解压
2. 下载graphiql项目，生成jar包。【bistoury不支持ide启动】
3. 在jar目录下使用java -jar ./demo-0.0.1-SNAPSHOT.jar 
4. 查询第3步的pid【ps -ef |grep demo-0.0.1-SNAPSHOT.jar】，本文中查到为23251.
注意：此时需要配置xxx/bistoury-ui-bin/conf/config.properties和/tmp/bistoury/releaseInfo.properties两个文件，具体配置方法参考github中的介绍。

5. 打开bistoury所在文件目录，运行命令【./quick_start.sh -c  org.springframework.web.servlet.DispatcherServlet -i 127.0.0.1  -p 23251  start】，若提示无法启动，可能是由于linux的版本的问题，需要将bistoury文件夹下所有以.sh结尾的文件 打开，然后把第一行改为【#!/bin/bash】，之后可以运行。
6.查询自己电脑的ip，在浏览器中输入 ip:9091进入bistoury的ui界面使用即可。【此处如果发生，只能看到欢迎界面的情况，可能是以由于ui的端口被占用导致的，修改端口即可】



注：org.springframework.web.servlet.DispatcherServlet为spring应用常用的的类，非spring类需要更改。

具体的生产部署，请参考【https://github.com/qunarcorp/bistoury/blob/master/docs/cn/deploy.md】
