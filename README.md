## MinimalistWeather

**MinimalistWeather 是 Android 平台上一款开源天气 App ，目前还在开发中。项目基于 MVP 架构，采用各主流开源库实现。开发此项目主要是为展示各种开源库的使用方式以及 Android 项目的设计方案，并作为团队项目开发规范的一部分。**

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
* 如何快速开发一款结构清晰、可扩展性强的Android Application。

### 项目结构设计图

![架构设计图](framework_minimalist_weather.png)

### 项目包结构介绍

**App Module包结构**

```Java
-com.baronzhang.android.weather
    + activity	  //Activity不再负责View的职责，仅仅是一个全局的控制者，负责创建View和Presenter的实例
    + contract    //契约类，用于统一管理View和Presenter的接口
    - model       //MVP中的Model层
        + db
        + http
        + preference
        + repository //Model层中的Data Repository模块，对Presenter层屏蔽数据来源和细节，并将Model成中的数据包装成Rx Observer
    + presenter               //MVP中的Presenter层
    + util
    - view                    //MVP中的View层
        + adapter
        + fragment
        + widget
    - AppConstants.java        //App全局常量
    - WeatherApplication.java  //Application类
```

对于包结构的规范，大家不要照搬这个项目。由于这里只是个Demo，体量小，不会涉及到过多的业务，所以这样分包是合理的。但是在商业项目中，建议大家先按业务分包，然后再去遵循上面的分包规则。至于业务分包的粒度就需要大家根据各自的业务项目去合理把控了。拿安居客的来举例，我们可以这样分包：

```Java
-com.anjuke.android.app
    + chat            //微聊业务
    + newhouse        //新房业务
    - secondhouse     //二手房业务
        + activity    //Activity不再负责View的职责，仅仅是一个全局的控制者，负责创建View和Presenter的实例
        + contract    //契约类，用于统一管理View和Presenter的接口
        - model       //MVP中的Model层
            + db
            + http
            + preference
            + repository  //Model层中的Data Repository模块，对Presenter层屏蔽数据来源和细节，并将Model成中的数据包装成Rx Observer
        + presenter       //MVP中的Presenter层
        + util
        - view            //MVP中的View层
            + adapter
            + fragment
            + widget
    + renthouse           //租房业务
    - AppConstants.java   //App全局常量
    - AnjukeApp.java      //Application类
```

### 开源许可 [![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)

```
Copyright 2017 Baron Zhang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

