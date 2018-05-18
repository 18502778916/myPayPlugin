# myPayPlugin
云闪付支付插件（Android版）

仅供个人项目使用

使用第三方SDK包

开发工程下执行以下命令导入本插件：

$ ionic cordova plugin add https://github.com/18502778916/myPayPlugin.git

已安装插件查看：

$ionic cordova plugin list

执行以下命令删本插件：

$ionic cordova plugin rm com.plugin.testPlugin

## 2 JS调用说明

cordova.plugins.TestPlugin.pay("此处输入TN",
result=>alert(result),
error=>alert("失败")
);

result：交易状态；
