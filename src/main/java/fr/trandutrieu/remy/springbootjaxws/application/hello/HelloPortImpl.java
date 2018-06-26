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
package fr.trandutrieu.remy.springbootjaxws.application.hello;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.trandutrieu.remy.springbootjaxws.socle.BusinessResponse;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit.Level;
import fr.trandutrieu.remy.springbootjaxws.socle.exceptions.BusinessException;

/**
 * Examples code for spring boot with CXF services. HelloPortImpl is the
 * implementation for Hello interface. This class was generated by Apache CXF
 * 3.1.0 2015-05-18T13:02:03.098-05:00 Generated source version: 3.1.0
 */

@WebService
@HandlerChain(file = "../../../../../../handlers.xml")
public class HelloPortImpl implements Hello {

	
    public BusinessResponse sayHello(String myname) {
    	BusinessResponse reponse = new BusinessResponse();
    	Audit.trace(Level.INFO, "Executing operation sayHello" + myname);
    	reponse.setReponse("Hello, Welcome to CXF Spring boot " + myname + "!!!");
    	reponse.setCode("000");
    	reponse.setLabel("OK");
    	return reponse;
    }
    
    public BusinessResponse sayRuntimeException() {
    	Audit.trace(Level.INFO, "Executing operation sayRuntimeException");
    	throw new RuntimeException("une erreur grave est survenue");
    }

	@Override
	public BusinessResponse sayBusinessException() throws BusinessException {
		Audit.trace(Level.INFO, "Executing operation sayBusinessException");
    	throw new BusinessException();
	}
    

}