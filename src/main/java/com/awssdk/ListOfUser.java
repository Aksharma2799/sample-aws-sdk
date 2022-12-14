package com.awssdk;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.User;

public class ListOfUser {
	
public void getAllUser() {
	final AmazonIdentityManagement iam =
            AmazonIdentityManagementClientBuilder.defaultClient();

        boolean done = false;
        ListUsersRequest request = new ListUsersRequest();

        while(!done) {
            ListUsersResult response = iam.listUsers(request);
            
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-16s| %-28s  |%n", "user","PL changed");
            System.out.println("--------------------------------------------------");
            
            
            for(User user : response.getUsers()) {
//            	System.out.format("\n Retrieved user : %s", user.getUserName());
//                System.out.format("\n PL changed : %s", user.getPasswordLastUsed());
            	System.out.printf("| %-16s| %-28s  |%n ",user.getUserName(),user.getPasswordLastUsed());
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