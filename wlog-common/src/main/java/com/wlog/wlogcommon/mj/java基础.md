1.jvm jdk jre有什么区别
jvm是 java虚拟机 是java实现跨平台的关键,不同的操作系统有不同的jvm实现,jvm负责将java字节码转换为特定平台的机器码并执行
jre是java运行时环境 包含了java程序所必须得库以及jvm
jdk是完整的sdk包括jre 编译器 等
简单来说就是 jdk包含 jre jre包含jvm

2.java有那些数据类型
数据类型分为两类 
byte short int long float double boolean char
1    2     4    8   4     8      1       2
int最大值是 2147483647
+1 变成 -2147483648
