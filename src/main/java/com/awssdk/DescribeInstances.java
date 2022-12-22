package com.awssdk;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.User;
import com.amazonaws.services.identitymanagement.model.LoginProfile;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileResult;

public class DescribeInstances {
	
public void getAllUser() {
	final AmazonIdentityManagement iam =
            AmazonIdentityManagementClientBuilder.defaultClient();

        boolean done = false;
        ListUsersRequest request = new ListUsersRequest();
        GetLoginProfileRequest st = new GetLoginProfileRequest();
        GetLoginProfileResult sr = new GetLoginProfileResult();
        LoginProfile lp = new LoginProfile();
       
        
       

        while(!done) {
            ListUsersResult response = iam.listUsers(request);
            
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-16s| %-28s  |%n", "user","Last login");
            System.out.println("--------------------------------------------------");
            
            
            for(User user : response.getUsers()) {
//            	System.out.format("\n Retrieved user : %s", user.getUserName());
//                System.out.format("\n PL changed : %s", user.getPasswordLastUsed());
            	System.out.printf("| %-16s| %-28s  |%n ",user.getUserName(),user.getPasswordLastUsed());
//            	System.out.println(st.getUserName());
//            	System.out.println(lp.getCreateDate());
            	 
            	 System.out.println("-------------------------------------------------");
               
            }
            System.out.println("--------------------------------------------------");
            request.setMarker(response.getMarker());
            if(!response.getIsTruncated()) {
                done = true;
            }
        }
}

}