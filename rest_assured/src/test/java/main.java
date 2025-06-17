import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class main {
    public static void main(String... args){
        JsonPath jsonPath = buildRequest().getBody().jsonPath();
        System.out.println(jsonPath.getList("data",User.class)
                .stream()
                .filter(current-> current.getFirst_name().equals("Michael"))
                .findFirst()
                .orElseThrow(null));
    }

    public static Response buildRequest(){
        RestAssured.baseURI ="https://reqres.in/api";
        RequestSpecification request = RestAssured.given();
        request.when().param("page","2");
        return request.get("/users");

    }
}
