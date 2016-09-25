package com.zjj;

import java.math.BigInteger;

public class HelloWorl {
	
	class Storage{
		int data1;
		float data2;
		Storage(int data1,float data2){
			this.data1=data1;
			this.data2=data2;
		}
		void modify(int value){
			this.data1=this.data1/value;
			this.data2=this.data2/value;
			value = value+2;
		}
	}
	 
	
    

	public static void main(String[] args) {
		
		int x=0;
		if(x>0)
			System.out.println("one");
			System.out.println("two");
				
		
			
	}
}







