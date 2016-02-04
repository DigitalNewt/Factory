//
//  EarthquakeDataTVC.h
//  EarthquakeTracker
//
//  Created by Brent Baker on 2/3/16.
//  Copyright © 2016 HBB Global. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface EarthquakeDataTVC : UITableViewController
@property (nonatomic, strong) NSArray *earthquakes;  //Latest list of Earthquake data in NSDictionaries

@end
