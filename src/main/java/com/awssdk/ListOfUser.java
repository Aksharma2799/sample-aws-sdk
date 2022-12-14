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

            for(User user : response.getUsers()) {
            	
                System.out.format("\n Password last changed : %s", user.getPasswordLastUsed());
                System.out.format("\n Retrieved user : %s", user.getUserName());
                
               
            }
            request.setMarker(response.getMarker());
            if(!response.getIsTruncated()) {
                done = true;
            }
        }
}

}
