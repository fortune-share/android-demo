package com.example.xuanfu.http;

import android.util.Log;
import android.widget.Toast;

import com.example.xuanfu.Application;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * @Author： 淘跑
 * @Date: 2018/7/5 11:43
 * @Use：该类仅供参考，实际业务Code, 根据需求来定义，
 */
public abstract class RxSubscriber<T> extends DisposableObserver<T> {

    /**
     * 是否显示错误布局
     */
    private boolean isShowError = false;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>构造函数>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    /**
     * @param isShowError 是否显示错误界面(默认不显示)
     */
    public RxSubscriber(boolean isShowError) {
        this.isShowError = isShowError;
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<构造函数<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public void onError(Throwable e) {
//        onError 这个方法执行之后 就不会执行onComplete()方法了


        //通用处理
        Log.i("TAG", "=================================onError");

        //TODO 关闭刷新动画(考虑当中)
        //TODO 显示加载错误的界面(考虑当中)

        //TODO json解析异常
        if (e instanceof JSONException
                || e instanceof JsonParseException
                || e instanceof ParseException) {

            Toast.makeText(Application.getContext(), "Json解析异常", Toast.LENGTH_SHORT).show();
            //TODO 显示加载错误的界面
        }


        if (e instanceof HttpException) {
//            errorHandle(e);
        }

        // TODO: 处理其它通用错误
        // 覆写此方法自行处理异常，通用异常使用super.onError(e)保留
        e.printStackTrace();
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.i("TAG", "=================================onStart");

        if (!NetworkUtil.isNetworkAvailable(Application.getContext())) {
            Toast.makeText(Application.getContext(), "无网络，请检查网络设置", Toast.LENGTH_SHORT).show();
            onComplete();
        }
    }

    @Override
    public void onNext(T t) {
//        if (t instanceof BaseResponse) {
//            BaseResponse baseResponse = (BaseResponse) t;
//            // 判断是否请求错误，出错直接转到onError()
//            if (!baseResponse.isOk()) {
////                Throwable e = new Throwable(response.getErrorMsg());
////                this.onError(e);
//                //普通错误只需显示Toast即可
//                switch (baseResponse.getErrorCode()) {
//                    case 400://Bad Request 请求出现语法错误, 一般是请求参数不对
//                    case 404://Not Found 无法找到指定位置的资源
//                    case 403://Forbidden 资源不可用
//                    case 500://服务器内部错误, 请联系Java后台开发人员 !!!
//                    case 401://Unauthorized 访问被拒绝
//                        break;
//                    case 10000://服务器忙
//                        break;
//                    case 10006://验证码错误
//                        break;
//                    case 102://登录失效
//                        showCodeOrMessage.set(new EventData(baseResponse.getErrorCode(), baseResponse.getErrorMsg(), 1));
//                        break;
//                    default:
//                        //返回错误码以及错误信息  想处理错误信息的话就自己重写此方法
//                        showCodeOrMessage.set(new EventData(baseResponse.getErrorCode(), baseResponse.getErrorMsg(), 0));
//                }
//                //关于登录方面的错误
////                if(mView!=null) {
////                    mView.showLoginDialog(response.getErrorMsg());
////                }
//                return;
//            }
//        }
        onResult(t);
    }

    public abstract void onResult(T t);

    public abstract void onError(String error);

    @Override
    public void onComplete() {
        Log.i("TAG", "=================================onComplete");
        // 隐藏正在显示的dialog
//        hideDialog.set(!hideDialog.get());
        //TODO 关闭刷新动画(考虑当中)
    }

    /**
     * 错误处理
     *
     * @param e
     */
//    private void errorHandle(Throwable e) {
//        if (!TextUtils.isEmpty(e.toString()) && !TextUtils.isEmpty(e.getMessage())) {
//            HttpException httpException = (HttpException) e;
//            int code = httpException.code();
//            int noOperationAuthority = 403;
//            int interfaceNotFound = 404;
//            int serviceNoResponse = 500;
//            int illegalInput = 400;
//            if (code == noOperationAuthority) {
//                this.onError("无操作权限");
//            } else if (code == interfaceNotFound) {
//                this.onError("接口未找到");
//            } else if (code >= serviceNoResponse) {
//                this.onError("服务正在维护中");
//            } else if (code >= illegalInput) {
//                try {
//                    ResponseBody responseBody = httpException.response().errorBody();
//                    String errorString = responseBody.string();
//                    ErrorTipsBean errorTipsBean = new Gson().fromJson(errorString, ErrorTipsBean.class);
//                    if (null != errorTipsBean) {
//                        this.onError(errorTipsBean.getTooltip());
//                    } else {
//                        //未知的错误
//                        this.onError(e.getMessage());
//                    }
//                } catch (IOException e2) {
//                    //未知的错误
//                    this.onError(e.getMessage());
//                    e2.printStackTrace();
//                }
//            } else {
//                //未知的错误
//                this.onError(e.getMessage());
//            }
//        }
//    }

}
