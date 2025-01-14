package com.awssdk;


//snippet-start:[ec2.java2.describe_instances.import]
//import org.junit.platform.commons.logging.LoggerFactory;
//import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.ec2.Ec2Client;
//import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
//import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
//import software.amazon.awssdk.services.ec2.model.Instance;
//import software.amazon.awssdk.services.ec2.model.Reservation;
//import software.amazon.awssdk.services.ec2.model.Ec2Exception;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
//snippet-end:[ec2.java2.describe_instances.import]

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.Region;
import com.amazonaws.services.ec2.model.Reservation;

/**
* Before running this Java V2 code example, set up your development environment, including your credentials.
*
* For more information, see the following documentation topic:
*
* https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
*/
public class DescribeInstance {

 // private static Global logger;
 private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
 public static void main(String[] args) {

     Region region = Region.US_EAST_1;
     Ec2Client ec2 = Ec2Client.builder()
         .region(region)
         .credentialsProvider(ProfileCredentialsProvider.create())
         .build();

    describeEC2Instances(ec2);
    ec2.close();
 }

 // snippet-start:[ec2.java2.describe_instances.main]
 public static void describeEC2Instances( Ec2Client ec2){

     String nextToken = null;
     try {
         do {
             DescribeInstancesRequest request = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build();
             DescribeInstancesResponse response = ec2.describeInstances(request);
             for (Reservation reservation : response.reservations()) {
                 for (Instance instance : reservation.instances()) {
                     System.out.println("Instance Id is " + instance.instanceId());
                     System.out.println("Image id is "+ instance.imageId());
                     System.out.println("Instance type is "+ instance.instanceType());
                     System.out.println("Instance state name is "+ instance.state().name());
                     System.out.println("monitoring information is "+ instance.monitoring().state());
                 }
             }
             nextToken = response.nextToken();
         } while (nextToken != null);

     } catch (Ec2Exception e) {
         System.err.println(e.awsErrorDetails().errorCode());
         System.exit(1);
     }
 }
 // snippet-end:[ec2.java2.describe_instances.main]
}
