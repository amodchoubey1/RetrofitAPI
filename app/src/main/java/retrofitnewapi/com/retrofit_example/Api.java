package retrofitnewapi.com.retrofit_example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
     String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("photos")
    Call<List<ImagePojo>> getPhoto();
}
