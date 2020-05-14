package com.groupservices.service1;

import org.springframework.stereotype.Component;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;

import common.FinancialTransaction;
import common.ServiceResponse;

@Component
public class MyQueueListener implements  ItemListener<Object> {
	HazelcastInstance hazelcastInstance;

	public MyQueueListener(){

		System.out.println("Constructor MyQueueListener");
		this.hazelcastInstance=Hazelcast.newHazelcastInstance();
	}

@Override
public void itemAdded(ItemEvent<Object> item) {
	System.out.println(item.getItem().toString());
	IMap<String, FinancialTransaction> transactionMap=this.hazelcastInstance.getMap("TransactionsMap");
	FinancialTransaction financialTransaction=transactionMap.get(item.getItem());
	System.out.println("Service one Financial Transaction" + financialTransaction.toString());
	ServiceResponse serviceResponse=new ServiceResponse();
	serviceResponse.setDecisionCode1("A");
	serviceResponse.setServiceName("Service1");
	serviceResponse.setServiceName("Service1 Approved");
	financialTransaction.getServiceResponseValues().put(item.getItem().toString(), serviceResponse)	;

}

@Override
public void itemRemoved(ItemEvent<Object> item) {
	// TODO Auto-generated method stub
	
}


}
