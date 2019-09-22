## Array

#### 创建数组

```js
var fruits = ['Apple','Banana'];
console.log(fruits.length);
```

#### 通过索引访问数组元素

```js
var frist = fruits[0];
var last = fruits[fruts.length-1];
```

#### 遍历数组

```js
fruits.forEach(function (item,index,array){
    console.log(item,index);
})
```

#### 添加元素到数组末尾

```js
var newLength = fruits.push('Orange');
```
#### 删除数组末尾的元素

```js
var last = fruits.pop();
```

#### 删除数组最前面（头部）的元素

```js
var frist = fruits.shift();
```

#### 添加元素到数组的头部

```js
var newLength = fruits.unshift('Strawberry');
```

#### 找出某个元素在数组中的索引

```js
fruits.push('Mango');
var pos = fruits.indexOf('Banana');
```

#### 通过索引删除某个元素

```js
var removedItem = fruits.splice(pos,1);
```

#### 从一个索引位置删除多个元素

```js
var vegetables = ['Cabbage', 'Turnip', 'Radish', 'Carrot'];
console.log(vegetables); 
// ["Cabbage", "Turnip", "Radish", "Carrot"]

var pos = 1, n = 2;

var removedItems = vegetables.splice(pos, n);
// this is how to remove items, n defines the number of items to be removed,
// from that position(pos) onward to the end of array.

console.log(vegetables); 
// ["Cabbage", "Carrot"] (the original array is changed)

console.log(removedItems); 
// ["Turnip", "Radish"]
```

#### 复制一个数组

```js
var shallowCopy = fruits.slice(); // this is how to make a copy 
// ["Strawberry", "Mango"]
```

#### 非严格相等

|      |           |            |       |                    |                           | 被比较值B                   |                             |
| ---- | --------- | ---------- | ----- | ------------------ | ------------------------- | --------------------------- | --------------------------- |
|      |           | Undefinded | Null  | Number             | String                    | Boolean                     | Object                      |
| 被   | Undefined | true       | true  | false              | false                     | false                       | IsFalsy(B)                  |
| 比   | Null      | true       | true  | flase              | false                     | false                       | IsFalsy(B)                  |
| 教   | Number    | false      | false | A===B              | A===ToNumber(B)           | A===ToNumber(B)             | A===ToPrimitive(B)          |
| 值   | String    | false      | false | ToNumber(A) ===B   | A===B                     | ToNumber(B)===ToNumber(A)   | ToPrimitive(B)==A           |
| A    | Boolean   | false      | false | ToNumber(A) ===B   | ToNumber(B)===ToNumber(A) | A===B                       | ToNumber(A)==ToPrimitive(B) |
|      | Object    | false      | false | ToPrimitive(A) ==B | ToPrimitive(A) ==B        | ToPrimitive(A)==ToNumber(B) | A === B                     |

注：``ToNumber(A) `尝试在比较前将参数 A 转换为数字.`ToPrimitive(A)`通过尝试调用A 的`A.toString()` 和 `A.valueOf()` 方法，将参数 A 转换为原始值（Primitive）



































