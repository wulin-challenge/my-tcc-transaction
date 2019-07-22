# my-tcc-transaction
扩展于tcc-transaction,让使用更加便捷

# 使用教程
## 将tcc-transaction包装,并提供spring boot的整合,让使用更加的方便
1. 使用spring boot 加 dubbo时只需要应用如下依赖
```
    <dependency>
			<groupId>com.wulin</groupId>
			<artifactId>tcc-transaction-boot-dubbo-starter</artifactId>
		</dependency>
```
2. 使用案例参见 tcc-transaction-boot-dubbo-xxx系列
3. 修复的bug,传递的分支xid与root存储的分支xid不一致的bug
4. 特点: 提供更方便的使用方式,添加当调用的dubbo的接口采用 reference的方式注入时,其bean不被spring管理也不能进行aop拦截的的支持
