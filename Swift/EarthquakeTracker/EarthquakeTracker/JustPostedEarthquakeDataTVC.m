//
//  JustPostedEarthquakeDataTVC.m
//  EarthquakeTracker
//
//  Created by Brent Baker on 2/3/16.
//  Copyright © 2016 HBB Global. All rights reserved.
//

#import "JustPostedEarthquakeDataTVC.h"
#import "APIManager.h"
#import "JSONUtility.h"

@interface JustPostedEarthquakeDataTVC ()

@end

@implementation JustPostedEarthquakeDataTVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [self fetchEarthquakeData];
}

/** Call API to retrieve Earthquake data
 @returns void
 */
- (void)fetchEarthquakeData {
    
    //TODO: add code to fetch earthquake data
    NSString *limit = @"20";
    NSString *orderby = @"time";
    
    
//    // Set the flag to YES
//    __block BOOL waitingForBlock = YES;
    
    NSDictionary *queryData = [APIManager constructQuery:limit withOrder: orderby];
    
    [APIManager requestHTTPGet:queryData withAction:kQuery onCompletion: ^(NSString *result, NSError *error){
        dispatch_async(dispatch_get_main_queue(), ^{
//            waitingForBlock = NO;
            if (error) {
                [self stopFetchingEarthquakeData:@"Failed to query earthquake data"];
                NSLog(@"%@", error);
            } else {
                [self stopFetchingEarthquakeData:result];
            }
        });
    }];
//#warning Block Main Que
//    while (waitingForBlock) {
//        [[NSRunLoop currentRunLoop] runMode:NSDefaultRunLoopMode
//                                 beforeDate:[NSDate dateWithTimeIntervalSinceNow:0.1]];
//        
//    }
//    
//    self.earthquakes = [results objectForKey:@"features"];
//    
//    NSLog(@"Earthquake results = %@", self.earthquakes);
}

-(void)stopFetchingEarthquakeData:(NSString *) result {
    NSDictionary *results = [JSONUtility getDictionaryFromJSON:result];
    self.earthquakes = [results objectForKey:@"features"];
    [self.tableView reloadData];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
