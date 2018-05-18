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

## JS调用说明

cordova.plugins.TestPlugin.pay("此处输入TN",
result=>alert(result),
error=>alert("失败")
);

result：交易状态；

## 备注

使用cordova插件时。请先↓

declare let cordova: any;

因为没有集成钩子文件，插件集成后可能会遇到R文件引用错误。

使用AndroidStudio导入项目自动Build即可。

## 注意

本插件使用的是对方提供的SDK库。有依赖.so库，JAR包。

assets文件夹下应存在data.bin文件。
