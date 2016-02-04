//
//  JSONUtility.h
//  Ravetree
//
//  Created by Brent Baker on 12/17/13.
//  Copyright (c) 2013 Ravetree. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface JSONUtility : NSObject
+ (NSDictionary *)getDictionaryFromJSON:(NSString *) jsonString;

@end
