package com.lbadvisor.AgeGroup.network;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.bean.GenericMessage;
import com.lbadvisor.AgeGroup.bean.Message;
import com.lbadvisor.AgeGroup.utils.DialogUtil;
import com.lbadvisor.AgeGroup.utils.LogUtils;
import com.lbadvisor.AgeGroup.utils.ToastUtil;
import com.lbadvisor.AgeGroup.utils.UIUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * 简单HTTP工具类封装
 *
 * @author likun
 */
public class HttpTool {
    private static HttpUtils httpUtils = null;

    public static void init() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
    }

    /**
     * GET 方式 ，无参
     *
     * @param context 上下文
     * @param url     路径
     */
    public static void sendByGet(final Context context, final String url, final OnNetWorkListener listener) {
        sendByGet(context, url, listener, true);
    }

    public static void sendByGet(final Context context, final String url, final OnNetWorkListener listener, final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }

        final String pathUrl = UIUtils.addRequestUrlParams(url, UIUtils.addBasicParams());

        httpUtils.send(HttpMethod.GET, pathUrl, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, pathUrl, null, null, listener, arg1, arg0, showDialog);

            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, pathUrl, null, null, null, listener, result, showDialog);
            }

        });

    }

    /**
     * GET 方式 ，有参
     *
     * @param context 上下文
     * @param url     路径
     * @param params  参数 List<NameValuePair>
     */

    public static void sendByGet(final Context context, final String url, List<NameValuePair> params, final OnNetWorkListener listener) {
        sendByGet(context, url, params, listener, true);
    }

    public static void sendByGet(final Context context, final String url, final List<NameValuePair> params, final OnNetWorkListener listener,
                                 final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }

        final RequestParams rp = new RequestParams();
        rp.addBodyParameter(params);


        final String pathUrl = UIUtils.addRequestUrlParams(url, UIUtils.addBasicParams());


        httpUtils.configCurrentHttpCacheExpiry(500);
        httpUtils.send(HttpMethod.GET, pathUrl, rp, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, pathUrl, null, rp, listener, arg1, arg0, showDialog);
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, pathUrl, null, rp, null, listener, result, showDialog);
            }
        });
    }

    /**
     * GET 方式 ，无参，并解析json
     *
     * @param <T>
     * @param context 上下文
     * @param url     路径
     * @param mClass  要解析的json对象
     */

    public static <T> void sendByGet(final Context context, final String url, final Class<T> mClass, final OnNetWorkListener listener) {
        sendByGet(context, url, mClass, listener, true);
    }

    public static <T> void sendByGet(final Context context, final String url, final Class<T> mClass, final OnNetWorkListener listener, final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }

        httpUtils.configCurrentHttpCacheExpiry(500);
        final RequestParams rp = new RequestParams();
//		LoginInfo info = AccountManager.getInstance().getLoginInfo();
        String token = null;

//		if (info != null) {
//			if (info.getToken() != null) {
//				token = info.getToken();
//				rp.setHeader("token", token);
//			}
//		}

        final String pathUrl = UIUtils.addRequestUrlParams(url, UIUtils.addBasicParams());

        final String tokenParam = token;

        httpUtils.send(HttpMethod.GET, pathUrl, rp, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, pathUrl, tokenParam, rp, listener, arg1, arg0, showDialog);
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, pathUrl, tokenParam, rp, mClass, listener, result, showDialog);
            }
        });
    }

    /**
     * GET 方式 ，有参，并解析json
     *
     * @param context 上下文
     * @param url     路径
     * @param params  参数 List<NameValuePair>
     * @param mClass  要解析的json对象
     */

    public static <T> void sendByGet(final Context context, final String url, List<NameValuePair> params, final Class<T> mClass,
                                     final OnNetWorkListener listener) {
        sendByGet(context, url, params, mClass, listener, true);
    }

    public static <T> void sendByGet(final Context context, final String url, final List<NameValuePair> params, final Class<T> mClass,
                                     final OnNetWorkListener listener, final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }

        final RequestParams rp = new RequestParams();
        rp.addBodyParameter(params);

        final String pathUrl = UIUtils.addRequestUrlParams(url, UIUtils.addBasicParams());

        httpUtils.send(HttpMethod.GET, url, rp, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, pathUrl, null, rp, listener, arg1, arg0, showDialog);
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, pathUrl, null, rp, mClass, listener, result, showDialog);
            }
        });
    }

    /**
     * POST 方式 ，无参
     *
     * @param context 上下文
     * @param url     路径
     */

    public static void sendByPost(final Context context, final String url, final OnNetWorkListener listener) {
        sendByPost(context, url, listener, true);
    }

    public static void sendByPost(final Context context, final String url, final OnNetWorkListener listener, final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }
        RequestParams rp = new RequestParams();
//		rp.addBodyParameter("deviceId", Utility.generateUDID());
//		rp.addBodyParameter("channelId", TTC.getInstance().getChannel());
//		rp.addBodyParameter("v", Utility.getLocalVersion(TTC.getInstance().getContext()));
        httpUtils.send(HttpRequest.HttpMethod.POST, url, rp, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, url, null, null, listener, arg1, arg0, showDialog);
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, url, null, null, null, listener, result, showDialog);
            }

        });
    }

    /**
     * POST 方式 ，有参
     *
     * @param context 上下文
     * @param url     路径
     * @param params  参数 List<NameValuePair>
     */

    public static void sendByPost(final Context context, final String url, List<NameValuePair> params, final OnNetWorkListener listener) {
        sendByPost(context, url, params, listener, true);
    }

    public static void sendByPost(final Context context, final String url, final List<NameValuePair> params, final OnNetWorkListener listener,
                                  final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }

        final RequestParams rp = new RequestParams();
//		rp.addBodyParameter("deviceId", Utility.generateUDID());
//		rp.addBodyParameter("channelId", TTC.getInstance().getChannel());
//		rp.addBodyParameter("v", Utility.getLocalVersion(TTC.getInstance().getContext()));
//		LoginInfo info = AccountManager.getInstance().getLoginInfo();
        String token = null;

//		if (info != null) {
//			if (info.getToken() != null) {
//				token = info.getToken();
//				rp.setHeader("token", token);
//			}
//		}
        final String tokenParam = token;

        httpUtils.send(HttpMethod.POST, url, rp, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, url, tokenParam, rp, listener, arg1, arg0, showDialog);
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, url, tokenParam, rp, null, listener, result, showDialog);
            }
        });
    }

    /**
     * POST 方式 ，无参 ，并且解析json 需传入Class
     *
     * @param context 上下文
     * @param url     路径
     * @param mClass  要解析的json对象
     */

    public static <T> void sendByPost(final Context context, final String url, final Class<T> mClass, final OnNetWorkListener listener) {
        sendByPost(context, url, mClass, listener, true);
    }

    public static <T> void sendByPost(final Context context, final String url, final Class<T> mClass, final OnNetWorkListener listener, final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }

        httpUtils.configCurrentHttpCacheExpiry(0);
        final RequestParams rp = new RequestParams();
//		rp.addBodyParameter("deviceId", Utility.generateUDID());
//		rp.addBodyParameter("channelId", TTC.getInstance().getChannel());
//		rp.addBodyParameter("v", Utility.getLocalVersion(TTC.getInstance().getContext()));
//		LoginInfo info = AccountManager.getInstance().getLoginInfo();
        String token = null;

//		if (info != null) {
//			if (info.getToken() != null) {
//				token = info.getToken();
//				rp.setHeader("token", token);
//			}
//		}

        final String tokenParam = token;

        httpUtils.send(HttpMethod.POST, url, rp, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, url, tokenParam, rp, listener, arg1, arg0, showDialog);
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, url, tokenParam, rp, mClass, listener, result, showDialog);
            }
        });

    }

    /**
     * POST 方式 ，有参，并解析json，需传入Class
     *
     * @param context 上下文
     * @param url     路径
     * @param params  参数 List《nameValuePair》
     * @param mClass  要解析的json对象
     */

    public static <T> void sendByPost(final Context context, final String url, List<NameValuePair> params, final Class<T> mClass,
                                      final OnNetWorkListener listener) {
        sendByPost(context, url, params, mClass, listener, true);
    }

    public static <T> void sendByPost(final Context context, final String url, final List<NameValuePair> params, final Class<T> mClass,
                                      final OnNetWorkListener listener, final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }

        final RequestParams rp = new RequestParams();
//		rp.addBodyParameter("deviceId", Utility.generateUDID());
//		rp.addBodyParameter("channelId", TTC.getInstance().getChannel());
//		rp.addBodyParameter("v", Utility.getLocalVersion(TTC.getInstance().getContext()));
//		LoginInfo info = AccountManager.getInstance().getLoginInfo();
        String token = null;

//		if (info != null) {
//			if (info.getToken() != null) {
//				token = info.getToken();
//				rp.setHeader("token", token);
//			}
//		}

        final String tokenParam = token;

        rp.addBodyParameter(params);
        httpUtils.send(HttpMethod.POST, url, rp, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, url, tokenParam, rp, listener, arg1, arg0, showDialog);
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, url, tokenParam, rp, mClass, listener, result, showDialog);
            }
        });
    }

    /**
     * POST 方式 ，有参，并解析json，需传入Class
     *
     * @param context 上下文
     * @param url     路径
     * @param params  参数 List《nameValuePair》
     * @param mClass  要解析的json对象
     */

    public static <T> void getCodeSendByPost(final Context context, final String url, List<NameValuePair> params, final Class<T> mClass, String sign,
                                             final OnNetWorkListener listener) {
        getCodeSendByPost(context, url, params, mClass, sign, listener, true);
    }

    public static <T> void getCodeSendByPost(final Context context, final String url, final List<NameValuePair> params, final Class<T> mClass, String sign,
                                             final OnNetWorkListener listener, final boolean showDialog) {

        if (showDialog) {
            DialogUtil.show(context);
        }

        final RequestParams rp = new RequestParams();
//		rp.addBodyParameter("deviceId", Utility.generateUDID());
//		rp.addBodyParameter("channelId", TTC.getInstance().getChannel());
//		rp.addBodyParameter("v", Utility.getLocalVersion(TTC.getInstance().getContext()));
        rp.setHeader("sign", sign);
        rp.addBodyParameter(params);

        httpUtils.send(HttpMethod.POST, url, rp, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                onResponseFailHandler(context, url, null, rp, listener, arg1, arg0, showDialog);
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
                onResponseSuccessHandler(context, url, null, rp, mClass, listener, result, showDialog);
            }
        });
    }

    private static boolean isContextFinished(Context context) {
        boolean ret = false;

        Activity activity = (Activity) context;
        if (activity == null) {
            ret = true;
        } else {
            ret = activity.isFinishing();// activity.isDestroyed() ||
        }

        return ret;
    }

    private static void onResponseFailHandler(Context context, String url, String token, RequestParams params, OnNetWorkListener listener, String result,
                                              HttpException ex, boolean showDialog) {
        ex.printStackTrace();

        debugLog(url, token, params, result);

        if (showDialog) {
            DialogUtil.close();
        }

        if (listener instanceof OnNetWorkSFListener) {
            ((OnNetWorkSFListener) listener).onNetWorkCallFailure();
        }

        if (ex instanceof HttpException) {
            toastShow(context, "网络连接失败，请检查网络设置");
            return;
        }

        printLog(context, url);
    }

    private static <T> void onResponseSuccessHandler(Context context, String url, String token, RequestParams params, Class<T> mClass,
                                                     OnNetWorkListener listener, ResponseInfo<String> result, boolean showDialog) {

        debugLog(url, token, params, result.result);

        if (showDialog) {
            DialogUtil.close();
        }
        if (!result.result.toString().contains("\"status\":")) {
            printLog(context, url);
            return;
        }
        if (mClass == null) {
            listener.onNetWorkCall(new ResponseBody(result.result));
        } else {
            if (!result.result.toString().contains("errorCode") || result.result.toString().contains("\"errorCode\":0")) {
                Message obj = (Message) JSONObject.parseObject(result.result.toString(), mClass);

                if (obj == null)
                    return;

                listener.onNetWorkCall(new ResponseBody(obj.getStatus(), obj));
            } else {
                GenericMessage obj = (GenericMessage) JSONObject.parseObject(result.result.toString(), GenericMessage.class);

                if (obj == null)
                    return;

//				if (showDialog && obj.getErrorCode() == ErrorCode.PassportErrors.ERROR_NOT_LOGIN) {
//					BusProvider.getInstance().post(new LoginEvent());
//
//					return;
//				}

                int resId = UIUtils.getResId("error_" + obj.getErrorCode(), R.string.class);

                String errorMessage = "";

                if (resId != -1) {
                    errorMessage = context.getResources().getString(resId);
                }

                listener.onNetWorkCall(new ResponseBody(obj.getStatus(), obj, errorMessage));
            }
        }
    }

    private static void toastShow(Context context, int text) {
        try {
            ToastUtil.show(context.getResources().getString(text));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static void toastShow(Context context, String text) {
        ToastUtil.show(text);
    }

    private static void debugLog(String url, String token, RequestParams params, String result) {
        if (!LogUtils.DebugMode) {
            return;
        }

        LogUtils.e("URL---", url);
        LogUtils.e("token", token != null ? token : "token is null");

        StringBuilder sb = new StringBuilder();
        ArrayList<BasicNameValuePair> paramsList = null;
        if (params != null) {
            try {
                // 反射字段
                Field fieldParams = params.getClass().getDeclaredField("bodyParams");
                fieldParams.setAccessible(true);
                paramsList = (ArrayList<BasicNameValuePair>) fieldParams.get(params);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if (paramsList != null) {
            for (NameValuePair item : paramsList) {
                sb.append(item.getName());
                sb.append(",");
                sb.append(item.getValue());
                sb.append("    ");
            }
        }
        LogUtils.e("body params", sb.toString());

        LogUtils.e("result", result);
    }

    private static void printLog(Context context, String url) {

        StringBuilder sb = new StringBuilder();
        sb.append("服务器出错，请稍后再试");

        sb.append(url);

        toastShow(context, sb.toString());

    }
}
