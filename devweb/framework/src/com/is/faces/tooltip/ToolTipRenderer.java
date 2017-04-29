package com.is.faces.tooltip;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.panel.Panel;
import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.WidgetBuilder;

public class ToolTipRenderer extends CoreRenderer{

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ToolTip tooltip = (ToolTip) component;
        String target = SearchExpressionFacade.resolveClientIds(
        				context, component, tooltip.getFor());
		
        encodeMarkup(context, tooltip, target);
		//encodeScript(context, tooltip, target);
	}
    
    protected void encodeMarkup(FacesContext context, ToolTip tooltip, String target) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        if(target != null) {
            String styleClass = tooltip.getStyleClass();
            //styleClass = styleClass == null ? ToolTip.CONTAINER_CLASS : ToolTip.CONTAINER_CLASS + " " + styleClass;

            writer.startElement("div", tooltip);
            writer.writeAttribute("id", tooltip.getClientId(context), null);
            writer.writeAttribute("class", styleClass, "styleClass");

            if(tooltip.getStyle() != null) 
                writer.writeAttribute("style", tooltip.getStyle(), "style");

            if(tooltip.getChildCount() > 0) {
                renderChildren(context, tooltip);
            }
            else {
                String valueToRender = ComponentUtils.getValueToRender(context, tooltip);
                if(valueToRender != null) {
                    if(tooltip.isEscape())
                        writer.writeText(valueToRender, "value");
                    else
                        writer.write(valueToRender);
                }
            }


            writer.endElement("div");
            Panel p = new Panel();
            //renderChild(context,p);
        }
    }
    
    protected void renderChild(FacesContext facesContext,UIComponent child) throws IOException {
    	  ResponseWriter responseWriter=facesContext.getResponseWriter();
    	  responseWriter.startElement("div",child);
    	  child.encodeAll(facesContext);
    	  responseWriter.endElement("div");
    	}

	protected void encodeScript(FacesContext context, ToolTip tooltip, String target) throws IOException {
        String clientId = tooltip.getClientId(context);
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.initWithDomReady("Tooltip", tooltip.resolveWidgetVar(), clientId) 
            .attr("target", target, null)
            .callback("onShow", "function()", tooltip.getOnShow())
            .callback("onHide", "function()", tooltip.getOnHide());
        
		wb.finish();
	}

    @Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		//Rendering happens on encodeEnd
	}

    @Override
	public boolean getRendersChildren() {
		return true;
	}
}