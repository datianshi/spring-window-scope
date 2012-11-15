package org.springframework.web.context.request;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.util.StringUtils;

public class WindowScope extends SessionScope {
	@Override
	public Object get(String name, ObjectFactory objectFactory) {
		return super.get(getWindowId() + name, objectFactory);
	}

	@Override
	public Object remove(String name) {
		return super.remove(getWindowId() + name);
	}

	private String getWindowId() {
		String windowId = (String) RequestContextHolder.currentRequestAttributes().getAttribute(
				WindowScopeRequestAttributes.WINDOW_ID_REQUEST_ATTRIBUTE,
				RequestAttributes.SCOPE_REQUEST);

		if (!StringUtils.hasText(windowId)) {
			throw new IllegalStateException("Window scope request attribute not found");
		}

		return windowId;
	}

}
