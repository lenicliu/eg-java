package com.lenicliu.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Main05 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// class User { // throw java.io.NotSerializableException
		class User implements Serializable {
			private static final long	serialVersionUID	= 2933636808737636317L;
			String						name;
			transient String			sex;

			public User(String name, String sex) {
				super();
				this.name = name;
				this.sex = sex;
			}

			public String getName() {
				return name;
			}

			public String getSex() {
				return sex;
			}
		}

		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("user.dat"));
		output.writeObject(new User("lenicliu", "male"));
		output.close();

		ObjectInputStream input = new ObjectInputStream(new FileInputStream("user.dat"));
		User user = (User) input.readObject();
		input.close();
		System.out.println("name is " + user.getName());
		System.out.println("sex is " + user.getSex());// output : sex is null
	}
}