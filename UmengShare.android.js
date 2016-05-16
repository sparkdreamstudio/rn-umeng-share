import { NativeModules } from 'react-native';
const umengClient = NativeModules.UmengShare;
var resolveAssetSource = require('../react-native/Libraries/Image/resolveAssetSource');

export default class UmengShare{

    // static setAppKey(value){
    //     umengClient.setAppKey(value);
    // }

    static setWXAppId(appId,appSecret){
        umengClient.setWXAppId(appId,appSecret);
    }

    //iOS
    static setQQZone(appId,appSecret){
      umengClient.setQQZone(appId,appSecret);
    }

    static setSinaWeibo(appKey,secret){
        umengClient.setSinaWeibo(appKey,secret);
    }

    static openShareAction(content,title,url,imageSource){
        console.log(resolveAssetSource(imageSource))
        umengClient.openShareAction(content,title,url,resolveAssetSource(imageSource));
    }

}
