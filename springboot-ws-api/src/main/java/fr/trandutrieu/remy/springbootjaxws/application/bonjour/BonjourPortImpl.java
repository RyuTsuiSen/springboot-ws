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
package fr.trandutrieu.remy.springbootjaxws.application.bonjour;

import javax.jws.WebService;

import org.apache.cxf.annotations.EndpointProperties;
import org.apache.cxf.annotations.EndpointProperty;
import org.apache.cxf.annotations.SchemaValidation;
import org.springframework.stereotype.Component;

import fr.trandutrieu.remy.socle.exceptions.BusinessException;
import fr.trandutrieu.remy.socle.exceptions.BusinessException.BusinessExceptionBuilder;
import fr.trandutrieu.remy.socle.inout.BusinessResponse;
import fr.trandutrieu.remy.socle.soap.WebserviceImpl;
import fr.trandutrieu.remy.springbootjaxws.application.hello.HelloCodeErreur;

/**
 * Examples code for spring boot with CXF services. HelloPortImpl is the
 * implementation for Hello interface. This class was generated by Apache CXF
 * 3.1.0 2015-05-18T13:02:03.098-05:00 Generated source version: 3.1.0
 */

@WebService
@SchemaValidation
@EndpointProperties(value = {
	    @EndpointProperty(key="schema-validation-enabled", value="true")
	})
@Component
public class BonjourPortImpl extends WebserviceImpl implements Bonjour {

    public BusinessResponse disBonjour(BonjourRequest request) {
    	BusinessResponse reponse = new BusinessResponse();
    	reponse.setReponse("Bonjour, Bienvenue a CXF Spring boot " + request.getName() + "!!!");
    	return reponse;
    }
    
    public BusinessResponse sayRuntimeException() {
    	throw new RuntimeException("une erreur grave est survenue");
    }

	@Override
	public BusinessResponse sayBusinessException() throws BusinessException {
    	throw BusinessExceptionBuilder.instance(HelloCodeErreur.CONTRAT_NON_TROUVE).build();
	}
    

}
