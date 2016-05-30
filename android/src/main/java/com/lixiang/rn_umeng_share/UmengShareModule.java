package com.lixiang.rn_umeng_share;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.common.util.UriUtil;
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
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;

import java.net.URLEncoder;
import java.util.HashMap;

import java.net.URLEncoder.*;

import com.facebook.imagepipeline.request.*;

import javax.annotation.Nullable;

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
    public void openShareAction(String content,String title,String url,ReadableMap imageSource)
    {
        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE
                };
        final Activity tempActivity = this.getCurrentActivity();
        final Context tempContext = this.getReactApplicationContext();
        final String finalContent = content;
        final String finaltitle = title;
        final UmengShareModule finalThis = this;
        try{
            final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

            final String finalurl = Uri.encode(url,ALLOWED_URI_CHARS) ;
            final String imageUrl = imageSource.getString("uri");

            this.getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try{
                        UMImage image;
                        if(imageUrl.contains("http://")||imageUrl.contains("https://"))
                        {
                            image = new UMImage(tempActivity, Uri.encode(imageUrl,ALLOWED_URI_CHARS));

                        }
                        else{
                            Uri uri = Uri.parse(imageUrl);
                            Bitmap bitmap;
                            if(uri.getScheme() == null)
                            {
                                int resId = finalThis.getResourceDrawableId(tempContext,imageUrl);
                                bitmap = BitmapFactory.decodeResource(tempActivity.getResources(),resId);
                            }
                            else{
                                String tempUrlString = "";
                                tempUrlString = imageUrl.replace("file://","");
                                bitmap = BitmapFactory.decodeFile(tempUrlString);
                            }
                            image = new UMImage(tempActivity,bitmap);
                        }
                        new ShareAction(tempActivity).setDisplayList(displaylist)
                                .withText(finalContent)
                                .withTitle(finaltitle)
                                .withTargetUrl(finalurl)
                                .withMedia(image)
                                .open();
                    }
                    catch (Exception e)
                    {
                        Log.e("友盟分享错误",e.toString());
                    }

                }
            });
        }
        catch (Exception e) {
            Log.e("友盟分享错误", e.toString());
        }
    }

    private int getResourceDrawableId(Context context, @Nullable String name) {
        if (name == null || name.isEmpty()) {
            return 0;
        }
        name = name.toLowerCase().replace("-", "_");

        int id = context.getResources().getIdentifier(
                name,
                "drawable",
                context.getPackageName());
        return id;
    }

    private @Nullable
    Drawable getResourceDrawable(Context context, @Nullable String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? context.getResources().getDrawable(resId) : null;
    }

    private Uri getResourceDrawableUri(Context context, @Nullable String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(resId))
                .build() : Uri.EMPTY;
    }
}
