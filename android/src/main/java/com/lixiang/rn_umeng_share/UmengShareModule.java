package com.lixiang.rn_umeng_share;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.*;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;



public class UmengShareModule extends ReactContextBaseJavaModule {

  private Context context;
  public UmengShareModule(ReactApplicationContext reactContext) {
      super(reactContext);
      context = reactContext.getBaseContext();
  }

    @Override
    public String getName() {
        return "RCTUmengShare";
    }

    @ReactMethod
    public void setWXAppId(String appid,String secret)
    {
        PlatformConfig.setWeixin(appid,secret);
    }

    @ReactMethod
    public void setQQZone(String appid,String secret)
    {
        PlatformConfig.setQQZone(appid,secret);
    }

    @ReactMethod
    public  void setSinaWeibo(String appkey,String secret)
    {
        PlatformConfig.setSinaWeibo(appkey,secret);

    }

    @ReactMethod
    public void openShareAction(String content,String title,String url,String imageSource)
    {
        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA
                };
        final Activity tempActivity = this.getCurrentActivity();
        final String finalContent = content;
        final String finaltitle = title;
        final String finalurl = url;
        this.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                new ShareAction(tempActivity).setDisplayList(displaylist)
                        .withText(finalContent)
                        .withTitle(finaltitle)
                        .withTargetUrl(finalurl)
                        .open();
            }
        });

    }
}
