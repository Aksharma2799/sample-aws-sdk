package com.awssdk;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

public class ListOfBucket {
	
	public void getAllBucket() {
		//Creating amazon s3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard() .build();
		//Listing all the buckets
		List<Bucket> buckets = s3.listBuckets();

		//Iterating through the bucket
		buckets.stream().forEach(bucket -> {
		System.out.println("Bucket Name : " + bucket.getName() + ", "
		+"\nBucket Owner : " +bucket.getOwner() .getDisplayName ()
		+",\nBucket Creation Date : " + bucket.getCreationDate());
		});
	}

}
