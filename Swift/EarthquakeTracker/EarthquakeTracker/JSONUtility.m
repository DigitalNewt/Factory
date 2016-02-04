//
//  JSONUtility.m
//  Ravetree
//
//  Created by Brent Baker on 12/17/13.
//  Copyright (c) 2013 Ravetree. All rights reserved.
//

#import "JSONUtility.h"

@implementation JSONUtility

+ (NSDictionary *)getDictionaryFromJSON:(NSString *) jsonString
{
    
    NSData *jsonData = [jsonString dataUsingEncoding:NSUTF8StringEncoding];
    
    NSError *error = nil;
    NSDictionary *dictionary = [NSJSONSerialization JSONObjectWithData:jsonData options:NSJSONReadingMutableContainers error:&error];
    
    if(!dictionary) {
        NSLog(@"%@",error);
    }
    return dictionary;
}

@end
