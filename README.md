# RxMVP
**Tag01**
retrofit2+rxjava 实现带进度条请求

**Tag02**
Android MVP 构架初试

目前讨论```MVP```  ```MVVM``` 的架构也来越多，这种构架也很适合Android。研究MVP记录如下

### 原有的MVC构架
刚开始接触Android的时候会觉得Android的整个代码架构就是一个MVC。

- M : 业务层和模型层，相当与javabean和我们的业务请求代码
- V : 视图层，对应Android的layout.xml布局文件
- C : 控制层，对应于Activity中对于UI 的各种操作

看起来MVC架构很清晰，但是实际的开发中，请求的业务代码往往被丢到了Activity里面，大家都知道layout.xml的布局文件只能提供默认的UI设置，所以开发中视图层的变化也被丢到了Activity里面，再加上Activity本身承担着控制层的责任。所以Activity达成了MVC集合的成就，最终我们的Activity就变得越来越难看，从几百行变成了几千行。维护的成本也越来越高

### 新的MVP架构

- M : 还是业务层和模型层
- V : 视图层的责任由Activity来担当
- P : 新成员Prensenter 用来代理 C(control) 控制层

MVP与MVC最大的不同，其实是Activity职责的变化，由原来的C (控制层) 变成了 V(视图层)，不再管控制层的问题，只管如何去显示。控制层的角色就由我们的新人 Presenter来担当，这种架构就解决了Activity过度耦合控制层和视图层的问题。
