## LittleKotlinMall
学习kotlin mvp 项目  

### 模块化
相对独立业务拆分成"块"，单独开发调试，拼接业务模块，组装App

why
1. 业务分离，解耦
2. 通用化，代码复用

实现模块化
1. 公共模块抽取
2. 业务模块抽取
3. 主工程组装业务模块

模块之间通讯
1. 跨模块跳转
2. 跨模块接口调用
3. ARouter路由框架（阿里巴巴）

### Mvp  view -- presenter - model

### 学习流程
1. 建立BaseLibrary -- 存放基础库，基础控件，工具类，父类
2. 建立Provider -- 存放和业务相关的共用属性，事件，key值
3. 建立UserCenter -- 用户模块


## 服务器代码和数据库表结构

[Kotlin_Server](https://github.com/maoai-xianyu/Kotlin_Server)



## 用户模块

1. 注册功能
2. 登录功能

## 商品模块
1. 展示商品

## 订单模块
1. 购买商品，下订单
2. 订单显示

## 支付模块
1. 支付宝支付

## 消息模块
1. 极光推送

> 关于激光推送，

因为没有改服务器代码，用的远程的服务器，上面的是课程视频老师的APPID,
1. 可以自行将项目的 applicationId 改为 "com.kotlin.mall"  JPUSH_APPKEY 改为 "7f3d74ec9161e033d723f585"

```

APP 

build.gradle

android {
    compileSdkVersion compile_sdk_version
    buildToolsVersion build_tools_version
    defaultConfig {
        applicationId "com.kotlin.mall"
        minSdkVersion min_sdk_version
        targetSdkVersion target_sdk_version
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

        manifestPlaceholders = [
                JPUSH_PKGNAME: applicationId,
                JPUSH_APPKEY : "7f3d74ec9161e033d723f585", //JPush 上注册的包名对应的 Appkey.
                JPUSH_CHANNEL : "developer-default", //暂时填写默认值即可.
        ]
    }
    buildTypes {

        debug {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    //解决错误： com.google.code.findbugs:jsr305
    configurations.all {
        resolutionStrategy.force 'com.google.code.findbugs:jsr305:1.3.9'
    }
}
         
MessageCenter


build.gradle

android {

    compileSdkVersion compile_sdk_version
    buildToolsVersion build_tools_version

    defaultConfig {
        minSdkVersion min_sdk_version
        targetSdkVersion target_sdk_version
        versionCode 1
        versionName "1.0"
        ndk {
            //选择要添加的对应 cpu 类型的 .so 库。
            abiFilters 'armeabi', 'armeabi-v7a', 'arm64-v8a'
            // 还可以添加 'x86', 'x86_64', 'mips', 'mips64'
        }

        manifestPlaceholders = [
                JPUSH_PKGNAME : "com.kotlin.mall",
                JPUSH_APPKEY : "7f3d74ec9161e033d723f585", //JPush 上注册的包名对应的 Appkey.
                JPUSH_CHANNEL : "developer-default", //暂时填写默认值即可.
        ]
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}


<receiver
        android:name=".receiver.MessageReceiver"
        android:enabled="true">
     <intent-filter>
           <action android:name="cn.jpush.android.intent.REGISTRATION" />
           <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED" />
           <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED" />
           <action android:name="cn.jpush.android.intent.NOTIFICATION_OPENED" />
           <action android:name="cn.jpush.android.intent.NOTIFICATION_CLICK_ACTION" />
           <action android:name="cn.jpush.android.intent.CONNECTION" />

           <category android:name="com.kotlin.mall" />
     </intent-filter>
</receiver>

```
2. 或者可以切换我的分支：[feature/可以点击通知打开订单页面](https://github.com/maoai-xianyu/LittleKotlinMall/tree/feature/%E5%8F%AF%E4%BB%A5%E7%82%B9%E5%87%BB%E9%80%9A%E7%9F%A5%E6%89%93%E5%BC%80%E8%AE%A2%E5%8D%95%E9%A1%B5%E9%9D%A2)，进行查看


