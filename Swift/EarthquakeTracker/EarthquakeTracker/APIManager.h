//
//  APIManager.h
//  EarthquakeTracker
//
//  Created by Brent Baker on 2/3/16.
//  Copyright © 2016 HBB Global. All rights reserved.
//

@import UIKit;

extern NSString *const kQuery;

typedef void(^RequestCompletionHandler) (NSString*, NSError*);

@interface APIManager : NSObject

+ (NSDictionary *)constructQuery:(NSString *)limit withOrder: (NSString *) orderby;

+ (void)requestHTTPGet:(NSDictionary *)userData withAction:(NSString *)action onCompletion:(RequestCompletionHandler)complete;

//+ (void)sendHTTPGet:(NSDictionary *)userData withAction:(NSString *)action onCompletion:(RequestCompletionHandler)complete;

@end
