//
//  SearchViewController.m
//  Tab'd
//
//  Created by Mark Evans on 7/3/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "SearchViewController.h"

@interface SearchViewController ()

@end

@implementation SearchViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        self.title = @"Search";
        UIImage *headerImage = [UIImage imageNamed:@"logo_titleView.png"];
        self.navigationItem.titleView = [[UIImageView alloc] initWithImage:headerImage];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.navigationController.navigationBar.tintColor=[UIColor blackColor];
    UIImage *listImg = [UIImage imageNamed:@"list.png"];
    UIBarButtonItem *rightButton =[[UIBarButtonItem alloc]initWithImage:listImg style:UIBarButtonItemStylePlain target:self action:@selector(toggleList)];  
    self.navigationItem.rightBarButtonItem = rightButton;
    // Do any additional setup after loading the view from its nib.
}

- (IBAction)onClose:(id)sender
{
    [self dismissModalViewControllerAnimated:TRUE];
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar
{
    [theSearchBar resignFirstResponder];
    // Do the search...
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

@end
