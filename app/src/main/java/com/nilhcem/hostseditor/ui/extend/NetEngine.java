package com.nilhcem.hostseditor.ui.extend;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

/**
 * Created by yftx on 2/2/15.
 */
public class NetEngine {
    private static final String BASE_URL = "https://leancloud.cn:443/1";
    private static final String HOST_TYPE = "/classes/hostsType";
    private static final String HOSTS = "/classes/host";
    Context context;
    RequestQueue mRequestQueue;
    Error error = new Error();

    public NetEngine(Context context) {
        this.context = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void getHostList(Response.Listener<JSONObject> listener) {
        Request request = new Request(Request.Method.GET, BASE_URL + HOST_TYPE, null, listener, error);
        request.setShouldCache(false);
        mRequestQueue.add(request);
    }

    public void getHost(Response.Listener<JSONObject> listener, int index) {
        Request request = new Request(Request.Method.GET, BASE_URL + HOSTS + "?where=" + URLEncoder.encode("{\"index\":" + index + "}"), null, listener, error);
        request.setShouldCache(false);
        mRequestQueue.add(request);
    }

    class Error implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Timber.e(volleyError.toString());
        }
    }


    class Request extends JsonObjectRequest {

        public Request(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }

        public Request(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(url, jsonRequest, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("X-AVOSCloud-Application-Id", "vxwedsqjf0dsddtwn2xhd2hw9rl5tk8pu115k1av9du0wntm");
            headers.put("X-AVOSCloud-Application-Key", "o95gfs76gzd2oginakdsks2ds2rqdfae3w7ux0sqy7d6e4g8");
            return headers;
        }
    }


}
