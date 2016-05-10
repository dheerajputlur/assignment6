package assign.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/myeavesdrop")
public class EavesdropApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public EavesdropApplication() {		
	}
	
	//get classes - return a set of classes, 
	@Override
	public Set<Class<?>> getClasses() {
		classes.add(EavesdropResource.class);
		return classes;
	}
	
	//return set of objects, single object or instance of class within the application  
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
