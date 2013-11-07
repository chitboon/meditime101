package model;

import javax.persistence.*;

import com.fitbit.api.model.APIResourceCredentials;


@Entity
public class ResourceCredentials {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;		
    String accessToken;
    String accessTokenSecret;
    String resourceId;
    String resourceURL;
    String localUserId;
    
    public void copy(APIResourceCredentials rc) {
    	setAccessToken(rc.getAccessToken());
    	setAccessTokenSecret(rc.getAccessTokenSecret());
    	setResourceId(rc.getResourceId());
    	setResourceURL(rc.getResourceURL());
    	setLocalUserId(rc.getLocalUserId());
    	System.out.println(toString());
    }
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceURL() {
		return resourceURL;
	}
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}
	public String getLocalUserId() {
		return localUserId;
	}
	public void setLocalUserId(String localUserId) {
		this.localUserId = localUserId;
	}
	public String getText() {
		StringBuffer buf = new StringBuffer();
		buf.append("AccessToken: " + getAccessToken() +"\n");
		buf.append("AccessTokenSecret: " + getAccessTokenSecret() +"\n");
		buf.append("ResourceId: " + getResourceId() +"\n");	
		buf.append("ResourceURL: " + getResourceURL() +"\n");	
		buf.append("LocalUserId: " + getLocalUserId());	
		return buf.toString();
	}
}
