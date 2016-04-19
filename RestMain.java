package com.java.owcs.customrest;

import java.util.ArrayList;
import java.util.List;

import jersey.repackaged.com.google.common.base.Joiner;

import com.fatwire.rest.beans.User;
import com.fatwire.rest.beans.UserBean;
import com.fatwire.rest.beans.UserSite;
import com.fatwire.rest.beans.UsersBean;
import com.sun.jersey.api.client.ClientResponse;

public class RestMain {
	
	 public static void main(String[] args) throws Exception {
	        RestClient client = new RestClient("http://localhost:9180/cs/", "fwadmin","xceladmin");
	        ClientResponse response = client.get("/users");
	        if (response.getStatus() == 200) {
	            UsersBean users = response.getEntity(UsersBean.class);
	            for (User user : users.getUsers()) {
	                ClientResponse userResponse = client.get(user.getHref());
	                if (userResponse.getStatus() == 200) {
	                    UserBean userBean = userResponse.getEntity(UserBean.class);
	                    System.out.println(userBean.getName());
	                    System.out.println(userBean.getId());
	                    List<String> siteNames = new ArrayList<String>();
	                    for (UserSite userSite : userBean.getSites()) {
	                        siteNames.add(userSite.getSite() + "=>" + Joiner.on(",").join(userSite.getRoles()));
	                    }
	                    System.out.println(Joiner.on(" || ").join(siteNames));
	                    System.out.println(Joiner.on(",").join(userBean.getAcls()));
	                }
	            }
	        }
	    }

}
