# Java TCP/IP Socket 编程 笔记

内容：

* 不介绍Java编程语言。需要熟悉Java语言的基本语法和类库，会用Java做程序开发。
* 不介绍协议。关注Socket抽象层为TCP/IP服务提供的接口。
* 介绍隐藏了通信细节的Java类库，如HTTPConnection。直接使用简单的字节流和显式字符编码，不对URL、URLConnection做介绍。
* 把清晰性放在首位，舍弃面向对象的思想。
* 不介绍使用Java实现自定义的本地套接字，不介绍实现了套接字的包装器如SocketImpl。
* 不介绍Java Applet。

## 1 简介

Java语言为实现程序的互相通信提供了许多有用的抽象应用程序接口（Application Programming Interface，API），这些接口被称为套接字（socket）。

### 1.1 计算机网络 分组报文 协议

主机（hosts）：运行应用程序的计算机。

通信信道（communication channel）：将字节序列从一个主机传输到另一个主机的一种手段。

路由器（routers）：将信息从一个通信信道传递或转发到另一个通信信道。

信息（information）：程序创建和解释的字节序列。在计算机网络环境中称为分组报文（packet）。

协议（protocol）：互相通信的程序间的一种约定，规定了分组报文的交换方式和意义。一组协议规定了分组报文的结构和如何对报文中的信息解析。

> 如超文本传输协议（HyperText Transfer Protocol，HTTP），解决服务器间传输超文本对象的问题。
>
> TCP/IP协议族（protocol suite）主要协议有互联网协议（Internet Protocol）、传输控制协议（Transmission Control Protocol）、用户数据报协议（User Datagram Protocol）。

#### TCP/IP协议族

网络层使用IP协议，使两个主机间的一系列通信信道和路由器看起来就像是单独一条主机到主机的信道。

IP协议提供了数据报服务，每组分组报文都由网络独立处理和分发。每个分组报文包含一个保存目的地址（address）的字段。IP协议是一个尽力而为（best-effort）的协议，试图分发每一个分组报文但是也可能发生报文丢失、顺序错乱或重复发送。

传输层使用TCP协议和UDP协议。TCP和UDP协议的功能之一是寻址，用端口号（port number）区分同一主机痛的不同应用程序，将报文发送到具体的应用程序。

TCP协议能够检测和恢复IP层可能发生的错误，它提供一个可信赖的字节流（reliable byte-stream）信道。TCP协议是一种面向连接（connection-oriented）的协议：在使用通信之前两个程序之间先建立一个TCP连接，交换握手消息（handshake message）。UDP协议也是尽力而为的。

### 1.2 关于地址

在TCP/IP协议中用互联网地址和端口号来定位一个应用程序。互联网地址的一个型式是IPv4，使用4个十进制数，每两个之间用点隔开，四个数字代表四个字节（即，每个数字为0~255）。端口号是一组16位的无符号二进制数，范围是1~65535（0被保留）。

每个版本的IP协议都定义了特殊地址，如IPv4的回环地址（loopback address）127.0.0.1，将报文返回到目标地址。

还有一些私有用途的地址，如所有以10或192.168开头的地址、第一个数是172且第二个数在16~31的地址。这些地方通过NAT（Network Address Translation）连接到互联网。NAT将一个接口中报文中的私有地址端口对映射成另一个接口中的共有地址端口对，使一小组主机能共享一个IP地址，内部地址不能通过公共互联网访问。

相关的类型的地址包括本地链接（link-local），由169.254开头，只能用来在连接到同一网络的主机之间进行通信，路由器不会转发这类地址的信息。

另一类地址是<span id="Multicast">多播（multicast）地址</span>，多播地址可与任意数量的目的地址关联，第一个数字在224~239之间。

### 1.3 关于名字

名字提供了一个间接层，使得IP地址的变化对用户不可见。

名字解析服务可以从各种各样的信息源获取信息。DNS（Domain Name System，域名系统）是一种分布式数据库，将名字映射到真实的互联网地址和其他信息上。DNS协议允许连接到互联网的主机通过TCP或UDP从DNS获取信息。本地配置数据库可以实现本地名字和互联网地址的映射。

### 1.4 客户端和服务器

客户端（client）是通信的发起者，服务器（server）等待客户端发起通信，并对其做出相应。两者共同组成了应用程序（application）。通常客户端需要知道服务器的名字，例如使用URL（Universal Resource Locator，统一资源定位符），再通过名字解析服务获取其对应的互联网地址。一些端口号被约定赋给一些应用程序，如21给FTP（File Transfer Protocol，文件传输协议），互联网的端口号授权机构维护了一个包含所有已约定使用的端口号列表（http://www.iana.org/assignments/port-numbers）。

### 1.5 什么是套接字

套接字是一种抽象层，应用程序通过它发送和接受数据。TCP/IP协议族中的主要socket类型为流套接字（stream socket）和数据报套接字（datagram socket）。流套接字将TCP作为其端对端协议，提供了可信赖的字节流服务。数据报套接字使用UDP协议，提供了“尽力而为”的数据报服务。一个TCP/IP套接字由一个互联网地址、一个端对端协议和一个端口号唯一确定。

## 2 基本套接字

首先通过InetAddress类和SocketAddress类示范Java应用程序如何识别网络主机，然后剧一个使用TCP协议的客户端和服务器端例子展示Socket类和ServerSocket类的用法。举了一个使用UDP协议的客户端和服务器例子来展示DatagramSocket类的用法。

### 2.1 套接字地址

示范InetAddress类的用法：

```java
import java.util.Enumeration;
import java.net.*;

public class InetAddressExample {

    public static void main(String[] args){

        //Get the network interfaces and associated addresses for this host
        try{
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
            if (interfaceList == null){
                System.out.println("--No interfaces found--");
            } else {
                while (interfaceList.hasMoreElements()){
                    NetworkInterface iface = interfaceList.nextElement();
                    System.out.println("Interface "+ iface.getName() + ":");
                    Enumeration<InetAddress> addrList = iface.getInetAddresses();
                    if (!addrList.hasMoreElements()){
                        System.out.println("\t(No addresses for this interface)");
                    }
                    while (addrList.hasMoreElements()) {
                        InetAddress address = addrList.nextElement();
                        System.out.print("\tAddress "
                                + ((address instanceof Inet4Address ? "(v4)"
                                : (address instanceof Inet6Address ? "(v6)" : "(?)"))));
                        System.out.println(": " + address.getHostAddress());
                    }
                }
            }
        } catch (SocketException se) {
            System.out.println("Error getting network interfaces:" + se.getMessage());
        }

        // Get name(s)/address(es) of hosts given on command line
        for (String host : args){
            try{
                System.out.println(host + ": ");
                InetAddress[] addressList = InetAddress.getAllByName(host);
                for (InetAddress address : addressList){
                    System.out.println("\t" + address.getHostName() + "/" +address.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.out.println("\tUnable to find address for " + host);
            }
        }
    }
}
```

第10行，静态方法getNetworkInterfaces()返回一个列表，包含该主机的每一个接口对应的NetworkInterface类实例。

第11行，空列表检测，通常回环接口总是存在的，列表检测不为空。

第14行，打印接口地址，

* 第16行，打印接口名
* 第21行，打印接口地址（可能是IPv4，IPv6，混合列表）

30行，捕获异常。

#### InetAddress：创建和访问

```java
static InetAddress[] getAllByName(String host);
static InetAddress getByName(String host);
static InetAddress getLocalHost();
byte[] getAddress();
```

这些静态方法返回一个可以传递给另一个套接字方法来指定主机的实例。

`getAddress()`方法返回一个字节数组，代表地址的二进制形式。如果是IPv4数组为4字节，IPv6数组为16字节。

#### InetAddress：字符串显示

```java
String toString();
String getHostAddress();
String getHostName();
String getCanonicalHostName();
```

`toString()`重写了`Object`类的方法，返回如*hostname.example.com/192.0.2.127*形式的字符串。

`getHostAddress()`给出单一的数字地址，最后两个方法只返回主机名。`getCanonicalHostName()`总是尝试对地址进行解析，获得主机域名全称（fully qulified domain name），而`getHostName()`可以返回创建这个实例时使用的主机名。当解析失败时这两个方法都返回数字地址。

#### InetAddress：检测属性

```java
boolean isAnyLocalAddress();
boolean isLinkLocalAddress();
boolean isLoopbackAddress();
boolean isMulticastAddress();
boolean isMCGlobal();
boolean isMCLinkLocal();
boolean isMCNodeLocal();
boolean isMCOrgLocal();
boolean isMCSiteLocal();
boolean isReachable(int timeout);
boolean isReachable(NetworkInterface netif, int ttl, int timeout);
```

前三个方法检查地址是否属于任意本地地址、本地链接地址、回环地址；第四个方法检查地址是否属于[多播地址](#Multicast)

#### NetworkInterface：创建、获取信息

### 2.2 TCP套接字

