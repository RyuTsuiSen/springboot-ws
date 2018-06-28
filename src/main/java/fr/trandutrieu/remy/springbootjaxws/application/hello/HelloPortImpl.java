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

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

import fr.trandutrieu.remy.springbootjaxws.application.hello.call.IT568;
import fr.trandutrieu.remy.springbootjaxws.socle.exceptions.BusinessException;
import fr.trandutrieu.remy.springbootjaxws.socle.externalcall.AdapterCall.TYPE_APPEL;
import fr.trandutrieu.remy.springbootjaxws.socle.webservice.BusinessResponse;
import fr.trandutrieu.remy.springbootjaxws.socle.webservice.WebserviceImpl;

@WebService
@Component
public class HelloPortImpl extends WebserviceImpl implements Hello {
	
	@Autowired
	private IT568 it568;
	
    public BusinessResponse sayHello(String myname) {
    	DynamicStringProperty sampleProp = DynamicPropertyFactory.getInstance().getStringProperty("stringprop", "");
    	BusinessResponse reponse = new BusinessResponse();
    	reponse.setReponse("Hello, Welcome " + sampleProp.get() + "!!!");
    	return reponse;
    }

    public BusinessResponse sayRuntimeException() {
    	throw new RuntimeException("une erreur grave est survenue");
    }

	@Override
	public BusinessResponse sayBusinessException() throws BusinessException {
    	throw new BusinessException();
	}

	@Override
	public BusinessResponse sayHelloWithExternalCall() throws BusinessException {
		it568.execute(null, TYPE_APPEL.OK);
    	BusinessResponse reponse = new BusinessResponse();
    	reponse.setReponse(it568.getClass().getSimpleName() + " " + TYPE_APPEL.OK + "!!!");
		return reponse;
	}
	

	@Override
	public BusinessResponse sayHelloButNullPointer() throws BusinessException {
		it568.execute(null, TYPE_APPEL.ERREUR_DEV);
    	BusinessResponse reponse = new BusinessResponse();
    	reponse.setReponse("Hello, Welcome to CXF Spring boot " + TYPE_APPEL.ERREUR_DEV + "!!!");
		return reponse;
	}

	@Override
	public BusinessResponse doExternalCallWithTimeOut() throws BusinessException {
		it568.execute(null, TYPE_APPEL.TIMEOUT);
		throw new UnsupportedOperationException("ne devrait pas passer par ici");
	}

	@Override
	public BusinessResponse doExternalCallWithInterupted() throws BusinessException {
		it568.execute(null, TYPE_APPEL.EXECUTION_ISSUE);
    	throw new UnsupportedOperationException("ne devrait pas passer par ici");
	}

	@Override
	public BusinessResponse doExternalCallWithExceptionChecked() throws BusinessException {
		it568.execute(null, TYPE_APPEL.CHECKED_EXCEPTION);
		throw new UnsupportedOperationException("ne devrait pas passer par ici");
	}
    

}
