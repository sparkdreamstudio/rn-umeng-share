//
//  RCTUmengShare.h
//  RCTUmengShare
//
//  Created by 李响 on 16/5/13.
//  Copyright © 2016年 lixiang. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RCTBridgeModule.h"
#import <UIKit/UIKit.h>


@interface RCTUmengShare : NSObject <RCTBridgeModule>
+(void) setRootController:(UIViewController*)rootController;
@end
