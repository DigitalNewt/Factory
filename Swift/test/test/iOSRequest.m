//
//  iOSRequest.m
//  Ravetree
//
//  Created by Brent Baker on 11/21/13.
//  Copyright (c) 2013 Ravetree. All rights reserved.
//

#import "iOSRequest.h"

@implementation iOSRequest
NSString *const rootURL = @"http://www.ravetree.com";
//NSString *const rootURL = @"http:107.170.24.58:9000";
//NSString *const rootURL = @"http:127.0.0.1:9000";
NSString *const kAuthorizationAction = @"/security/auth";
NSString *const kRegistrationAction = @"/security/register";
NSString *const kLogoutAction = @"/security/logout";

NSString *const kEmailAddressExist = @"/api/v1/utilities/email-address";


+ (NSString *)toJSON:(NSDictionary *)dictionary
{
    //NSDictionary *myDictionary = [NSDictionary dictionaryWithObject:@&quot;Hello&quot; forKey:@&quot;World&quot;];
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

+ (void)getRequestRestService:(NSDictionary *)userData withAction:(NSString *)action onCompletion:(RequestCompletionHandler)complete
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
    
    NSOperationQueue *backgroundQueue = [[NSOperationQueue alloc] init];
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@%@",rootURL, action, [self toURL:userData]]]
                                                                cachePolicy:NSURLRequestReloadIgnoringLocalAndRemoteCacheData
                                                            timeoutInterval:10];
    
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


+ (void)requestRestService:(NSDictionary *)userCredentials withAction:(NSString *)action onCompletion:(RequestCompletionHandler)complete
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
    
    NSOperationQueue *backgroundQueue = [[NSOperationQueue alloc] init];
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@",rootURL, action]]
                                                                cachePolicy:NSURLRequestReloadIgnoringLocalAndRemoteCacheData
                                                            timeoutInterval:10];

    request.HTTPMethod = @"POST";
    NSString *postString = [self toJSON:userCredentials];
    [request addValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    request.HTTPBody = [postString dataUsingEncoding:NSUTF8StringEncoding];
    

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

@end
