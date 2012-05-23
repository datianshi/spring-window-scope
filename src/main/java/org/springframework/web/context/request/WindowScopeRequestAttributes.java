package org.springframework.web.context.request;

public interface WindowScopeRequestAttributes {
	String WINDOW_ID_REQUEST_ATTRIBUTE = WindowScopeRequestAttributes.class.getName() + ".WINDOW_ID";
	String WINDOW_ID_COUNTER_SESSION_ATTRIBUTE = WindowScopeRequestAttributes.class.getName() + ".WINDOW_COUNTER";
}
