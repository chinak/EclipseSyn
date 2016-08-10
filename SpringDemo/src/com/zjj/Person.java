package com.zjj;

public class Person {
	
	private IHelloMessage helloMessage;

	public IHelloMessage getHelloMessage() {
		return helloMessage;
	}

	public void setHelloMessage(IHelloMessage helloMessage) {
		this.helloMessage = helloMessage;
	}
	
	public String sayHello(){
		
		return helloMessage.sayHello();
	}

}
