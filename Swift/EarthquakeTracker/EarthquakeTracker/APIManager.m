//
//  APIManager.m
//  EarthquakeTracker
//
//  Created by Brent Baker on 2/3/16.
//  Copyright © 2016 HBB Global. All rights reserved.
//

#import "APIManager.h"

@implementation APIManager
NSString *const rootURL = @"http://ehp2-earthquake.wr.usgs.gov/fdsnws/event/1/";

NSString *const kQuery = @"query";


/*
 * Convert NSDictionary to JSON string
 * @return JSON in NSString
 */
+ (NSString *)toJSON:(NSDictionary *)dictionary
{
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dictionary
                                                       options:0
                                                         error:&error];
    NSString *JSONString = nil;
    
    if (!jsonData) {
        NSLog(@"JSON error: %@", error);
    } else {
        
        JSONString = [[NSString alloc] initWithBytes:[jsonData bytes] length:[jsonData length] encoding:NSUTF8StringEncoding];
        NSLog(@"JSON OUTPUT: %@",JSONString);
    }
    
    return JSONString;
}

+ (NSString *)toURL:(NSDictionary *)dictionary
{
    NSMutableString *urlData = [NSMutableString stringWithString:@""];
    if (dictionary != nil && dictionary.count > 0) {
        BOOL firstParameter = true;
        [urlData appendString:@"?"];
        for(id key in dictionary) {
            if (firstParameter) {
                [urlData appendString:[NSString stringWithFormat:@"%@=%@",key, [dictionary objectForKey:key]]];
                firstParameter = false;
            } else {
                [urlData appendString:[NSString stringWithFormat:@"&%@=%@",key, [dictionary objectForKey:key]]];
            }
            
        }
    }
    return urlData;
}


+ (NSDictionary *)constructQuery:(NSString *)limit withOrder: (NSString *) orderby {
    NSString *format = @"geojson";
    NSString *eventtype = @"earthquake";
    
    NSDictionary *queryData = @{@"limit":limit,
                                @"eventtype":eventtype,
                                @"orderby":orderby,
                                @"format":format};

    return queryData;
}


/*
 * HTTP Get Request
 * @returns Dictionary with results.
*/
+ (void)requestHTTPGet:(NSDictionary *)queryData withAction:(NSString *)action onCompletion:(RequestCompletionHandler)complete
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
    
    NSOperationQueue *backgroundQueue = [[NSOperationQueue alloc] init];
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@%@",rootURL, action, [self toURL:queryData]]]
                                                                cachePolicy:NSURLRequestReloadIgnoringLocalAndRemoteCacheData
                                                            timeoutInterval:50];
    
    request.HTTPMethod = @"GET";
    [request addValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    
    [NSURLConnection sendAsynchronousRequest:request
                                       queue:backgroundQueue
                           completionHandler:^(NSURLResponse *response, NSData *data, NSError *connectionError) {
                               NSString *result = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
                               if (complete) {
                                   [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
                                   complete(result, connectionError);
                               }
                           }];
}
//
///*
// * HTTP Get Request
// * @returns Dictionary with results.
// */
//+ (void)sendHTTPGet:(NSDictionary *)queryData withAction:(NSString *)action onCompletion:(RequestCompletionHandler)complete
//{
//    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
//    
//    NSURLSessionConfiguration *defaultConfigObject = [NSURLSessionConfiguration defaultSessionConfiguration];
//    NSURLSession *defaultSession = [NSURLSession sessionWithConfiguration:defaultConfigObject
//                                                                 delegate:self
//                                                            delegateQueue:[NSOperationQueue mainQueue]];
//    NSURL * url = [NSURL URLWithString:[NSString stringWithFormat:@"%@%@%@",rootURL, action, [self toURL:queryData]]];
//    
//    NSURLSessionDataTask * dataTask = [defaultSession dataTaskWithURL:url
//                                                    completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
//        if(error == nil)
//        {
//            NSString * text = [[NSString alloc] initWithData: data encoding: NSUTF8StringEncoding];
//            NSLog(@"Data = %@",text);
//        }
//        
//    }];
//    
//    [dataTask resume];
//    
//}

@end
