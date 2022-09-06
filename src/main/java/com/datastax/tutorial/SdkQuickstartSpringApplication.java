package com.datastax.tutorial;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.stargate.sdk.doc.CollectionClient;
import com.datastax.stargate.sdk.doc.DocumentClient;



@RestController
@SpringBootApplication
public class SdkQuickstartSpringApplication {

 public static void main(String[] args) {
  SpringApplication.run(SdkQuickstartSpringApplication.class, args);
 }

 // Provided by the Starter
 @Autowired
 private AstraClient astraClient;
 private CollectionClient dealerCollection;


 // Spring Data using the CqlSession initialized by the starter
 @Autowired
 private CassandraTemplate cassandraTemplate;

 @GetMapping("/api/devops/organizationid")
 public String showOrganizationId() {
   return astraClient.apiDevopsOrganizations().organizationId();
 }
 @GetMapping("/api/spring-data/datacenter")
 public String showDatacenterNameWithSpringData() {
   return cassandraTemplate.getCqlOperations()
                           .queryForObject("SELECT data_center FROM system.local", String.class);
 }

 @GetMapping("/api/cql/datacenter")
 public String showDatacenterNameWithSpringData2() {
   return astraClient.cqlSession()
                     .execute("SELECT data_center FROM system.local")
                     .one().getString("data_center");
 }

 @GetMapping("/api/cql/documentfetch")
 public String hello() throws Exception {
	 //return astraClient.apiDevopsOrganizations().organizationId();
	 CollectionClient cp = astraClient.apiStargateDocument()
			 .namespace("eapocdocumentns")
			 .collection("doccollection");
	 // Retrieve document as row Strings
	 DocumentClient documentClient = cp.document("430ec972-3aa4-4fe8-88d7");
	 boolean x = documentClient.exist();
	 System.out.println("Does Exist--DOCID 430ec972-3aa4-4fe8-88d7 ?->"+ x);
	 documentClient = cp.document("430ec972-3aa4-4fe8-88d7-343a93e56e98");
	 x = documentClient.exist();
	 System.out.println("Does Exist--DOCID 430ec972-3aa4-4fe8-88d7-343a93e56e98 ?->"+ x);
	 Optional<String> y = documentClient.find();
	 System.out.println ("Document--430ec972-3aa4-4fe8-88d7-343a93e56e98->"+ y.get());
	 return ("Document--430ec972-3aa4-4fe8-88d7-343a93e56e98->"+ y.get());
	 //findDealersNames("Cliff Wicklo6");
	 //return "Hello";
 }
}
