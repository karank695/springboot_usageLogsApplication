package com.rchilli.entities;

public class TestObject {
private int age;
private String name;
public TestObject() {
	super();
	// TODO Auto-generated constructor stub
}
public TestObject(int age, String name) {
	super();
	this.age = age;
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Test [age=" + age + ", name=" + name + "]";
}

}
