# RetrofitAPI
Retrofit API


Retrofit is a rest client for java and android.

 Retrofit library developed by Square to handle REST API calls in our android application.

 It is used for network transaction.

 It makes it relatively easy to retrieve and upload JSON through REST webservice .

 Retrofit automatically serialises the JSON response using a POJO(Plain Old Java Object).

 Retrofit is a type safe http client which has a very high benchmark scores on comparison with other network libraries .

 We need three things when work with Retrofit .

Model class which is used to map the JSON data (response parse from sever).
Interfaces which defines the possible HTTP operations (defines different operatrions).
Builder class - defining the URL end point for the HTTP operation.
 No need to take care about parsing of JSON response.  It is developed by Square. 

```
package retrofitnewapi.com.retrofit_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listVie);
        // to get image from server and load to listview
        getPhoto();
    }

    private void getPhoto() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<ImagePojo>> call = api.getPhoto();

        call.enqueue(new Callback<List<ImagePojo>>() {
            @Override
            public void onResponse(Call<List<ImagePojo>> call, Response<List<ImagePojo>> response) {
                List<ImagePojo> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getTitle();
                }
                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
            }

            @Override
            public void onFailure(Call<List<ImagePojo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
```
