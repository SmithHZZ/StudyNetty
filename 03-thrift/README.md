### Thrift

#### 简介
    最初由Facebook研发，主要用于各个服务之间的RPC通信，支持跨语言，
    常用的语言C++，Java，Python、PhpRuby，Erlang、Perl、Haskel，C#,Cocoa,javascript,
    Nodejs,Smalltalk, and OCaml都支持
    
    Thrift是典型的CS结构，客户端和服务端可以使用不同语言开发，
    那就一定要有中间语言来关联客户端和服务端的语言，这种语言就是IDL
    Interface Description Language

#### 数据类型
    thrift不支持无符号类型，很多编程语言没有无符号类型，比如Java
    byte i16 i32 i64 double string
    容器类型：
        集合中的元素可以是除了Service之外的任何类型
        list：有序列表可重复
        set：无序集合不可重复
        map：key-value 字典结构
        struct
        enum
    exception
    
    服务：相当于Java中创建Interface一样，
    创建的service经过代码生成命令之后会生成客户端和服务端的框架代码，
    定义形式如下：
```idl
service HelloService {
    string doAction(1:string name, 2:i32 age);
}
```
    支持类似C++的typedef
    typedef i32 int
    typedef i64 long
    
    支持常量定义，使用const
    const i32 MAX_VALUE = 255
    
    命名空间相当于Java中的package，主要目的是组织代码。
    thrift使用关键词namespace定义命名空间
    namespace java study.netty.thrift
    格式：namespace 语言名 路径
    
    注释支持shell风格的注释，支持C/C++风格的注释
    # // /**/
    
    可选与必选
    required optional
    分别用于表示对应的字段是必填的还是可选的
    
    
        
#### 工作原理
    数据传输使用socket（多种语言均受支持），
    数据再以特定的格式(string等)发送，接收方语言再进行解析
    
    
    