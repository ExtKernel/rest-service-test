package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import persistence.OrmDbManager;
import provider.UserDataProvider;

import java.util.List;

public class UsersApiTests {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test(dataProvider = "userRequests", dataProviderClass = UserDataProvider.class)
    public void testUsers(String testName, String endpoint, int expectedStatus) {
        try {
            Response response = RestAssured.given()
                    .baseUri("http://localhost:8080")
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();

            int actualStatus = response.statusCode();
            Assert.assertEquals(actualStatus, expectedStatus,
                    "Unexpected HTTP status for test: " + testName);

            // Validate response body
            if (expectedStatus == 200) {
                List<User> users = mapper.readValue(
                        response.asByteArray(),
                        new TypeReference<>() {}
                );

                Assert.assertNotNull(users, "User list should not be null");
                Assert.assertFalse(users.isEmpty(), "User list should not be empty for " + testName);
            }

            OrmDbManager.saveResult(testName, "PASSED");

        } catch (AssertionError | Exception exception) {
            OrmDbManager.saveResult(testName, "FAILED");

            // Rethrow to mark that the test failed in the report
            Assert.fail("Test " + testName + " failed: " + exception.getMessage());
        }
    }
}