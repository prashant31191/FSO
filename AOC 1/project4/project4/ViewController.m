//
//  ViewController.m
//
//  Created by Mark Evans on 4/23/12.
//  Copyright (c) 2012 MdTA / Full Sail University. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    UILabel *textLabel = [[UILabel alloc] initWithFrame:CGRectMake(10.0f, 10.0f, 100.0f, 30.0f)];
    if (textLabel != nil)
    {
        textLabel.text = @"Username: ";
        [self.view addSubview:textLabel];
    }
    textField = [[UITextField alloc] initWithFrame:CGRectMake(100.0f, 10.0f, 200.0f, 30.0f)];
    if (textField != nil)
    {
        textField.borderStyle = UITextBorderStyleRoundedRect;
        [self.view addSubview:textField];
    }
    
    UIButton *button = [UIButton buttonWithType:UIButtonTypeRoundedRect];
    if (button != nil)
    {
        button.frame = CGRectMake(225.0f, 50.0f, 75.0f, 30.0f);
        [button setTitle:@"Login" forState:UIControlStateNormal];
        [button addTarget:self action:@selector(onClick) forControlEvents: UIControlEventTouchUpInside];
        [self.view addSubview:button];
    }
    textStatusLabel = [[UILabel alloc] initWithFrame:CGRectMake(0.0f, 100.0f, 320.0f, 80.0f)];
    if (textStatusLabel != nil)
    {
        textStatusLabel.text = @"Please Enter Username ";
        textStatusLabel.backgroundColor = [UIColor lightGrayColor];
        textStatusLabel.textColor = [UIColor blueColor];
        textStatusLabel.textAlignment = UITextAlignmentCenter;        
        [self.view addSubview:textStatusLabel];
    }
    
    UIButton *showDateButton = [UIButton buttonWithType:UIButtonTypeRoundedRect];
    if (showDateButton != nil)
    {
        showDateButton.frame = CGRectMake(10.0f, 250.0f, 125.0f, 50.0f);
        [showDateButton setTitle:@"Show Date" forState:UIControlStateNormal];
        [showDateButton addTarget:self action:@selector(showDate) forControlEvents: UIControlEventTouchUpInside];
        [self.view addSubview:showDateButton];
    }
    
    UIButton *infoButton = [UIButton buttonWithType:UIButtonTypeInfoDark];
    if (infoButton != nil)
    {
        infoButton.frame = CGRectMake(10.0f, 315.0f, 25.0f, 25.0f);
        [infoButton addTarget:self action:@selector(showInfo) forControlEvents: UIControlEventTouchUpInside];
        [self.view addSubview:infoButton];
    }
    infoLabel = [[UILabel alloc] initWithFrame:CGRectMake(0.0f, 340.0f, 320.0f, 100.0f)];
    if (infoLabel != nil)
    {
        
        infoLabel.backgroundColor = [UIColor whiteColor];
        infoLabel.textColor = [UIColor greenColor];
        infoLabel.textAlignment = UITextAlignmentCenter;
        infoLabel.numberOfLines = 7;
        infoLabel.lineBreakMode = UILineBreakModeWordWrap;
        [self.view addSubview:infoLabel];
    }
    //UITextField *userNameField = [UITextField textFieldAtIndex:1];
    
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

// OnClick Function for login
-(void)onClick
{
    NSString *userNameText = [textField text];
    int stringLength = [userNameText length];
    if (stringLength == 0)
    {
        NSString *labelStr = @"Username cannot be empty.";
        [textStatusLabel setText:(NSString *)labelStr];
    } else {
        NSString *labelStr = [NSString stringWithFormat:@"User: %@ has been logged in.", userNameText];
        [textStatusLabel setText:(NSString *)labelStr];
    }  
    return;
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}

@end