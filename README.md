# Material Design Library Management Software using JavaFX
This is a library management software developed using JavaFX programming language. The entire development video with explanation of each and every part (in real time) is available in my YouTube Channel [Genuine Coder Youtube Channel](https://www.youtube.com/playlist?list=PLhs1urmduZ29jTcE1ca8Z6bZNvH_39ayL).

## TODOs
- 将完成操作后的提醒换为下面这种
<p><a>
<img src="https://goo.gl/Ez1Eeq" alt="Alt text" title="Masonry Demo" style="max-width:100%;">
</a></p>

- 使用 JFoenix Dialog
- 修改图标
- 登录界面在等待主界面时，显示等待圈圈
- 使用 stage manager

## Bugs
- \#01 当开启了 book list 等窗口，关闭主窗口时，不会退出程序
- \#02 ~~member list 不能正常放大缩小~~ **Fixed!**
- \#03 ~~菜单栏中 View->Member 不是弹出列表窗口，而是新建 member窗口~~ **Fixed!**

## Releases
### 2018-01-25
- Fixed bug\#02
- 调整项目结构
- 修改 CSS
- 密码错误时，清空当前输入的密码
- 登录界面增加验证器，并修改 error-label 的样式

### 2018-01-24
- 去掉 Login 界面的标题栏
- 更新代码风格
- add_book add_member 确认时退出添加界面
- Login 界面始终置于最上层
- edit_book edit_member 确认时退出编辑界面
- Fixed bug\#03
- 使用 Maven

## Libraries Used
  * [JFoenix](https://github.com/jfoenixadmin/JFoenix) - JavaFX Material Design Library
  * [Apache Derby](https://db.apache.org/derby/) - Standalone Relational database
  * [Apache Commons](https://commons.apache.org/) - For creating SHA hash
  * [GSon](https://github.com/google/gson) - JSON Library. Used for storing configuration
  * [FontawesomeFX](https://bitbucket.org/Jerady/fontawesomefx) - Icon library

## Default Login Credentials
| Username  | Password |
| ------------- | ------------- |
| admin  | admin  |

## Screenshots
<p align="center">
  <img src=https://i.imgur.com/txmOeXS.png>
  <img src=https://i.imgur.com/Ezj7Bdh.png>
  <img src=https://i.imgur.com/YyK54nF.png>
  <img src=https://i.imgur.com/0wCfUjQ.png>
  <img src=https://i.imgur.com/E4OhaWl.png>
  <img src=https://i.imgur.com/QvcJS1d.png>
  <img src=https://i.imgur.com/O0LXqoK.png>
</p>
