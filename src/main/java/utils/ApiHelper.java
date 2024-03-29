package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import reporting.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiHelper {

    public static String sendGetRequest(String url) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjUxMTI3MywiaXNzIjoiaHR0cDovL2VjMi0zNC0yMDAtMjI4LTIzNy5jb21wdXRlLTEuYW1hem9uYXdzLmNvbS9hcGkvdjEvYXV0aC9sb2dpbiIsImlhdCI6MTU2NzU0Mzk3NCwiZXhwIjoxNTY3NjMwMzc0LCJuYmYiOjE1Njc1NDM5NzQsImp0aSI6ImF0WHRrNGkzWnV6bFQ4MDMifQ.kysfU8CPyhdK_b19dh7FT65ZpqdLwfF1zrpdA10GGC0");
        HttpResponse response = null;
        String result = null;
        try {
            response = client.execute(request);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getCount(String searchQuery, String dataType) {
        String bigResponse = sendGetRequest("https://s5qp4t4bt1.execute-api.us-east-1.amazonaws.com/dev/api/v1/quick-search/institutions?filterBy=" + dataType + "&query=" + searchQuery);
        Matcher matcher = Pattern.compile("(?<=count\":)\\d+").matcher(bigResponse);
        matcher.find();
        int result = Integer.parseInt(matcher.group());
        Logger.info(dataType + " count (from API) : " + result);
        return result;
    }
}