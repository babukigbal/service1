package com.groupservices.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
public class Service1Application {

	public static void main(String[] args) {
		HazelcastInstance hazelcastInstance=Hazelcast.newHazelcastInstance();

		hazelcastInstance.getQueue("Service1Queue").addItemListener(new MyQueueListener(), true);

		SpringApplication.run(Service1Application.class, args);
	}

}
