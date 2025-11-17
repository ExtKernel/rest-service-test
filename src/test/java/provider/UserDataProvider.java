package provider;

import org.testng.annotations.DataProvider;

public class UserDataProvider {

    @DataProvider
    public Object[][] userRequests(){
        return new Object[][] {
                {"testGetAllUsers_Positive", "/users", 200},
                {"testFilterByAge_Positive", "/users?age=30", 200},
                {"testFilterByGender_Positive", "/users?gender=female", 200},
                {"testInvalidAge_Negative", "/users?age=-1", 400},
                {"testInternalServerError_Negative", "/users?trigger=error", 500},
                {"testInvalidGender_Negative", "/users?gender=unknown", 422}
        };
    }
}
