package interviewmaster.admin.interview.com.checking;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView listView;
    MyAdapte myAdapte;
    Context context;
    DBhandler dBhandler = new DBhandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (RecyclerView) findViewById(R.id.list_View);
        listView.hasFixedSize();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);
        final Endpoint e = ApiClient.getApiService();
        Call<List<Example>> res = e.getEmployeedetails();
        res.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {

                List<Employee> emp = response.body().get(0).getEmployee();
                dBhandler.addFoodCountryName(emp);
                myAdapte = new MyAdapte(getApplicationContext(), dBhandler.getAllFoodCountryName());
                listView.setAdapter(myAdapte);
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

            }
        });

    }
}
