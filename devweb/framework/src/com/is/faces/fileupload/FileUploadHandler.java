package com.is.faces.fileupload;

import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.MetaRule;
import javax.faces.view.facelets.MetaRuleset;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.facelets.MethodRule;

public class FileUploadHandler extends ComponentHandler {

	private static final MetaRule FILE_UPLOAD_LISTENER =
			new MethodRule("fileUploadListener", null, new Class[]{FileUploadEvent.class});
	
	public FileUploadHandler(ComponentConfig config) {
		super(config);
	}
	
	@SuppressWarnings("unchecked")
	protected MetaRuleset createMetaRuleset(Class type) { 
		MetaRuleset metaRuleset = super.createMetaRuleset(type); 

		metaRuleset.addRule(FILE_UPLOAD_LISTENER);
		
		return metaRuleset; 
	} 
}
