package org.springframework.web.context.request;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.util.StringUtils;

public class WindowScope extends AbstractRequestAttributesScope {
	private final static int SCOPE_WINDOW = 3;

	@Override
	protected int getScope() {
		return SCOPE_WINDOW;
	}

	@Override
	public String getConversationId() {
		String windowId = (String) RequestContextHolder.currentRequestAttributes().getAttribute(
				WindowScopeRequestAttributes.WINDOW_ID_REQUEST_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);

		if (!StringUtils.hasText(windowId)) {
			windowId = generateWindowId();
		}

		return windowId;
	}

	private String generateWindowId() {
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		long time = System.currentTimeMillis();
		return sessionId + ":" + time;
	}

	@Override
	public Object get(String name, ObjectFactory objectFactory) {
		Object mutex = RequestContextHolder.currentRequestAttributes().getSessionMutex();
		synchronized (mutex) {
			return super.get(name, objectFactory);
		}
	}

	@Override
	public Object remove(String name) {
		Object mutex = RequestContextHolder.currentRequestAttributes().getSessionMutex();
		synchronized (mutex) {
			return super.remove(name);
		}
	}
}
