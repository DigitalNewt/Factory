//
//  DateUtility.m
//  Ravetree
//
//  Created by Brent Baker on 1/7/14.
//  Copyright (c) 2014 Ravetree. All rights reserved.
//

#import "DateUtility.h"

@interface DateUtility ()
@property (nonatomic, strong) NSCalendar *calendar;
@end

@implementation DateUtility

- (NSCalendar *)calendar
{
    if (!_calendar) {
        _calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSCalendarIdentifierGregorian];
    }
    return _calendar;
}

- (NSDate *)getDate18YearsFromNow
{
    NSDate *newDate = nil;
    @try {
        NSDateComponents *dateComponents = [[NSDateComponents alloc] init];
        dateComponents.year = -18;
        
        newDate = [self.calendar dateByAddingComponents:dateComponents toDate:[NSDate date] options:0];
        
    } @catch (NSException *exception) {
        NSLog(@"Exception : %@", exception);
    }
    return newDate;
}


@end
