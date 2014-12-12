/**
 * Created by chengt4 on 12/12/2014.
 */
var a = "Hello Java";
var b = typeof a;
console.log("b: ", b);

var life = {};
for (life.age = 1; life.age <= 3; life.age++) {
    switch (life.age) {
        case 1:
            life.body = "卵细胞";
            life.say = function () {
                console.log(this.age + this.body)
            };
            break;
        case 2:
            life.tail = "尾巴";
            life.gill = "腮";
            life.body = "蝌蚪";
            life.say = function () {
                console.log(this.age + this.body + "-" + this.tail + "," + this.gill)
            };
            break;
        case 3:
            delete life.tail;
            delete life.gill;
            life.legs = "四条腿";
            life.lung = "肺";
            life.body = "青蛙";
            life.say = function () {
                console.log(this.age + this.body + "-" + this.legs + "," + this.lung)
            };
            break;
    }
    ;
    life.say();
}
;

var myfunc = function () {
    console.log("hello");
};

console.log(typeof(myfunc));
myfunc();


var anObject = {};  //一个对象
anObject.aProperty = "Property of object";  //对象的一个属性
anObject.aMethod = function () {//对象的一个方法
    console.log("Method of object")
};
//主要看下面：
console.log(anObject["aProperty"]);   //可以将对象当数组以属性名作为下标来访问属性
anObject["aMethod"]();          //可以将对象当数组以方法名作为下标来调用方法
for (var s in anObject)           //遍历对象的所有属性和方法进行迭代化处理
    console.log(s + " is a " + typeof(anObject[s]));


function WhoAmI()       //定义一个函数WhoAmI
{
    console.log("I'm " + this.name + " of " + typeof(this));
};

WhoAmI();   //此时是this当前这段代码的全局对象，在浏览器中就是window对象，其name属性为空字符串。输出：I'm of object

var BillGates = {name: "Bill Gates"};
BillGates.WhoAmI = WhoAmI;  //将函数WhoAmI作为BillGates的方法。
BillGates.WhoAmI();         //此时的this是BillGates。输出：I'm Bill Gates of object

var SteveJobs = {name: "Steve Jobs"};
SteveJobs.WhoAmI = WhoAmI;  //将函数WhoAmI作为SteveJobs的方法。
SteveJobs.WhoAmI();         //此时的this是SteveJobs。输出：I'm Steve Jobs of object

WhoAmI.call(BillGates);     //直接将BillGates作为this，调用WhoAmI。输出：I'm Bill Gates of object
WhoAmI.call(SteveJobs);     //直接将SteveJobs作为this，调用WhoAmI。输出：I'm Steve Jobs of object

BillGates.WhoAmI.call(SteveJobs);   //将SteveJobs作为this，却调用BillGates的WhoAmI方法。输出：I'm Steve Jobs of object
SteveJobs.WhoAmI.call(BillGates);   //将BillGates作为this，却调用SteveJobs的WhoAmI方法。输出：I'm Bill Gates of object

WhoAmI.WhoAmI = WhoAmI;     //将WhoAmI函数设置为自身的方法。
WhoAmI.name = "WhoAmI";
WhoAmI.WhoAmI();            //此时的this是WhoAmI函数自己。输出：I'm WhoAmI of function

({name: "nobody", WhoAmI: WhoAmI}).WhoAmI();    //临时创建一个匿名对象并设置属性后调用WhoAmI方法。输出：I'm nobody of object


function Person(name)   //带参数的构造函数
{
    this.name = name;   //将参数值赋给给this对象的属性
    this.SayHello = function () {
        console.log("Hello, I'm " + this.name);
    };   //给this对象定义一个SayHello方法。
};

function Employee(name, salary)     //子构造函数
{
    Person.call(this, name);        //将this传给父构造函数
    this.salary = salary;       //设置一个this的salary属性
    this.ShowMeTheMoney = function () {//添加ShowMeTheMoney方法。
        console.log(this.name + " $" + this.salary);
    };
};

var BillGates = new Person("Bill Gates");   //用Person构造函数创建BillGates对象
var SteveJobs = new Employee("Steve Jobs", 1234);   //用Empolyee构造函数创建SteveJobs对象

BillGates.SayHello();   //显示：I'm Bill Gates
SteveJobs.SayHello();   //显示：I'm Steve Jobs
SteveJobs.ShowMeTheMoney();   //显示：Steve Jobs $1234

console.log(BillGates.constructor == Person);  //显示：true
console.log(SteveJobs.constructor == Employee);  //显示：true

console.log(BillGates.SayHello == SteveJobs.SayHello); //显示：false


