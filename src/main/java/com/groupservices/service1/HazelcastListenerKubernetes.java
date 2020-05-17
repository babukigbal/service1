package com.groupservices.service1;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;

@Configuration
public class HazelcastListenerKubernetes {

	@Bean
	public Config hazelCastConfig() {
		System.out.println("Inside openshift profile Hazelcast Listener- Updated");

		Config config = new Config();
		config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
		config.getNetworkConfig().getJoin().getKubernetesConfig().setEnabled(true)
		      .setProperty("namespace", "orch-project")
		      .setProperty("service-name", "hzport")
//			  .setProperty("kubernetes-master","5701")
			  .setProperty("service-port","5701");
		
		return config;
	}
	
}
