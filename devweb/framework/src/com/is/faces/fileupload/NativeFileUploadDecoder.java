package com.is.faces.fileupload;

import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.NativeUploadedFile;

public class NativeFileUploadDecoder {

    public static void decode(FacesContext context, FileUpload fileUpload) {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            if(fileUpload.getMode().equals("simple")) {
                decodeSimple(context, fileUpload, request);
            }
            else {
                decodeAdvanced(context, fileUpload, request);
            }
        } 
        catch (IOException ioe) {
            throw new FacesException(ioe);
        } 
        catch (ServletException se) {
            throw new FacesException(se);
        }
    }
    
    private static void decodeSimple(FacesContext context, FileUpload fileUpload, HttpServletRequest request) throws IOException, ServletException {
        Part part = request.getPart(fileUpload.getSimpleInputDecodeId(context));
        
        if(part != null) {
            fileUpload.setTransient(true);
            fileUpload.setSubmittedValue(new NativeUploadedFile(part));
        }
        else {
            fileUpload.setSubmittedValue("");
        }
	}
    
    private static void decodeAdvanced(FacesContext context, FileUpload fileUpload, HttpServletRequest request) throws IOException, ServletException {
        String clientId = fileUpload.getClientId(context);
        Part part = request.getPart(clientId);

        if(part != null) {
            fileUpload.setTransient(true);
            fileUpload.queueEvent(new FileUploadEvent(fileUpload, new NativeUploadedFile(part)));
        }
	}
    
}
