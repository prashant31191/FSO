//
//  ViewController.h
//  project4
//
//  Created by Mark Evans on 5/14/12.
//  Copyright (c) 2012 MdTA / Full Sail University. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol testDelegate <NSObject>

-(void)DidClose:(NSString*)newString;

@end

@interface ViewController : UIViewController <testDelegate>
{
    IBOutlet UITextView *textView;
    NSMutableString *outputText;
    IBOutlet UILabel *swipeLabel;
    UISwipeGestureRecognizer *leftSwipe;
    UISwipeGestureRecognizer *rightSwipe;
}

-(IBAction)onClick:(id)sender;

@end