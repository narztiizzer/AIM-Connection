package group.aim.framework.connection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
/**
 * Created by NARZTIIZZER on 2/23/2016.
 */
class ConnectionManager {

    public static int ERROR_EXCEPTION_RESPONSE_CODE = 9925;
    public static final int DEFAULT_REQUEST_TIMEOUT = 90000;
    public static final String DEFAULT_REQUEST_METHOD = "POST";

    public static HttpsURLConnection createHttpsConnection(String tag, String url , String method , int timeout) {
        try {
            URL requestUrl = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) requestUrl.openConnection();
            conn.setReadTimeout(timeout);
            conn.setConnectTimeout(timeout);
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Cache-Control", "no-cache");

            Log.d(tag, "CREATE CONNECTION SUCCESS.");
            return conn;
        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.d(tag, "CREATE CONNECTION FAIL, CAUSE : " + e.getMessage());
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d(tag, "CREATE CONNECTION FAIL, CAUSE : " + e.getMessage());
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(tag, "CREATE CONNECTION FAIL, CAUSE : " + e.getMessage());
            return null;
        }
    }

    public static HttpsURLConnection createHttpsConnection(String tag, String url) {
        return createHttpsConnection(tag, url, DEFAULT_REQUEST_METHOD , DEFAULT_REQUEST_TIMEOUT);
    }

    public static HttpsURLConnection createHttpsConnection(String tag , String url , String method ) {
        return createHttpsConnection(tag, url, method , DEFAULT_REQUEST_TIMEOUT);
    }

    public static HttpsURLConnection createHttpsConnection(String tag, String url , int timeout) {
        return createHttpsConnection(tag, url, DEFAULT_REQUEST_METHOD , timeout);
    }

    public static HttpURLConnection createHttpConnection(String tag, String url , String method , int timeout) {

        try {
            URL requestUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
            conn.setReadTimeout(timeout);
            conn.setConnectTimeout(timeout);
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Cache-Control", "no-cache");

            Log.d(tag, "CREATE CONNECTION SUCCESS.");
            return conn;
        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.d(tag, "CREATE CONNECTION FAIL, CAUSE : " + e.getMessage());
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d(tag, "CREATE CONNECTION FAIL, CAUSE : " + e.getMessage());
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(tag, "CREATE CONNECTION FAIL, CAUSE : " + e.getMessage());
            return null;
        }
    }

    public static HttpURLConnection createHttpConnection(String tag, String url) {
        return createHttpConnection(tag, url, DEFAULT_REQUEST_METHOD , DEFAULT_REQUEST_TIMEOUT);
    }

    public static HttpURLConnection createHttpConnection(String tag , String url , String method ) {
        return createHttpConnection(tag, url, method , DEFAULT_REQUEST_TIMEOUT);
    }

    public static HttpURLConnection createHttpConnection(String tag, String url , int timeout) {
        return createHttpConnection(tag, url, DEFAULT_REQUEST_METHOD , timeout);
    }

    public static void postQueryParameterString(HttpURLConnection connection, String queryString) {

        try {
            OutputStream outputReader = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputReader, "UTF-8"));
            writer.write(queryString);
            writer.flush();
            writer.close();
            outputReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int getConnectionResponseCode(String tag, HttpURLConnection connection) {
        try {
            int responseCode = connection.getResponseCode();
            Log.d(tag, "REQUEST RESPONSE CODE COMPLETE, CODE : " + responseCode);

            return responseCode;
        } catch (IOException e) {
            Log.d(tag, "REQUEST RESPONSE CODE FAIL, CODE : " + ERROR_EXCEPTION_RESPONSE_CODE);
            return ERROR_EXCEPTION_RESPONSE_CODE;
        }
    }

    public static String getConnectionResponseString(String tag, HttpURLConnection connection) {

        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = inputReader.readLine()) != null) {
                response.append(inputLine);
            }

            inputReader.close();

            Log.d(tag, "REQUEST RESPONSE STRING COMPLETE.");
            return response.toString();
        } catch (IOException e) {
            Log.d(tag, "REQUEST RESPONSE STRING FAIL, CAUSE : " + e.getMessage());
            return e.getMessage();
        }
    }

}
