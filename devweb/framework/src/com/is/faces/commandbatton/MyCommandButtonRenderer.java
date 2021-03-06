package com.is.faces.commandbatton;

import java.io.IOException;   

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.HTML;

public class MyCommandButtonRenderer extends CoreRenderer {

    @Override
	public void decode(FacesContext facesContext, UIComponent component) {
    	MyCommandButton button = (MyCommandButton) component;
        if(button.isDisabled()) {
            return;
        }
        
		String param = component.getClientId(facesContext);
		
		if(facesContext.getExternalContext().getRequestParameterMap().containsKey(param)) {
			component.queueEvent(new ActionEvent(component));
		}
	}

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException {
		CommandButton button = (CommandButton) component;
		
		//myfaces fix
		if(button.getType() == null)
			button.setType("submit");
		
		encodeMarkup(facesContext, button);
		encodeScript(facesContext, button);
	}
	
	protected void encodeMarkup(FacesContext facesContext, CommandButton button) throws IOException {
		ResponseWriter writer = facesContext.getResponseWriter();
		String clientId = button.getClientId(facesContext);
		String type = button.getType();

		writer.startElement("button", button);
		writer.writeAttribute("id", clientId, "id");
		writer.writeAttribute("name", clientId, "name");
		if(button.getStyleClass() != null) writer.writeAttribute("class", button.getStyleClass() , "styleClass");

		String onclick = button.getOnclick();
		if(!type.equals("reset") && !type.equals("button")) {
			UIComponent form = ComponentUtils.findParentForm(facesContext, button);
			if(form == null) {
				throw new FacesException("CommandButton : \"" + clientId + "\" must be inside a form element");
			}
			
			String formClientId = form.getClientId(facesContext);		
			String request = button.isAjax() ? buildAjaxRequest(facesContext, button, formClientId, clientId) + "return false;" : buildNonAjaxRequest(facesContext, button, formClientId);
			onclick = onclick != null ? onclick + ";" + request : request;
		}
		
		if(!isValueBlank(onclick)) {
			writer.writeAttribute("onclick", onclick, "onclick");
		}
		
		renderPassThruAttributes(facesContext, button, HTML.BUTTON_ATTRS, HTML.CLICK_EVENT);
		
		if(button.getValue() != null) {
			writer.write(button.getValue().toString());
		} else if(button.getImage() != null) {
			writer.write("ui-button");
		}
			
		writer.endElement("button");
	}
	
	private String buildAjaxRequest(FacesContext facesContext,
			CommandButton button, String formClientId, String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void encodeScript(FacesContext facesContext, CommandButton button) throws IOException {
		ResponseWriter writer = facesContext.getResponseWriter();
		String clientId = button.getClientId(facesContext);
		String type = button.getType();
		boolean hasValue = (button.getValue() != null);
		
		writer.startElement("script", button);
		writer.writeAttribute("type", "text/javascript", null);

		writer.write(button.resolveWidgetVar() + " = new PrimeFaces.widget.CommandButton('" + clientId + "', {");
		
		if(type.equals("image") || button.getImage() != null) {
			writer.write("text:" + hasValue);
			writer.write(",icons:{");
			writer.write("primary:'" + button.getImage() + "'");
			writer.write("}");
		} 
		
		writer.write("});");
		
		writer.endElement("script");
	}

	protected String buildNonAjaxRequest(FacesContext facesContext, CommandButton button, String formId) {
        boolean hasParam = false;
        StringBuilder request = new StringBuilder();
        
        for(UIComponent component : button.getChildren()) {
			if(component instanceof UIParameter) {
                UIParameter param = (UIParameter) component;
                
                if(!hasParam) {
                    request.append("PrimeFaces");
                    hasParam = true;
                }

                request.append(addSubmitParam(formId, param.getName(), String.valueOf(param.getValue())));
			}
		}

        request.append(";");

		return request.toString();
	}

	private Object addSubmitParam(String formId, String name, String valueOf) {
		// TODO Auto-generated method stub
		return null;
	}
}