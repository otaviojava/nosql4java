package linguagil.riak.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public enum SpringUtil {
	
	INSTANCE;

	private ApplicationContext context;
	
	public <T> T getInstance(Class<T> classBean) {
		return context.getBean(classBean);
	}
	
	{
		context = new GenericXmlApplicationContext("SpringConfig.xml");
	}
}
