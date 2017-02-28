package interviewmaster.admin.interview.com.checking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Endpoint {
    @GET("/employeesList")
   Call<List<Example>> getEmployeedetails();
}
