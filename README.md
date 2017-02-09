# MinimalistWeather（待完善）


## 一、简介

**MinimalistWeather是Android平台上一款开源天气App，目前还在开发中。项目基于MVP架构，采用各种主流开源库实现。开发此项目主要是为展示各种开源库的使用方式以及Android项目的架构方案，并作为团队项目开发规范的一部分。**

采用的开源库包括：

* RxJava
* Retrofit2
* OKHttp3
* ORMLite
* Dagger2
* ButterKnife
* RetroLambda
* Stetho

**本项目还展示了：**

* MVP+RxJava在实际项目中的应用，MVP中RxJava生命周期的管理...；
* 上述罗列的各种开源框架的使用方法；
* Java8 Lambda表达式和Stream API的用法；
* 怎样适配Material Design；
* ToolBar、RecycleView、CardView、CoordinatorLayout等新控件的用法；
* Gradle的基本配置（包括签名打包、项目依赖等等）；
* 如何更好的管理Gradle依赖库的版本；
* 代码混淆配置；
* 怎样快速开发架构一款Android App。

## 二、项目结构设计图

![架构设计图](framework_minimalist_weather.png)

## 三、项目目录结构介绍

### App Module包结构

```Java
-me.baron.weather
  + activities	      //Activity在项目中不在负责View的职责，仅仅是一个全局的控制者，负责创建View和Presenter的实例
  + contracts       //契约类,用于统一管理View和Presenter的接口
  - models          //MVP中的Model层
    + db
    + http
    + preferences
    + repository    //-->Model层中的Data Repository模块，对Presenter层屏蔽数据来源和细节，并将Model成中的数据包装成Rx Observer
  + presenters      //MVP中的Presenter层
  + utils
  - views           //MVP中的View层
    + adapters
    + fragments
    + widget
  - AppConstants.java  //App全局常量
  - WeatherApp.java    //Application类
```

