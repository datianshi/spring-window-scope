package com.sample.scopes.web;

import com.sample.scopes.domain.ValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@Autowired
	@Qualifier("sessionScopedBean")
	private ValueBean sessionScopedBean;

	@Autowired
	@Qualifier("windowScopedBean")
	private ValueBean windowScopedBean;

	@Autowired
	@Qualifier("requestScopedBean")
	private ValueBean requestScopedBean;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String show(Model model) {
		HomeCommand command = new HomeCommand();
		command.setSessionScopedValue(sessionScopedBean.getValue());
		command.setWindowScopedValue(windowScopedBean.getValue());
		command.setRequestScopedValue(requestScopedBean.getValue());

		model.addAttribute("command", command);

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String update(HomeCommand command) {
		sessionScopedBean.setValue(command.sessionScopedValue);
		windowScopedBean.setValue(command.windowScopedValue);
		requestScopedBean.setValue(command.requestScopedValue);

		return "redirect:/";
	}
}

class HomeCommand {
	String sessionScopedValue;
	String requestScopedValue;
	String windowScopedValue;

	public HomeCommand() {
	}

	public String getSessionScopedValue() {
		return sessionScopedValue;
	}

	public void setSessionScopedValue(String sessionScopedValue) {
		this.sessionScopedValue = sessionScopedValue;
	}

	public String getRequestScopedValue() {
		return requestScopedValue;
	}

	public void setRequestScopedValue(String requestScopedValue) {
		this.requestScopedValue = requestScopedValue;
	}

	public String getWindowScopedValue() {
		return windowScopedValue;
	}

	public void setWindowScopedValue(String windowScopedValue) {
		this.windowScopedValue = windowScopedValue;
	}
}
