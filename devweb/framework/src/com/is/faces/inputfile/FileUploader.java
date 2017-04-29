/**
 * 
 */
package com.is.faces.inputfile;

import javax.faces.component.html.HtmlInputText;

/**
 * @author imdadareeph
 *
 */
public class FileUploader extends HtmlInputText {

    // Getters ------------------------------------------------------------------------------------
	public static final String COMPONENT_TYPE = "com.is.faces.inputfile.FileUploader";
	public static final String COMPONENT_FAMILY = "com.is.faces.inputfile";
	private static final String DEFAULT_RENDERER = "com.is.faces.inputfile.FileUploaderRenderer";
	
/*    @Override
    public String getRendererType() {
        return "javax.faces.File";
    }*/
    
    public FileUploader() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
