package com.zjj;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource resource = new FileSystemResource("helloMessage.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		Person person = (Person) factory.getBean("person");//modify
		
		String s = person.sayHello();
		
		System.out.println("The person is currently saying: "+s);

	}

}
