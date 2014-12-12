/**
 * Created by chengt4 on 12/12/2014.
 */
function Person(firstName, lastName, age) {
    //私有变量：
//    var _firstName = firstName;
//    var _lastName = lastName;

    //公共变量:
    this.age = age;

    //方法：
    this.getName = function () {
        return(firstName + " " + lastName);
    };
    this.SayHello = function () {
        console.log("Hello, I'm " + firstName + " " + lastName + " " + this.age);
    };
};

var BillGates = new Person("Bill", "Gates", 53);
var SteveJobs = new Person("Steve", "Jobs", 55);

BillGates.SayHello();
SteveJobs.SayHello();
console.log(BillGates.getName() + " " + BillGates.age);
console.log(BillGates.firstName);     //这里不能访问到私有变量
console.log(BillGates);