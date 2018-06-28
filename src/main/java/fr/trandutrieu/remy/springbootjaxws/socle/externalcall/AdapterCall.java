package fr.trandutrieu.remy.springbootjaxws.socle.externalcall;

import java.time.Duration;
import java.time.Instant;

import com.netflix.hystrix.exception.HystrixRuntimeException;

import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit;
import fr.trandutrieu.remy.springbootjaxws.socle.audit.Audit.Level;
import fr.trandutrieu.remy.springbootjaxws.socle.exceptions.BusinessException;

public abstract class AdapterCall {

	private static final String ADAPTER_CALL = "ADAPTER CALL";

	public ExternalCallResponse execute(ExternalCallRequest request, TYPE_APPEL typeAppel) throws BusinessException {
		Instant start = Instant.now();
		
		try {
			ExternalCall external = new ExternalCall(typeAppel);
			ExternalCallResponse response =  external.execute();
			traitement(typeAppel);
			handleCheckedException(typeAppel);
			Duration duration = Duration.between(start, Instant.now());
			Audit.trace(Level.INFO, ADAPTER_CALL, this.getClass().getSimpleName() + " a repondu OK execTime=" + duration.toMillis() + "ms");
			return response;
		}
		catch(BusinessException e) {
			Duration duration = Duration.between(start, Instant.now());
			Audit.trace(Level.ERROR, ADAPTER_CALL, this.getClass().getSimpleName() + " a repondu avec un code erreur execTime=" + duration.toMillis() + "ms");
			throw e;
		}
		catch(HystrixRuntimeException e) {
			Duration duration = Duration.between(start, Instant.now());
			Audit.trace(Level.ERROR, ADAPTER_CALL, this.getClass().getSimpleName() + " s'est interrompu execTime=" + duration.toMillis() + "ms");
			throw e;
		}
	}

	private void traitement(TYPE_APPEL typeAppel) {
		if (typeAppel.equals(TYPE_APPEL.ERREUR_DEV)) {
			Object objetNull = null;
			objetNull.equals(null);
		}
	}

	protected void handleCheckedException(TYPE_APPEL typeAppel) throws BusinessException{
		if(typeAppel.equals(TYPE_APPEL.CHECKED_EXCEPTION)) {
			BusinessException e = new BusinessException("0008=Personne non trouve");
			throw e;
		}
	}

	public enum TYPE_APPEL{
		OK,
		TIMEOUT,
		EXECUTION_ISSUE,
		CHECKED_EXCEPTION,
		ERREUR_DEV,
	}

}
