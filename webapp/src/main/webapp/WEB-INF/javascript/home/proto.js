/**
 * Created by chengt4 on 12/12/2014.
 */
var Person =  //定义一个对象来作为原型类
{
    Create: function (name, age)  //这个当构造函数
    {
        this.name = name;
        this.age = age;
    },
    SayHello: function ()  //定义方法
    {
        console.log("Hello, I'm " + this.name);
    },
    HowOld: function ()  //定义方法
    {
        console.log(this.name + " is " + this.age + " years old.");
    }
};

function anyfunc(name, age) {//定义一个函数躯壳
    this.Create(name, age);
};
anyfunc.prototype = Person;     //将原型对象放到中转站prototype
var BillGates = new anyfunc("Bill", 55);  //新建对象的内置原型将是我们期望的原型对象
BillGates.SayHello();