package com.is.faces.fileupload;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequestWrapper;
import org.apache.commons.fileupload.FileItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.webapp.MultipartRequest;

public class CommonsFileUploadDecoder{

    public static void decode(FacesContext context, FileUpload fileUpload) {
		MultipartRequest multipartRequest = null;
        Object request = context.getExternalContext().getRequest();
		
		while(request instanceof ServletRequestWrapper) {
			if(request instanceof MultipartRequest) {
				multipartRequest = (MultipartRequest) request;
                break;
			}
			else {
				request = ((ServletRequestWrapper) request).getRequest();
			}
		}
        
		if(multipartRequest != null) {
            if(fileUpload.getMode().equals("simple")) {
                decodeSimple(context, fileUpload, multipartRequest);
            }
            else {
                decodeAdvanced(context, fileUpload, multipartRequest);
            }
		}
    }
        
    private static void decodeSimple(FacesContext context, FileUpload fileUpload, MultipartRequest request) {
        FileItem file = request.getFileItem(fileUpload.getSimpleInputDecodeId(context));
        
        if(file != null) {
            if(file.getName().equals("")) {
                fileUpload.setSubmittedValue("");
            } else {
                fileUpload.setTransient(true);
                fileUpload.setSubmittedValue(new DefaultUploadedFile(file));
            }
        }  
	}
    
    private static void decodeAdvanced(FacesContext context, FileUpload fileUpload, MultipartRequest request) {
        String clientId = fileUpload.getClientId(context);
        FileItem file = request.getFileItem(clientId);
            
        if(file != null) {
            fileUpload.setTransient(true);
            fileUpload.queueEvent(new FileUploadEvent(fileUpload, new DefaultUploadedFile(file)));
        }
	}
}
