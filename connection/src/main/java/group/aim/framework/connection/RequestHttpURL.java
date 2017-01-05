package group.aim.framework.connection;

import android.net.Uri;
import android.os.AsyncTask;

import java.net.HttpURLConnection;

import group.aim.framework.connection.interfaces.RequestURLListener;

/**
 * Created by narztiizzer on 11/18/2016 AD.
 */

public class RequestHttpURL {
    private static RequestHttpURL selfInstance;
    private static String requestURL;

    private int requestTimeout;
    private String requestTag;
    private String requestMethod;
    private Uri.Builder paramsBuilder;
    private RequestURLListener requestCallback;

    public static RequestHttpURL to(String targetURL) {
        selfInstance = new RequestHttpURL();
        selfInstance.requestURL = targetURL;
        return selfInstance;
    }

    public RequestHttpURL addParameters(String key , String value) {
        if(this.paramsBuilder == null)
            this.paramsBuilder = new Uri.Builder();
        this.paramsBuilder.appendQueryParameter(key , value);

        return this;
    }

    public RequestHttpURL addParameters(String key , long value) {
        return addParameters(key , value + "");
    }

    public RequestHttpURL addParameters(String key , double value) {
        return addParameters(key , value + "");
    }

    public RequestHttpURL setRequestTimeout(int timeout) {
        this.requestTimeout = timeout;
        return this;
    }

    public RequestHttpURL setRequestMethod(String method) {
        this.requestMethod = method;
        return this;
    }

    public RequestHttpURL setRequestTag(String tag) {
        this.requestTag = tag;
        return this;
    }

    public RequestHttpURL setRequestURLListener(RequestURLListener callback) {
        this.requestCallback = callback;
        return this;
    }

    public void start() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = ConnectionManager
                        .createHttpConnection(requestTag
                                , requestURL
                                , requestMethod == null ? ConnectionManager.DEFAULT_REQUEST_METHOD : requestMethod
                                , requestTimeout < 0 ? ConnectionManager.DEFAULT_REQUEST_TIMEOUT : requestTimeout
                        );

                if(paramsBuilder != null)
                    ConnectionManager.postQueryParameterString(conn, paramsBuilder.build().getEncodedQuery());

                int responseCode = ConnectionManager.getConnectionResponseCode(requestTag , conn);
                String responseString = ConnectionManager.getConnectionResponseString(requestTag , conn);

                if(responseCode == 200)
                    requestCallback.onRequestURLSuccess(responseCode , requestTag , responseString);
                else
                    requestCallback.onRequestURLFailed(responseCode , requestTag , responseString);
            }
        });
    }
}
