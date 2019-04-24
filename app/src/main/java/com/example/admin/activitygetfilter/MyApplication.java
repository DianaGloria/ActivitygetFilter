package com.example.admin.activitygetfilter;
import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MyApplication extends Application{
    public static final String TAG = MyApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getmInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        //set the defauld tag if tag is empy
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag) {
        if (mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }


}
