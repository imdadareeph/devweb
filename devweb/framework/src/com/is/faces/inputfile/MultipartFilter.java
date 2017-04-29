/**
 * 
 */
package com.is.faces.inputfile;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
/**
 * @author imdadareeph
 *
 */
@WebFilter(urlPatterns = { "/*" }, initParams = {
	    @WebInitParam(name = "location", value = "/upload") })
public class MultipartFilter implements Filter {

    // Constants ----------------------------------------------------------------------------------

    private static final String INIT_PARAM_LOCATION = "location";
    private static final String REQUEST_METHOD_POST = "POST";
    private static final String CONTENT_TYPE_MULTIPART = "multipart/";

    // Vars --------------------------------------------------------------------------------------

    private String location;

    // Actions ------------------------------------------------------------------------------------

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.location = config.getInitParameter(INIT_PARAM_LOCATION);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (isMultipartRequest(httpRequest)) {
            request = new MultipartRequest(httpRequest, location);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NOOP.
    }

    // Helpers ------------------------------------------------------------------------------------

    /**
     * Returns true if the given request is a multipart request.
     * @param request The request to be checked.
     * @return True if the given request is a multipart request.
     */
    public static final boolean isMultipartRequest(HttpServletRequest request) {
        return REQUEST_METHOD_POST.equalsIgnoreCase(request.getMethod())
            && request.getContentType() != null
            && request.getContentType().toLowerCase().startsWith(CONTENT_TYPE_MULTIPART);
    }

}