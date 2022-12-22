package com.awssdk;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DryRunResult;
import com.amazonaws.services.ec2.model.DryRunSupportedRequest;
import com.amazonaws.services.ec2.model.Reservation;

public class insta {
	private static AmazonEC2 getEc2StandardClient() {
	    // Using StaticCredentialsProvider
	    final String accessKey = "access_key";
	    final String secretKey = "secret_key";
	    BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

	    return AmazonEC2ClientBuilder.standard()
	            .withRegion(Regions.AP_NORTHEAST_1)
	            .withCredentials(new AWSStaticCredentialsProvider(credentials))
	            .build();
	}

	public static void getInstanceInfo(String instanceId) {
	    final AmazonEC2 ec2 = getEc2StandardClient();

	    DryRunSupportedRequest<DescribeInstancesRequest> dryRequest =
	            () -> {
	                DescribeInstancesRequest request = new DescribeInstancesRequest()
	                    .withInstanceIds(instanceId);
	                return request.getDryRunRequest();
	            };

	    DryRunResult<DescribeInstancesRequest> dryResponse = ec2.dryRun(dryRequest);
	    if(!dryResponse.isSuccessful()) {
	        System.out.println("Failed to get information of instance " + instanceId);
	    }

	    DescribeInstancesRequest request = new DescribeInstancesRequest()
	            .withInstanceIds(instanceId);
	    DescribeInstancesResult response = ec2.describeInstances(request);

	    Reservation reservation = response.getReservations().get(0);
	    Instance instance = reservation.getInstances().get(0);
	    


	    System.out.println("Instance id: " + instance.getInstanceId(), ", state: " + instance.getState().getName() + 
	        ", public ip: " + instance.getPublicIpAddress() + ", private ip: " + instance.getPrivateIpAddress());
	}
}
