//
//  EarthquakeTrackerTests.m
//  EarthquakeTrackerTests
//
//  Created by Brent Baker on 2/3/16.
//  Copyright © 2016 HBB Global. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "APIManager.h"
#import "JSONUtility.h"

@interface EarthquakeTrackerTests : XCTestCase
@property (nonatomic,strong) NSManagedObjectContext* managedObjectContext;

@end

@implementation EarthquakeTrackerTests

- (void)setUp {
    [super setUp];
    // Put setup code here. This method is called before the invocation of each test method in the class.
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void)testPerformanceExample {
    // This is an example of a performance test case.
    [self measureBlock:^{
        // Put the code you want to measure the time of here.
    }];
}

- (void)testReturn20ResultsFromEarthquakeAPI
{
    NSString *limit = @"20";
    NSString *orderby = @"time";
    
    
    // Set the flag to YES
    __block BOOL waitingForBlock = YES;
    
    __block NSDictionary *results;
    

    NSDictionary *queryData = [APIManager constructQuery:limit withOrder: orderby];
    
    [APIManager requestHTTPGet:queryData withAction:kQuery onCompletion: ^(NSString *result, NSError *error){
        dispatch_async(dispatch_get_main_queue(), ^{
            waitingForBlock = NO;
            if (error) {
                results = [[NSDictionary alloc] initWithObjectsAndKeys:@"Failed to query earthquake data", @"errorMessage", nil];
                NSLog(@"%@", error);
            } else {
                results = [JSONUtility getDictionaryFromJSON:result];
            }
        });
    }];
    
    while (waitingForBlock) {
        [[NSRunLoop currentRunLoop] runMode:NSDefaultRunLoopMode
                                 beforeDate:[NSDate dateWithTimeIntervalSinceNow:0.1]];
        
    }
    
    NSArray *information = [results objectForKey:@"features"];
    
    XCTAssertTrue(information.count > 0);
}

@end
