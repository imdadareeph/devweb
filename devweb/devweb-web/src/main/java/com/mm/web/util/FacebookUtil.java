/**
 * 
 */
package com.mm.web.util;

import java.util.List;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import com.restfb.types.User;

/**
 * @author jamal
 *
 */
public class FacebookUtil { 
	 
	 public static Page getFacebookPage(String accessToken, String url) { 
	  FacebookClient facebookClient = new DefaultFacebookClient(accessToken); 
	 
	  return facebookClient.fetchObject(url, Page.class); 
	 } 
	 
	 public static User getFacebookUser(String accessToken) { 
	  FacebookClient facebookClient = new DefaultFacebookClient(accessToken); 
	 
	  return facebookClient.fetchObject("me", User.class); 
	 } 
	 
	 public static long getFriendsCount(String accessToken) { 
	  FacebookClient facebookClient = new DefaultFacebookClient(accessToken); 
	 
	  String query = "SELECT friend_count FROM user WHERE uid = me()"; 
	 
	  List<JsonObject> jsonObjects = facebookClient.executeFqlQuery( 
	   query, JsonObject.class); 
	 
	  JsonObject jsonObject = jsonObjects.get(0); 
	  long frndcount = 0;
			  
			  try {
				  frndcount = Long.valueOf(jsonObject.get("friend_count").asLong());
			         
			      } catch (NumberFormatException nfe) {
			         System.out.println("NumberFormatException: " + nfe.getMessage());
			      }
			  return frndcount;
	 // return GetterUtil.getLong(jsonObject.get("friend_count")); 
	 } 
	 
	 /*public static boolean isUserLikes(String accessToken, String url) { 
	  Page page = getFacebookPage(accessToken, url); 
	 
	  FacebookClient facebookClient = new DefaultFacebookClient(accessToken); 
	 
	  Post.Likes postLikes = facebookClient.fetchObject( 
	   "me/likes/" + page.getId(), Post.Likes.class); 
	 
	  List<NamedFacebookType> likes = postLikes.getData(); 
	 
	  if (likes.size() > 0) { 
	   return true; 
	  } 
	 
	  return false; 
	 } */
	 
	}