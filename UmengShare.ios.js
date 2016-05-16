import { NativeModules } from 'react-native';
const umengClient = NativeModules.UmengShare;
var resolveAssetSource = require('../react-native/Libraries/Image/resolveAssetSource');
export default class UmengShare{

    static setAppKey(value){
        umengClient.setAppKey(value);
    }

    static setWXAppId(appId,appSecret,url){
        umengClient.setWXAppId(appId,appSecret,url);
    }

    //iOS
    static setQQWithAppId(appId,appSecret,url,supportWebView){
      umengClient.setQQWithAppId(appId,appSecret,url,supportWebView);
    }

    static openNewSinaSSOWithAppKey(appKey,secret,url){
        umengClient.openNewSinaSSOWithAppKey(appKey,secret,url);
    }

    static setQQData(dic){
        if(dic.imageSource !== undefined)
        {
          dic.imageSource = resolveAssetSource(dic.imageSource)
        }
        umengClient.setQQData(dic);
    }

    static setQzoneData(dic){
        if(dic.imageSource !== undefined)
        {
          dic.imageSource = resolveAssetSource(dic.imageSource)
        }
        umengClient.setQzoneData(dic);
    }
    static setWechatSessionData(dic){
      if(dic.imageSource !== undefined)
      {
        dic.imageSource = resolveAssetSource(dic.imageSource)
      }
        umengClient.setWechatSessionData(dic);
    }
    static setWechatTimelineData(dic){
        if(dic.imageSource !== undefined)
        {
          dic.imageSource = resolveAssetSource(dic.imageSource)
        }
        umengClient.setWechatTimelineData(dic);
    }

    static setSinaData(dic){
        umengClient.setSinaData(dic);
    }

    static presentSnsIconSheetView(content,imageSource){
        console.log(resolveAssetSource(imageSource))
        umengClient.presentSnsIconSheetView(content,resolveAssetSource(imageSource));
    }

}
