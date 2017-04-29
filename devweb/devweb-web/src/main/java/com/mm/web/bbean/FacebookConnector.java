/**
 * 
 */
package com.mm.web.bbean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.User;

/**
 * @author jamal
 *
 */
@Named("facebookConnector")
@Scope("session")
public class FacebookConnector {

    /* Variables */
	public String graphApiFbUrl = "https://www.facebook.com/dialog/oauth?";
	
    private final String pageAccessToken = "EAACEdEose0cBAHu2yZCymnPT5CEv3T6W5N89bd7b98bKIS4tWNqcDeX6s9OlekmH69DXLeIoiIREtbT83dLqiXSVtFkZBw6zXtfJYZAQ03JGV8MxJOtjgYrUq8t0bXQXyzFiZCsO2atEhqKeqg61OIEDoVKbfuyT841hZAHlCqwZDZD";
    private final String appID = "862152287259246";
    private FacebookClient fbClient;
    private User myuser = null;    //Store references to your user and page
    private Page mypage = null;    //for later use. In this answer's context, these
                                   //references are useless.
    private FacebookLoginData facebookLoginData =new FacebookLoginData();
    private int counter = 0;
    
    public FacebookConnector() {
    	
    }
    @PostConstruct
    public void init(){
    	facebookLoginData = prepopulateFbData();
    	graphApiFbUrl=graphApiFbUrl+"client_id="+facebookLoginData.getClient_id()+"&redirect_uri="+facebookLoginData.getRedirect_uri()+"&display="+facebookLoginData.getDisplay()+"&scope="+facebookLoginData.getScope()+"&response_type="+facebookLoginData.getResponse_type();
        try {

            fbClient = new DefaultFacebookClient(pageAccessToken);
            myuser = fbClient.fetchObject("me", User.class);
            mypage = fbClient.fetchObject(appID, Page.class);
            counter = 0;
        } catch (FacebookException ex) {     //So that you can see what went wrong
            ex.printStackTrace(System.err);  //in case you did anything incorrectly
        }
    }
    
    public void openFacebookDialog(){
    	setGraphApiFbUrl(graphApiFbUrl);
    }
    
    public FacebookLoginData prepopulateFbData(){
    	facebookLoginData.setClient_id("862152287259246");
    	facebookLoginData.setDisplay("popup");
    	facebookLoginData.setRedirect_uri("https://www.facebook.com/connect/login_success.html");
    	facebookLoginData.setResponse_type("token");
    	facebookLoginData.setScope("user_photos");
    	return facebookLoginData;
    }

    public void makeTestPost() {
        fbClient.publish(appID + "/feed", FacebookType.class, Parameter.with("message", Integer.toString(counter) + ": Hello, facebook World!"));
        counter++;
    }
    
    public void fetchProfilePic() {
        //fbClient.publish(appID + "/me/picture", FacebookType.class);
    	FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken);
    	User user = facebookClient.fetchObject("me", User.class);
    	System.out.println("User name: " + user.getName());
    	//System.out.println("User name: " + user.getPicture());
    }
    
    public void fetchUserImage(){
		String fbprofileImage = "";
		FacebookClient client = new DefaultFacebookClient(pageAccessToken, Version.LATEST);
		JsonObject picture = 
		      client.fetchObject("me/picture", 
			      JsonObject.class, Parameter.with("redirect","false"));
		fbprofileImage = picture.toString();
		System.out.println("User Image: " + fbprofileImage);
	}
    
    /*public static Result authUser(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        User user = facebookClient.fetchObject("me", User.class);


        return ok(authUser.render(user.getFirstName() + " " + user.getLastName(), user.getEmail()));
    }*/
    
    /*public void getAccessToken() {
    	System.out.println("home init");
    	if (!FacesContext.getCurrentInstance().getExternalContext()
    			.getSessionMap().containsKey("accessToken")) {
    		HttpServletRequest req = (HttpServletRequest) FacesContext
    				.getCurrentInstance().getExternalContext().getRequest();
    		String code = req.getParameter("code");
    		String accessToken = FacebookService.getInstance().getAccessToken(
    				code, "Home.xhtml");
    		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
    		User user = fbClient.fetchObject("me", User.class); 
    		int userId = UserDP.insert(user.getId(), user.getEmail());
    		Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
    				.getExternalContext().getSessionMap();
    		sessionMap.put("accessToken", accessToken);
    		Connection<Group> gr = fbClient.fetchConnection("me/groups",
    				Group.class);
    		List<Group> groups = gr.getData();
    		List<UserGroup> userGroups = new ArrayList<UserGroup>();
    		for(Group g: groups) {
    			userGroups.add(new UserGroup(g.getId(),userId,g.getName()));
    		}
    		sessionMap.put("userGroups", userGroups);
    		UserGroupDP.insert(userId, userGroups);
    		sessionMap.put("userId", userId);
    		sessionMap.put("username", user.getName());
    	}
    	return;
    }*/
    
    
    
    public static void main(String[] args) {
    	FacebookConnector fc = new FacebookConnector();
    	fc.fetchUserImage();
	}
	public String getGraphApiFbUrl() {
		return graphApiFbUrl;
	}
	public void setGraphApiFbUrl(String graphApiFbUrl) {
		this.graphApiFbUrl = graphApiFbUrl;
	}

}

class FacebookLoginData{
	String client_id;
	String redirect_uri;
	String display;
	String scope;
	String response_type;
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
}
