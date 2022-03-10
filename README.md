# 工程简介
##资料
[spring 文档](https://spring.io/guides)
[spring web](https://spring.io/guides/gs/serving-web-content/)
[leaf 依赖](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)
[Github OAuth](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)
## 工具
[git工具](https://git-scm.com/downloads)

##git常用命令
git init    使用当前目录作为git仓库
git status  查看上次提交后是否有修改
git add .   将当前项目全部加入到暂存区中去
git clone xxxx  克隆代码仓库
git commit  将缓存区内容写入到仓库中
git commit -m ‘提示信息’    提交代码到本地仓库
git log     查看日志
git branch  查看本地所有拥有的分支
git checkout -b 分支名字    创建一个新的分支，并切换到该分支下
git checkout 分支名字   切换到某一个分支
git merge 分支名字  将后面的分支合并到当前分支上
git push origin 标签名字    推送一个本地的标签到远程


##脚本
```sql
create table user
(
    id           int auto_increment
        primary key,
    account_id   varchar(50) null,
    name         varchar(50) null,
    token        char(36)    null,
    gmt_create   bigint      null,
    gmt_modified bigint      null
);
```