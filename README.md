# Material Design Library Management Software using JavaFX
This is a library management software developed using JavaFX programming language. The entire development video with explanation of each and every part (in real time) is available in my YouTube Channel [Genuine Coder Youtube Channel](https://www.youtube.com/playlist?list=PLhs1urmduZ29jTcE1ca8Z6bZNvH_39ayL).

## TODOs
- 使用 snackBar
- 使用 JFoenix Dialog
- 修改图标
- 登录界面在等待主界面时，显示等待圈圈
- 使用 stage manager
- 添加多用户支持

## Bugs
- \#01 ~~当开启了 book list 等窗口，关闭主窗口时，不会退出程序~~ **Fixed!**
- \#02 ~~member list 不能正常放大缩小~~ **Fixed!**
- \#03 ~~菜单栏中 View->Member 不是弹出列表窗口，而是新建 member窗口~~ **Fixed!**
- \#04 ~~当开始输入的时候即检测有输入，即时去掉 input request 的错误提示~~ **Fixed!**
- \#05 ~~编辑 book 和 member 信息后不会自动更新~~ **Fixed!**

## Releases
### 2018-01-26
- Fixed bug\#01, 为 `mainStage` 添加 `setOnCloseRequest` 响应事件
- Fixed bug\#04, 将 `focusedProperty().addListener` 响应事件改为 `textProperty().addListener`
- 使用 `SnackBar` 展示添加 book 和 member 成功的消息
- 修改 `SnackBar` 的 css
- Fixed bug\#01, 重新实现：将 mainStage 设置为其他窗口的 owner
- 重新实现 `loadWindow`
- 使用 `SnackBar` 展示编辑 book 和 member 成功的消息
- 使用 `SnackBar` 展示删除 book 和 member 成功的消息
- 使用 `SnackBar` 展示编辑 settings 成功的消息
- Fixed bug\#05, 在(编辑)窗口关闭前触发 `WindowEvent.WINDOW_CLOSE_REQUEST` 事件
- 使用 `Modality.WINDOW_MODAL` 设置弹出子窗口时，父窗口不能响应

### 2018-01-25
- Fixed bug\#02, 为 `member list` 添加四个 Anchor
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
