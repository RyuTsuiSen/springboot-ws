package fr.trandutrieu.remy.springbootjaxws.socle.context;

public class ContextManager {
	
	private static final ThreadLocal<ContextBean> context = new ThreadLocal<>(); ;
	
	public static ContextBean get() {
		return context.get();
	}
	
	public static void set(ContextBean bean){
		context.set(bean);
	}
	
	public static void remove(){
		context.remove();
	}
}
