package com.is.faces.commandbatton;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UINamingContainer;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;

@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="jquery/ui/jquery-ui.css"),
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="jquery/ui/jquery-ui.js"),
	@ResourceDependency(library="primefaces", name="core/core.js"),
	@ResourceDependency(library="primefaces", name="button/button.js")
})
public class MyCommandButton extends HtmlCommandButton implements org.primefaces.component.api.AjaxSource,org.primefaces.component.api.Widget {


	public static final String COMPONENT_TYPE = "com.is.faces.commandbatton.MyCommandButton";
	public static final String COMPONENT_FAMILY = "com.is.faces.commandbatton";
	private static final String DEFAULT_RENDERER = "com.is.faces.commandbatton.MyCommandButtonRenderer";
	private static final String OPTIMIZED_PACKAGE = "com.is.faces.commandbatton.";

	protected enum PropertyKeys {

		widgetVar
		,ajax
		,async
		,process
		,update
		,onstart
		,oncomplete
		,onerror
		,onsuccess
		,global;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
}
	}

	public MyCommandButton() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public java.lang.String getWidgetVar() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
	}
	public void setWidgetVar(java.lang.String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
		handleAttribute("widgetVar", _widgetVar);
	}

	public boolean isAjax() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.ajax, true);
	}
	public void setAjax(boolean _ajax) {
		getStateHelper().put(PropertyKeys.ajax, _ajax);
		handleAttribute("ajax", _ajax);
	}

	public boolean isAsync() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.async, false);
	}
	public void setAsync(boolean _async) {
		getStateHelper().put(PropertyKeys.async, _async);
		handleAttribute("async", _async);
	}

	public java.lang.String getProcess() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.process, null);
	}
	public void setProcess(java.lang.String _process) {
		getStateHelper().put(PropertyKeys.process, _process);
		handleAttribute("process", _process);
	}

	public java.lang.String getUpdate() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.update, null);
	}
	public void setUpdate(java.lang.String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
		handleAttribute("update", _update);
	}

	public java.lang.String getOnstart() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onstart, null);
	}
	public void setOnstart(java.lang.String _onstart) {
		getStateHelper().put(PropertyKeys.onstart, _onstart);
		handleAttribute("onstart", _onstart);
	}

	public java.lang.String getOncomplete() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.oncomplete, null);
	}
	public void setOncomplete(java.lang.String _oncomplete) {
		getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
		handleAttribute("oncomplete", _oncomplete);
	}

	public java.lang.String getOnerror() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onerror, null);
	}
	public void setOnerror(java.lang.String _onerror) {
		getStateHelper().put(PropertyKeys.onerror, _onerror);
		handleAttribute("onerror", _onerror);
	}

	public java.lang.String getOnsuccess() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onsuccess, null);
	}
	public void setOnsuccess(java.lang.String _onsuccess) {
		getStateHelper().put(PropertyKeys.onsuccess, _onsuccess);
		handleAttribute("onsuccess", _onsuccess);
	}

	public boolean isGlobal() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.global, true);
	}
	public void setGlobal(boolean _global) {
		getStateHelper().put(PropertyKeys.global, _global);
		handleAttribute("global", _global);
	}


	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	public String resolveWidgetVar() {
		FacesContext context = FacesContext.getCurrentInstance();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if(userWidgetVar != null)
			return userWidgetVar;
		 else
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

	public void handleAttribute(String name, Object value) {
		List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		if(setAttributes == null) {
			String cname = this.getClass().getName();
			if(cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
				setAttributes = new ArrayList<String>(6);
				this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
			}
		}
		if(setAttributes != null) {
			if(value == null) {
				ValueExpression ve = getValueExpression(name);
				if(ve == null) {
					setAttributes.remove(name);
				} else if(!setAttributes.contains(name)) {
					setAttributes.add(name);
				}
			}
		}
	}

	@Override
	public boolean isPartialSubmit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPartialSubmitSet() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPartialSubmitFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isResetValues() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isResetValuesSet() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIgnoreAutoUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAjaxified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDelay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getForm() {
		// TODO Auto-generated method stub
		return null;
	}
}