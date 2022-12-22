package com.awssdk;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.Reservation;

import software.amazon.awssdk.*;

public class Instance{
public static void describeEC2Instances( AmazonEC2Client ec2){

    boolean done = false;
    String nextToken = null;

    try {

        do {
            DescribeInstancesRequest request = DescribeInstancesRequest
//            		.builder().maxResults(6).nextToken(nextToken).build();
            DescribeInstancesResponse response = ec2.describeInstances(request);

            for (Reservation reservation : response.reservations()) {
                for (Instance instance : reservation.instances()) {
                    System.out.println("Instance Id is " + instance.instanceId());
                    System.out.println("Image id is "+  instance.imageId());
                    System.out.println("Instance type is "+  instance.instanceType());
                    System.out.println("Instance state name is "+  instance.state().name());
                    System.out.println("monitoring information is "+  instance.monitoring().state());

            }
        }
            nextToken = response.nextToken();
        }
        while (nextToken != null);

}
    catch (Ec2Exception e) {
        System.err.println(e.awsErrorDetails().errorMessage());
        System.exit(1);
    }
}
}