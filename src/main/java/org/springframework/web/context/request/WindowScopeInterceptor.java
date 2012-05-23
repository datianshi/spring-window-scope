/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.context.request;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class WindowScopeInterceptor extends HandlerInterceptorAdapter {
	public static final String DEFAULT_PARAM_NAME = "wid";

	private String paramName = DEFAULT_PARAM_NAME;

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamName() {
		return paramName;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
		String windowId = request.getParameter(paramName);

		if (!StringUtils.hasText(windowId)) {
			Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
			if (flashMap != null) {
				windowId = (String) flashMap.get(WindowScopeRequestAttributes.WINDOW_ID_REQUEST_ATTRIBUTE);
			}
		}

		if (!StringUtils.hasText(windowId)) {
			windowId = generateWindowId();
		}

		RequestContextHolder.currentRequestAttributes().setAttribute(
				WindowScopeRequestAttributes.WINDOW_ID_REQUEST_ATTRIBUTE,
				windowId, RequestAttributes.SCOPE_REQUEST);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		String windowId = (String) RequestContextHolder.currentRequestAttributes().getAttribute(
				WindowScopeRequestAttributes.WINDOW_ID_REQUEST_ATTRIBUTE,
				RequestAttributes.SCOPE_REQUEST);

		FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
		flashMap.put(WindowScopeRequestAttributes.WINDOW_ID_REQUEST_ATTRIBUTE, windowId);
	}

	private String generateWindowId() {
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		int counter = getWindowIdCounter();

		return sessionId + ":" + counter;
	}

	private int getWindowIdCounter() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

		Integer counter = (Integer) requestAttributes.getAttribute(
				WindowScopeRequestAttributes.WINDOW_ID_COUNTER_SESSION_ATTRIBUTE,
				RequestAttributes.SCOPE_SESSION);

		if (counter == null) {
			counter = 0;
		}

		counter++;

		requestAttributes.setAttribute(
				WindowScopeRequestAttributes.WINDOW_ID_COUNTER_SESSION_ATTRIBUTE,
				counter,
				RequestAttributes.SCOPE_SESSION);

		return counter;
	}
}
