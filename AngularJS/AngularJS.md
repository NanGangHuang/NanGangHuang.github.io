## AngularJS常见指令
w
**ng-app**  指令定义一个 AngularJS 应用程序

**ng-model**  指令把元素值（比如输入域的值）绑定到应用程序。

**ng-bind**  指令把应用程序数据绑定到 HTML 视图。

**ng-init**  指令初始化 AngularJS 应用程序变量，如 ng-init="firstName='John'"。

**ng-repeat**  指令会重复一个 HTML 元素,例如使用 ng-repeat 来循环数组。

**ng-disabled**  指令直接绑定应用程序数据到 HTML 的 disabled 属性。值为true时，按钮或输入框不可用。

**ng-show**  指令隐藏或显示一个 HTML 元素。

**ng-click**  指令定义了一个 AngularJS 单击事件。

**ng-hide**  指令用于设置应用的一部分 不可见 

**ng-include** 指令用来来包含 HTML 内容，如：`<div ng-include="'myUsers_Form.htm'"></div>`

## AngularJS 表达式

**{{expression}}**

## AngularJS Scope(作用域)

scope 是一个 JavaScript 对象，带有属性和方法，这些属性和方法可以在视图和控制器中使用。

*  根作用域 $rootScope  
   **$rootScope** 可作用于整个应用中。是各个 controller 中 scope 的桥梁。用 rootscope 定义的值，可以在各个 controller 中使用。

   ```
   <div ng-app="myApp" ng-controller="myCtrl">

     <h1>{{lastname}} 家族成员:</h1>

     <ul>
       <li ng-repeat="x in names">{{x}} {{lastname}}</li>
     </ul>

   </div>

   <script>
     var app = angular.module('myApp', []);

     app.controller('myCtrl', function($scope, $rootScope) {
       $scope.names = ["Emil", "Tobias", "Linus"];
       $rootScope.lastname = "Refsnes";
     });
   </script>

   ```

## AngularJS 过滤器

AngularJS 过滤器可以用来格式化数据，过滤器能够用在表达式和指令中。   
过滤器可以使用一个管道字符（|）添加到表达式和指令中。

| 过滤器    | 描述                     |
| --------- | ------------------------ |
| currency  | 格式化数字为货币格式。   |
| filter    | 从数组项中选择一个子集。 |
| lowercase | 格式化字符串为小写。     |
| orderBy   | 根据某个表达式排列数组。 |
| uppercase | 格式化字符串为大写。     |

*  过滤输入

   ```
   <div ng-app="myApp" ng-controller="namesCtrl">

     <p><input type="text" ng-model="test"></p>

     <ul>
       <li ng-repeat="x in names | filter:test | orderBy:'country'">
       {{ (x.name | uppercase) + ', ' + x.country }}
      </li>
     </ul>

   </div>
   

   angular.module('myApp', []).controller('namesCtrl', function($scope) {
       $scope.names = [
           {name:'Jani',country:'Norway'},
           {name:'Hege',country:'Sweden'},
           {name:'Kai',country:'Denmark'}
       ];
   }); 

   ```

## AngularJS 服务(Service)

*  什么是服务？

   在 AngularJS 中，服务是一个函数或对象，可在你的 AngularJS 应用中使用。  
   AngularJS 内建了30 多个服务。

*  **$location**服务  返回当前页面的 URL 地址。

   ```
    var app = angular.module('myApp', []);
      app.controller('customersCtrl', function($scope, $location) {
      $scope.myUrl = $location.absUrl();
    });

   ```

*  **$timeout**服务  AngularJS $timeout 服务对应了 JS window.setTimeout 函数。  

   ```
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $timeout) {
       $scope.myHeader = "Hello World!";
       $timeout(function () {
           $scope.myHeader = "How are you today?";
       }, 2000);
    });

   ```

*  **$interval**服务  AngularJS $interval 服务对应了 JS window.setInterval 函数。

   ```

   var app = angular.module('myApp', []);
   app.controller('myCtrl', function($scope, $interval) {
       $scope.theTime = new Date().toLocaleTimeString();
       $interval(function () {
           $scope.theTime = new Date().toLocaleTimeString();
       }, 1000);
   });

   ```

*  创建自定义服务  
   你可以创建自定义的访问，链接到你的模块中。

   创建名为hexafy 的访问:

   ```
   app.service('hexafy', function() {
       this.myFunc = function (x) {
           return x.toString(16);
       }
   });

   ```

*  使用自定义的的服务 hexafy 

   ```
   app.controller('myCtrl', function($scope, hexafy) {
       $scope.hex = hexafy.myFunc(255);
   });

   ```

*  **$http**是 AngularJS 中的一个核心服务，用于读取远程服务器的数据。  
   [http服务](https://www.w3cschool.cn/angularjs/angularjs-http.html)

## AngularJS Select(选择框)

*  使用 ng-options 创建选择框

   ```
   <div ng-app="myApp" ng-controller="myCtrl">

     <select ng-model="selectedName" ng-options="x for x in names">
     </select>

   </div>

   <script>
     var app = angular.module('myApp', []);
     app.controller('myCtrl', function($scope) {
       $scope.names = ["Google", "W3Cschool", "Taobao"];
     });
   </script>

   ```

*  使用ng-repeat 指令来创建下拉列表：

   ```

   <select>
     <option ng-repeat="x in names">{{x}}</option>
   </select>

   ```

ng-repeat 指令是通过数组来循环 HTML 代码来创建下拉列表，但 ng-options 指令更适合创建下拉列表，它有以下优势：  

使用 ng-options 的选项的一个对象， ng-repeat 是一个字符串。

选择的值在 key-value 对的 value 中, 这是它是一个对象:

   ```

   $scope.cars = {
   car01 : {brand : "Ford", model : "Mustang", color : "red"},
   car02 : {brand : "Fiat", model : "500", color : "white"},
   car03 : {brand : "Volvo", model : "XC90", color : "black"}
   };

   ```

在下拉菜单也可以不使用key-value 对中的 key , 直接使用对象的属性：

   ```

   <select ng-model="selectedCar" ng-options="y.brand for (x, y) in sites"></select>
   ```

## AngularJS API

以下列出了一些通用的 API 函数：

|API	|描述  |
|------|------|
|angular.lowercase()	|转换字符串为小写|
|angular.uppercase()	|转换字符串为大写|
|angular.isString()	|判断给定的对象是否为字符串，如果是返回 true。|
|angular.isNumber()	|判断给定的对象是否为数字，如果是返回 true。|

*  实例

   ```
   <div ng-app="myApp" ng-controller="myCtrl">
     <p>{{ x1 }}</p>
     <p>{{ x2 }}</p>
   </div>

   <script>
     var app = angular.module('myApp', []);
     app.controller('myCtrl', function($scope) {
       $scope.x1 = "JOHN";
       $scope.x2 = angular.isNumber($scope.x1);
     });
   </script>
   ```

## AngularJS 动画

AngularJS 提供了动画效果，可以配合 CSS 使用。

AngularJS 使用动画需要引入 angular-animate.min.js 库。
   ```
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular-animate.min.js"></script>
   ```
还需在应用中使用模型 ngAnimate：
```
<body ng-app="ngAnimate">
```

如果我们应用已经设置了应用名，可以把 ngAnimate 直接添加在模型中：

*  实例

```
   <body ng-app="myApp">

     <h1>隐藏 DIV: <input type="checkbox" ng-model="myCheck"></h1>
    
     <div ng-hide="myCheck"></div>
    
     <script>
       var app = angular.module('myApp', ['ngAnimate']);
     </script>
```

## AngularJS 依赖注入

**未完待续**

## AngularJS 路由

**未完待续** 

