//
//  HomeViewController.swift
//  covid
//
//  Created by michael on 24.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import UIKit
import KotlinShared

class HomeViewController: UITabBarController {
    
    private var viewModel: HomeViewModel? = nil
    
    private var summaryVC: SummaryListViewController!
    private var newsVC: SummaryListViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViewControllers()
        let component = createComponent()
        
        viewModel = component.viewModel
        observeViewModel()
        viewModel?.onCreate()
    }
    
    private func setupViewControllers() {
        self.summaryVC = SummaryListViewController()
        self.newsVC = SummaryListViewController()
        self.newsVC.view.backgroundColor = .blue
        
        self.summaryVC.tabBarItem = UITabBarItem(tabBarSystemItem: .search, tag: 0)
        self.newsVC.tabBarItem = UITabBarItem(tabBarSystemItem: .search, tag: 1)
        
        self.viewControllers = [summaryVC, newsVC]
        selectedViewController = summaryVC
        
        self.delegate = self
    }
    
    private func observeViewModel() {
        
    }
    
    deinit {
        print("deinit \(self)")
        self.viewModel?.clear()
    }
    
    private func createComponent() -> HomeComponent {
        return HomeComponent(
            parentKodein: Injector.shared.kodein,
            newsNavigator: NewsNavigatorImpl(delegate: self),
            summaryNavigator: SummaryNavigatorImpl(delegate: self)
            
        )
    }
}

extension HomeViewController : UITabBarControllerDelegate {
    func tabBarController(_ tabBarController: UITabBarController, shouldSelect viewController: UIViewController) -> Bool {
        if(viewController == summaryVC) {
            viewModel?.onSummaryTabClicked()
        } else if (viewController == newsVC) {
            viewModel?.onNewsTabClicked()
        }
        //        if(viewController is SummaryListViewController) {
        //            viewModel?.onSummaryTabClicked()
        //        } else if (viewController is newsVC) {
        //            viewModel?.onNewsTabClicked()
        //        }
        return false
    }
}

extension HomeViewController: SummaryNavigatorDelegate {
    func openSummaryTab() {
        self.selectedViewController = summaryVC
    }
}

extension HomeViewController: NewsNavigatorDelegate {
    func openNewsTab() {
        self.selectedViewController = newsVC
    }
}
