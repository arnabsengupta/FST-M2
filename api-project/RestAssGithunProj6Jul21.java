package GitHubRestAssuredProjectJul6;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class RestAssGithunProj6Jul21 {
	
	 // Declare request specification
    RequestSpecification requestSpec;
    // Declare response specification
    ResponseSpecification responseSpec;
    
    String SSHkey;
    
    int sshkeyID;
    
    @BeforeClass
    public void setUp() {
    	
    	// Create request specification
        requestSpec = new RequestSpecBuilder()
                // Set content type
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "token xx")
                // Set base URL
                .setBaseUri("https://api.github.com")
                // Build request specification
                .build();
        
 SSHkey="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCNBXS/dfujJ8hDPQWbS0Vx8vn9kmL3wsSJDTFHVGsNT+VCnFkxe0Trk16kt/+zyFxVead3RYf/u/zW9eG6aZxSekLv119Mavl+lKBblQMfYk8SKY9nCymiN+GxRVLzx/AMeI5b5fsKmI8s0xl3IzBUOeXABXZ7Unead7nE+de4+w==";
    }
    
    @Test(priority=1)
    public void addKey() {
    	  // Create JSON request
        String reqBody = "{\"title\": \"TestAPIKey\", \"key\": \""+SSHkey+"\"}";
        System.out.println("reqBody : "+reqBody);
		
		  Response response =
		  given().spec(requestSpec).when().body(reqBody).post("/user/keys");// Send POST request 
		  //extract 
		// Extract name from response
		//  sshkeyID = Integer.parseInt(response.then().extract().header("id"));
		// Extract key from response
		  sshkeyID = response.then().extract().path("id");
		//  Print response 
		  String body = response.getBody().asPrettyString();
		  System.out.println("response body: "+body);
		  System.out.println("sshkeyID: "+sshkeyID);
		 
		  
		  //Assertions
		  response.then().statusCode(201);
		  response.then().log().body();
		 
    }
    @Test(priority=2)
    public void getKeys() {
    	
    	 Response response = 
 		        given().spec(requestSpec).when().pathParam("keyID", sshkeyID).get("/user/keys/{keyID}");// Send POST request
 		 
 		    // Print response
 		    String body = response.getBody().asPrettyString();
 		    System.out.println(body);
 		    
 		   //Assertions
 			  response.then().statusCode(200);
 			  response.then().log().all();
 			 
    }

    @Test(priority=3)
    public void DeleteKeys() {
    	
    	 Response response = 
 		        given().spec(requestSpec).when().pathParam("keyID", sshkeyID).delete("/user/keys/{keyID}");// Delete request
 		 
 		    // Print response
 		    String body = response.getBody().asPrettyString();
 		    System.out.println(body);
 		    
 		    //Assertion
 		    response.then().statusCode(204);
 			  response.then().log().all();
    }

}
