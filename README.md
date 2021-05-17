# 玖言国学起名系统

仅限内部使用

## 页面统计

共10个页面，6个弹窗

+ 字库上传
    + 拼音选择弹窗

+ 字库管理
    + 编辑弹窗

+ 名库上传
    + 拼音选择弹窗

+ 名库管理
    + 编辑弹窗

+ 添加订单
    + 五行选择弹窗

+ 订单列表
    + 调整弹窗

+ 回访对象录入

+ 应回访列表

+ 待交付列表

+ 执行页面

## API接口统计

共37个API接口

+ 字库控制器 8个

+ 名库控制器 8个

+ 订单控制器 16个

+ 用户控制器 5个

## 数据库

+ 字库表
    + 11列

+ 名库表
    + 10列

+ 订单表
    + 24列

+ 订单调整表
    + 4列

+ 订单生成名表
    + 8列

+ 订单命盘表
    + 17列

+ 订单命局表
    + 14列

+ 回访用户表
    + 7列

## SQL Mapper

+ 字库模块
    + 12个

+ 名库模块
    + 13个

+ 订单模块
    + 16个

+ 回访用户模块
    + 4个

## 非常规点

+ 字库管理
    + 无音标拼音处理（难度★✰✰✰✰）

      为了禁用拼音判断的需求，需要保存不带音调的拼音。做一个映射，将有带音调的拼音映射成没有音调的拼音并保存。

+ 名库管理

+ 同上

+ 订单管理
    + 生成名字（难度★★★✰✰）

      生成名字功能需要考虑的问题有完整覆盖分支，减少数据库频繁读写，加快生成速度，高优先级名取完判断

    + 执行列表前端（难度★✰✰✰✰）

      排版与文字意见复制功能

## 代码统计

| 语言                | 文件 | 代码行数 | 注释行数 | 空白行数 |
| ------------------- | ---- | -------- | -------- | -------- |
| Java                | 63   | 2394     | 180      | 544      |
| Freemarker Template | 16   | 3576     | 0        | 34       |
| XML                 | 18   | 1502     | 0        | 59       |
| HTML                | 3    | 370      | 0        | 2        |
| CSS                 | 3    | 220      | 0        | 50       |
| JavaScript          | 1    | 148      | 1        | 45       |
| Bourne Shell        | 2    | 135      | 45       | 27       |
| Markdown            | 2    | 107      | 0        | 56       |
| DOS Batch           | 1    | 66       | 2        | 21       |
| Gradle              | 2    | 36       | 5        | 5        |
| YAML                | 2    | 22       | 3        | 3        |
| 总数                | 113  | 8576     | 236      | 846      |

## 定价

| 模块         | 价格 |
| ------------ | ---- |
| 字库管理     | 1900 |
| 名库管理     | 1900 |
| 订单管理     | 5400 |
| 回访用户管理 | 1000 |
| 总共         | 9200 |
