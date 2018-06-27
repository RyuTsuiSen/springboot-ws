package fr.trandutrieu.remy.springbootjaxws.socle;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class ExternalCall extends HystrixCommand<ExternalCallResponse> {
	
	protected ExternalCall(HystrixCommandGroupKey group) {
		super(group);
	}

	@Override
	protected ExternalCallResponse run() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
