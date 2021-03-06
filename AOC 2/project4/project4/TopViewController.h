//
//  TopViewController.h
//  project4
//
//  Created by Mark Evans on 5/14/12.
//  Copyright (c) 2012 MdTA / Full Sail University. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ViewController.h"

@interface TopViewController : UIViewController
{
    IBOutlet UITextField *textField;
    IBOutlet UILabel *topSwipeLabel;
    UISwipeGestureRecognizer *topLeftSwiper;
    UISwipeGestureRecognizer *topRightSwiper;
}

@property (retain, nonatomic) IBOutlet UILabel *leftSwiper;
@property (retain, nonatomic) IBOutlet UIButton *closeKeyBoardButton;
//@property (retain, nonatomic) IBOutlet UIButton *saveDataButton;
@property (retain, nonatomic) IBOutlet UIDatePicker *myDatePicker;
@property (retain, nonatomic) IBOutlet UITextField *myTextField;
@property (strong) id<testDelegate> delegate;

// Not needed -(IBAction)DidSave:(id)sender;
- (IBAction)dismissKeyboard:(id)sender;

@end