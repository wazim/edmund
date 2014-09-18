package net.wazim.edmund.client;

import net.wazim.edmund.model.EdmundResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.ArrayList;
import java.util.List;

public class EdmundHttpClient {

    private List<NameValuePair> queryParameters;
    private final HttpClient httpClient;

    public EdmundHttpClient() {
        httpClient = new HttpClient();
        queryParameters = new ArrayList<NameValuePair>();
    }

    public EdmundResponse getRequest(String requestUrl) {
        HttpMethod method = new GetMethod(requestUrl);
        method.setQueryString(getNameValuePairs());
        try {
            int responseCode = httpClient.executeMethod(method);
            String responseBody = method.getResponseBodyAsString();
            return new EdmundResponse(responseCode, responseBody.trim());
        }
        catch(Exception e){
            return new EdmundResponse(e);
        }
    }

    public EdmundResponse postRequest(String requestUrl) {
        HttpMethod method = new PostMethod(requestUrl);
        method.setQueryString(getNameValuePairs());
        try {
            int responseCode = httpClient.executeMethod(method);
            String responseBody = method.getResponseBodyAsString();
            return new EdmundResponse(responseCode, responseBody.trim());
        }
        catch(Exception e) {
            return new EdmundResponse(e);
        }
    }

    public void addQueryParameter(String key, String value) {
        queryParameters.add(new NameValuePair(key, value));
    }

    public List<NameValuePair> queryParams() {
        return queryParameters;
    }

    private NameValuePair[] getNameValuePairs() {
        NameValuePair[] array = new NameValuePair[queryParameters.size()];
        for(int i = 0; i< queryParameters.size(); i++){
            array[i] = queryParameters.get(i);
        }
       return array;
    }

}
