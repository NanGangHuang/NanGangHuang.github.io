## java8官方文档 - 匿名内部类

###### 声明匿名内部类

```java
public class HelloWorldAnonymousClasses{
	interface HelloWorld{
		public void greet();
		public void greetSomeone(String someone);
	}
	
	public void sayHello(){
		class EnglishGreeting implements HelloWorld{		
			String name = "world";
			public void greet(){
				greetSomeone("world");
			}
			public void greetSomeone(String someone){
				name = someone;
				System.out.println("Hello"+name);
			}
		}
		HelloWorld englishGreeting = new EnglishGreeting();
		
		HelloWorld frenchGreeting = new HelloWorld(){
			String name = "tout le monde";
			public void greet(){
				greetSomeone("tout le monde");
			}
			public void greetSomeone(String someone){
				name = someone;
				System.out.println("Salut " + name);
			}
		};
		HelloWorld spanishGreeting = new HelloWorld(){
			String name = "mudo";
			public void greet(){
				greetSomeone("mundo");
			}
			public void greetSomeone(String someone){
				name = someone;
				System.out.println("Hola, " + name);
			}
		};
		englishGreeting.greet();
		frenchGreeting.greetSomeone("Fred");
		spanishGreeting.greet();
	}
	
	public static void main(String... args){
		HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
		myApp.sayHello();
	}
}

```

## Lambda表达式

> [java8官方文档—Lambda表达式](https://www.jianshu.com/p/f76f0a0dc5c1)

