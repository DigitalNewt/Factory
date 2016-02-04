//
//  iOSRequest.h
//  Ravetree
//
//  Created by Brent Baker on 11/21/13.
//  Copyright (c) 2013 Ravetree. All rights reserved.
//

#import <Foundation/Foundation.h>

extern NSString *const kAuthorizationAction;
extern NSString *const kRegistrationAction;
extern NSString *const kLogoutAction;
extern NSString *const kEmailAddressExist;

typedef void(^RequestCompletionHandler) (NSString*, NSError*);

@interface iOSRequest : NSObject

@property (nonatomic, strong)NSManagedObjectContext *context;

+ (void)getRequestRestService:(NSDictionary *)userData withAction:(NSString *)action onCompletion:(RequestCompletionHandler)complete;
+ (void)requestRestService:(NSDictionary *)userCredentials withAction:(NSString *)action onCompletion:(RequestCompletionHandler)complete;

@end
