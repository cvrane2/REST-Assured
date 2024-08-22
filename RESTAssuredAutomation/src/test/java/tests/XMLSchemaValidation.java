package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

public class XMLSchemaValidation {

	@Test
	public void schemaValidation() throws IOException {
		
File file = new File("./SoapRequest/ListOfContinentsByName.xml");
		
		if(file.exists())
			System.out.println("  >> File Exists");
			
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		
		baseURI = "http://webservices.oorsprong.org";
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("/websamples.countryinfo/CountryInfoService.wso").
		then().
			statusCode(200).log().all().
			and().
				body("//*:sName/text()", contains("AFAfricaANAntarcticaASAsiaEUEuropeOCOcenaniaAMThe Americas")).
			and().
				assertThat().body(matchesXsdInClasspath("ListOfContinentsByName.xsd"));
				
		
	}
	
}
