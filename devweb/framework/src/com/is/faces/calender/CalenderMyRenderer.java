package com.is.faces.calender;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.WidgetBuilder;

public class CalenderMyRenderer extends CoreRenderer{

    @Override
    public void decode(FacesContext context, UIComponent component) {
    	
    	CalenderMy calendar = (CalenderMy) component;

         
           String param = calendar.getClientId(context) ;
           String submittedValue = context.getExternalContext().getRequestParameterMap().get(param);

           if(submittedValue != null) {
               calendar.setSubmittedValue(submittedValue);
           }
        decodeBehaviors(context, component);
    }

    @Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		CalenderMy slider = (CalenderMy) component;
		
		encodeMarkup(context, slider);
			encodeScript(context, slider);
	}
	
	protected void encodeMarkup(FacesContext context, CalenderMy slider) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = slider.getClientId(context);
		
		  writer.startElement("input",null);
		     writer.writeAttribute("type","text","type");
		     writer.writeAttribute("name",clientId,"name");
		     writer.writeAttribute("class","caln","class");
		     writer.writeAttribute("value",slider.getValue(),"value");
		     writer.endElement("input");
	}

	protected void encodeScript(FacesContext context, CalenderMy slider) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = slider.getClientId(context);
		
		writer.startElement("script",null);
		writer.writeAttribute("type","text/javascript","type");
		writer.write("$( document ).ready(function() {  $('input[name=\""+clientId+"\"]').daterangepicker(); $('.daterangepicker').css('display', 'none');});");
		
		 writer.endElement("script");
		 
/*		 slider.getAddBtn().encodeAll(context);*/

	}
	
	protected UIComponent getTarget(FacesContext context, CalenderMy slider, String target) {
		if(target == null) {
			return null;
		} 
        else {
			UIComponent targetComponent = SearchExpressionFacade.resolveComponent(context, slider, target);

			return targetComponent;
		}
	}
}