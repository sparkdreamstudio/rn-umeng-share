#rn-umeng-share
# 安装
目前支持微信和qq分享

`npm install --save rn-umeng-share@https://github.com/sparkdreamstudio/rn-umeng-share.git`
# 手动添加到XCode
* 在XCode project navigator 中右键单击 Libraries -> Add Files to [你的项目名称] 进入 node_module/rn-umeng-share/ios 将 .xcodeproj 文件添加进来，并点击[你的项目名称] －> Build Phases 添加 Link Binary libRCTUmengshare.a
* TARGETS->BUILD SETTING->Framework Search Paths 添加 $(SRCROOT)/../node_modules/rn-umeng-share/ios/RCTUmengShare/UmengSocial/UMSocial_Sdk_Extra_Frameworks/TencentOpenAPI
* 在`Appdelegate.m`中添加如下代码

	```
	...
	#import "RCTUmengShare.h"
	...
	- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
	{
  		self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
  		UIViewController *rootViewController = [UIViewController new];
  		rootViewController.view = rootView;
  		self.window.rootViewController = rootViewController;
  		[self.window makeKeyAndVisible];
  		[RCTUmengShare setRootController:rootViewController]; //加入这行代码
	}
	```
	
* 在XCode project navigator 中右键单击 [你的项目名称] ->Add Files to [你的项目名称]添加第三方分享的依赖文件


	平台名称    | 文件路径
	-------------|------------
	微信          | node_module/rn-umeng-share/ios/RCTUumengShare/UmengSocial/UMSocial_Sdk_Extra_Frameworks/Wechat/libSocialWechat.a 和 libWeChatSDK.a
	新浪微博      |node_module/rn-umeng-share/ios/RCTUumengShare/UmengSocial/UMSocial_Sdk_Extra_Frameworks/SinaSSO/libSocialSinaSSO.a libWeiboSDK.a 和 WeiboSDK.bundle
	QQ           |node_module/rn-umeng-share/ios/RCTUumengShare/UmengSocial/UMSocial_Sdk_Extra_Frameworks/TencentOpenAPI／libSocialQQ.a TencentOpenApi_IOS_Bundle.bundle 和 TencentOpenAPI.framework

＊ 配置`info.plist`

	```
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
	<plist version="1.0">
	<dict>
	...
	<key>CFBundleURLTypes</key>
	<array>
		<dict>
			<key>CFBundleURLName</key>
			<string>weixin</string>
			<key>CFBundleURLSchemes</key>
			<array>
				<string>你的微信appId</string>
			</array>
		</dict>
		<dict>
			<key>CFBundleTypeRole</key>
			<string>Editor</string>
			<key>CFBundleURLSchemes</key>
			<array>
				<string>eqianzhuang</string>
			</array>
		</dict>
		<dict>
			<key>CFBundleTypeRole</key>
			<string>Editor</string>
			<key>CFBundleURLSchemes</key>
			<array>
				<string>tencent'你的qq APPid'</string>
			</array>
		</dict>
		<dict>
			<key>CFBundleTypeRole</key>
			<string>Editor</string>
			<key>CFBundleURLSchemes</key>
			<array>
				<string>sina.54535988fd98c5f10000726d</string>
			</array>
		</dict>
		<dict>
			<key>CFBundleTypeRole</key>
			<string>Editor</string>
			<key>CFBundleURLSchemes</key>
			<array>
				<string>QQ41D42AF9</string>
			</array>
		</dict>
		</array>
		<key>LSApplicationQueriesSchemes</key>
		<array><string>wechat</string>
			<string>weixin</string>
			<string>sinaweibohd</string>
			<string>sinaweibo</string>
			<string>sinaweibosso</string>
			<string>weibosdk</string>
			<string>weibosdk2.5</string>
			<string>mqqapi</string>
			<string>mqq</string>
			<string>mqqOpensdkSSoLogin</string>
			<string>mqqconnect</string>
			<string>mqqopensdkdataline</string>
			<string>mqqopensdkgrouptribeshare</string>
			<string>mqqopensdkfriend</string>
			<string>mqqopensdkapi</string>
			<string>mqqopensdkapiV2</string>
			<string>mqqopensdkapiV3</string>
			<string>mqzoneopensdk</string>
			<string>wtloginmqq</string>
			<string>wtloginmqq2</string>
			<string>mqqwpa</string>
			<string>mqzone</string>
			<string>mqzonev2</string>
			<string>mqzoneshare</string>
			<string>wtloginqzone</string>
			<string>mqzonewx</string>
			<string>mqzoneopensdkapiV2</string>
			<string>mqzoneopensdkapi19</string>
			<string>mqzoneopensdkapi</string>
			<string>mqzoneopensdk</string>
		</array>
	...
	</dict>
	</plist>
	
	```
# 手动添加android平台
* 在`android/setting.gradle`中添加

	```bash
	include ':rn-umeng-share'
	project(':rn-umeng-share').projectDir = new File(rootProject.projectDir, '../node_modules/rn-umeng-share/android')
	```
* 在`android/app/build.gradle`中的添加

	```bash
	android {
	...
		defaultConfig {
		...
			manifestPlaceholders = [
			...
				Umeng_KEY:你的UmengAPPID,
				qqAppId:"tencent"+qq appId,
			..
			］
		...
		｝
	...
	｝
	dependencies {
		...
		compile project(':rn-umeng-share')
		...
	}
	```
* 在`android/app/src/main/java/[...]/MainActivity.java`中
	
	* 文件顶部添加 `import com.lixiang.rn_umeng_share.*;`
	* 在`getPackages()`方法中添加 `new UmengSharePackage()`

# 示例
```
import UmengShare from 'rn-umeng-share'

...

UmengShare.setAppKey(UmengAppKey)
//ios

//url:默认分享的链接
UmengShare.setWXAppId(appId,appSecret,url)
//supportWebView:是否支持web分享
UmengShare.setQQWithAppId(appId,appSecret,url,supportWebView)

//shareData示例
const shareData={
	url:'http://www.umeng.com',
	content:'分享的文字内容',
	title:'分享的标题',
	imageSource:require('../someAssetImage.png')
	//或者
	imageSource:require('../someInternetImage.png')
}

//设置分享内容
UmengShare.setQQData(shareData)
UmengShare.setQzoneData(shareData)
UmengShare.setWechatSessionData(shareData)
UmengShare.setWechatTimelineData(shareData)

//只更改分享内容和图片,并弹出分享页面
UmengShare.presentSnsIconSheetView(content,require('../someAssetImage.png'))
UmengShare.presentSnsIconSheetView(content,{uri:'http://....someInternetImage.png'})
...


//android

UmengShare.setWXAppId(appId,appSecret)
UmengShare.setQQZone(appId,appSecret)

//弹出分享页面
UmengShare.openShareAction(content,title,url,require('../someAssetImage.png'))
UmengShare.openShareAction(content,title,url,{uri:'http://....someInternetImage.png'})

```
	

 




