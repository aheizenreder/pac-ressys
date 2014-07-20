/**
 * 
 */
package com.prodyna.pac.ressys.monitoring.logging;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.prodyna.pac.ressys.monitoring.logging.Logged;

/**
 * @author Andreas Heizenreder (andreas.heizenreder@prodyna.com)
 * 
 */
@Logged
@Interceptor
public class LoggingInterceptor {

	@Inject
	private Logger log;

	/**
	 * default constructor.
	 */
	public LoggingInterceptor() {
	}

	/**
	 * creates a log entry for called method.
	 * 
	 * @param ic
	 *            InvocationContext
	 * @return result of method invocations.
	 * @throws Exception
	 *             for the errors while invocation.
	 */
	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		long endTime = 0;
		long startTime = 0;
		long elapsedTime = 0;
		StringBuilder parametersStringBuilder = new StringBuilder();

		startTime = System.currentTimeMillis();
		Object proceed = ic.proceed();
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;

		Method method = ic.getMethod();
		Object[] parameters = ic.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i] != null) {
				parametersStringBuilder.append(parameters[i].toString());
				// add separator if not the last parameter
				if (i < parameters.length - 1) {
					parametersStringBuilder.append(", ");
				}
			}
		}
		
		log.info("service call : " + method.getDeclaringClass().getName() + "."
				+ method.getName() + " (" + elapsedTime
				+ " ms) with parameters [" + parametersStringBuilder.toString()
				+ "]");
		
		return proceed;
	}
}
