/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.trandutrieu.remy.springbootjaxws.application;

import javax.xml.ws.Endpoint;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.jmx.ConfigJMXManager;

import fr.trandutrieu.remy.springbootjaxws.application.bonjour.BonjourPortImpl;
import fr.trandutrieu.remy.springbootjaxws.application.hello.HelloPortImpl;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit.Level;
import fr.trandutrieu.remy.springbootjaxws.socle.webservice.ApplicationAbstract;


public class Application extends ApplicationAbstract{
    
	static {
		AbstractConfiguration configInstance = ConfigurationManager.getConfigInstance();
		ConfigJMXManager.registerConfigMbean(configInstance);
		
		ConfigurationManager.getConfigInstance().addConfigurationListener(new ConfigurationListener() {
			@Override
			public void configurationChanged(ConfigurationEvent event) {
				if(event.isBeforeUpdate()) {
					AbstractConfiguration manager = ConfigurationManager.getConfigInstance();
					Audit.trace(Level.DEBUG, "PROPERTIES", "Quelqu'un a changé la valeur de " + event.getPropertyName() + " : [old="+ manager.getString(event.getPropertyName(), "ERROR") +"] / [new="+ event.getPropertyValue()+"] ");
				}
			}
		}); 
	}
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
    @Autowired
    private HelloPortImpl hello;
    
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(this.bus,  hello);
        endpoint.publish("/Hello");
        return endpoint;
    }
    
    @Bean
    public Endpoint bonjourEndPoint(BonjourPortImpl helloPortImpl) {
        EndpointImpl endpoint = new EndpointImpl(this.bus, helloPortImpl);
        endpoint.publish("/Bonjour");
        return endpoint;
    }  
}
