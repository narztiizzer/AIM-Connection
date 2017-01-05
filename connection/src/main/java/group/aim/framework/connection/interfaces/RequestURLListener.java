package group.aim.framework.connection.interfaces;

/**
 * Created by Nattapongr on 9/28/2016 AD.
 */

public interface RequestURLListener {
    void onRequestURLSuccess(int code , String tag , String responseString);

    void onRequestURLFailed(int code , String tag , String exception);
}
