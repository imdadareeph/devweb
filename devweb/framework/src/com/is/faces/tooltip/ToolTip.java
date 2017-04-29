package com.is.faces.tooltip;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;


	@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="primefaces.css"),
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="jquery/jquery-plugins.js"),
	@ResourceDependency(library="js", name="tooltip.js"),
	@ResourceDependency(library="primefaces", name="primefaces.js")
})
public class ToolTip extends UIOutput implements org.primefaces.component.api.Widget {
	
		public static final String COMPONENT_TYPE = "com.is.faces.tooltip.ToolTip";
		public static final String COMPONENT_FAMILY = "com.is.faces.tooltip";
		private static final String DEFAULT_RENDERER = "com.is.faces.tooltip.ToolTipRenderer";

		protected enum PropertyKeys {

			widgetVar
			,forValue("for")
			,style
			,styleClass
			,escape
			,onHide
			,onShow;

			String toString;

			PropertyKeys(String toString) {
				this.toString = toString;
			}

			PropertyKeys() {}

			public String toString() {
				return ((this.toString != null) ? this.toString : super.toString());
	}
		}

		public ToolTip() {
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
		}
		
		public java.lang.String getFor() {
			return (java.lang.String) getStateHelper().eval(PropertyKeys.forValue, null);
		}
		public void setFor(java.lang.String _for) {
			getStateHelper().put(PropertyKeys.forValue, _for);
		}

		public java.lang.String getStyle() {
			return (java.lang.String) getStateHelper().eval(PropertyKeys.style, null);
		}
		public void setStyle(java.lang.String _style) {
			getStateHelper().put(PropertyKeys.style, _style);
		}

		public java.lang.String getStyleClass() {
			return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass, null);
		}
		public void setStyleClass(java.lang.String _styleClass) {
			getStateHelper().put(PropertyKeys.styleClass, _styleClass);
		}
		
		public boolean isEscape() {
			return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.escape, true);
		}
		public void setEscape(boolean _escape) {
			getStateHelper().put(PropertyKeys.escape, _escape);
		}
		
		public java.lang.String getOnHide() {
			return (java.lang.String) getStateHelper().eval(PropertyKeys.onHide, null);
		}
		public void setOnHide(java.lang.String _onHide) {
			getStateHelper().put(PropertyKeys.onHide, _onHide);
		}

		public java.lang.String getOnShow() {
			return (java.lang.String) getStateHelper().eval(PropertyKeys.onShow, null);
		}
		public void setOnShow(java.lang.String _onShow) {
			getStateHelper().put(PropertyKeys.onShow, _onShow);
		}
	
	
	public static String CONTAINER_CLASS = "ui-tooltip ui-widget ui-widget-content ui-shadow ui-corner-all";
	@Override
	public String resolveWidgetVar() {
		FacesContext context = getFacesContext();
		String userWidgetVar = (String) getAttributes().get("widgetVar");

		if(userWidgetVar != null)
			return userWidgetVar;
		 else
			return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
	}

}
