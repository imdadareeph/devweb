/**
 * 
 */
package com.mm.web.bbean;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;

/**
 * @author jamal
 *
 */
@Named("socialNetworkingBean")
@Scope("session")
public class SocialNetworkingBean {
	// the access token can be found in your Facebook app in the messenger section
	public String pageAccessToken = "MY PAGE ACCESS TOKEN";
	public static final String accessToken = "7f17cfa810b48f15fb289c8d863e81e1";
	public String authenticateFB(){
		FacebookClient client = new DefaultFacebookClient(accessToken, Version.LATEST);
		JsonObject picture = 
		      client.fetchObject("me/picture", 
			      JsonObject.class, Parameter.with("redirect","false"));
		return picture.toString();
	}
	
	public String fetchUserImage(){
		String fbprofileImage = "";
		FacebookClient client = new DefaultFacebookClient(accessToken, Version.LATEST);
		JsonObject picture = 
		      client.fetchObject("me/picture", 
			      JsonObject.class, Parameter.with("redirect","false"));
		fbprofileImage = picture.toString();
		return fbprofileImage;
	}

}
