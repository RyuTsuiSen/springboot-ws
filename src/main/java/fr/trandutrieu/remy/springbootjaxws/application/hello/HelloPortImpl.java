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

import fr.trandutrieu.remy.socle.exceptions.BusinessException;
import fr.trandutrieu.remy.socle.exceptions.BusinessException.BusinessExceptionBuilder;
import fr.trandutrieu.remy.socle.externalcall.AdapterCall.TYPE_APPEL;
import fr.trandutrieu.remy.socle.externalcall.ExternalCallResponse;
import fr.trandutrieu.remy.socle.externalcall.exceptions.ExternalCallCheckedException;
import fr.trandutrieu.remy.socle.webservices.inout.BusinessResponse;
import fr.trandutrieu.remy.socle.webservices.soap.WebserviceImpl;
import fr.trandutrieu.remy.springbootjaxws.application.hello.call.IT568;
import fr.trandutrieu.remy.springbootjaxws.application.hello.call.IT569;

@WebService
@Component
public class HelloPortImpl extends WebserviceImpl implements Hello {
	
	@Autowired
	private IT568 it568;
	
	@Autowired
	private IT569 it569;
	
    public BusinessResponse sayHello(String myname) {
    	BusinessResponse reponse = new BusinessResponse();
    	reponse.setReponse("Hello, Welcome " + myname + "!!!");
    	return reponse;
    }

    public BusinessResponse sayRuntimeException() {
    	throw new RuntimeException("une erreur grave est survenue");
    }

	@Override
	public BusinessResponse sayBusinessException()  throws BusinessException{
		throw BusinessExceptionBuilder.instance(HelloCodeErreur.CONTRAT_NON_TROUVE).build();
	}

	@Override
	public BusinessResponse sayHelloWithExternalCall() throws BusinessException{
		BusinessResponse reponse = new BusinessResponse();
		try {
			ExternalCallResponse execute = it568.execute(null, TYPE_APPEL.OK);
			reponse.setReponse("IT568 a cherche : " + execute.getReponse() + "!!!");
			it569.execute(null, TYPE_APPEL.OK);
		} catch (ExternalCallCheckedException e) {
			throw BusinessExceptionBuilder.instance(HelloCodeErreur.CONTRAT_NON_TROUVE).withThrowable(e).build();
		}
    	
		return reponse;
	}
	

	@Override
	public BusinessResponse sayHelloButNullPointer() throws BusinessException{
		try {
			it568.execute(null, TYPE_APPEL.ERREUR_DEV);
		} catch (ExternalCallCheckedException e) {
			throw BusinessExceptionBuilder.instance(HelloCodeErreur.CONTRAT_NON_TROUVE).withThrowable(e).build();
		}
    	BusinessResponse reponse = new BusinessResponse();
    	reponse.setReponse("Hello, Welcome to CXF Spring boot " + TYPE_APPEL.ERREUR_DEV + "!!!");
		return reponse;
	}

	@Override
	public BusinessResponse doExternalCallWithTimeOut() throws BusinessException{
		try {
			it568.execute(null, TYPE_APPEL.TIMEOUT);
		} catch (ExternalCallCheckedException e) {
			throw BusinessExceptionBuilder.instance(HelloCodeErreur.CONTRAT_NON_TROUVE).withThrowable(e).build();
		}
		throw new UnsupportedOperationException("ne devrait pas passer par ici");
	}

	@Override
	public BusinessResponse doExternalCallWithInterupted() throws BusinessException{
		try {
			it568.execute(null, TYPE_APPEL.EXECUTION_ISSUE);
		} catch (ExternalCallCheckedException e) {
			throw BusinessExceptionBuilder.instance(HelloCodeErreur.CONTRAT_NON_TROUVE).withThrowable(e).build();
		}
    	throw new UnsupportedOperationException("ne devrait pas passer par ici");
	}

	@Override
	public BusinessResponse doExternalCallWithExceptionChecked() throws BusinessException{
		try {
			it568.execute(null, TYPE_APPEL.CHECKED_EXCEPTION);
		} catch (ExternalCallCheckedException e) {
			throw BusinessExceptionBuilder.instance(HelloCodeErreur.CONTRAT_NON_TROUVE).withThrowable(e).build();
		}
		throw new UnsupportedOperationException("ne devrait pas passer par ici");
	}
    

}
