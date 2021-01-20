//
//  SummaryListViewController.swift
//  covid
//
//  Created by michael on 25.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

//import UIKit
//import KotlinShared
//
//class SummaryListViewController: UIViewController {
//    
//    private let summaryListView = SummaryListView()
//    private var viewModel: SummaryListViewModel? = nil
//    
//    override func viewDidLoad() {
//        super.viewDidLoad()
//        
//        let component = createComponent()
//        self.viewModel = component.viewModel
//        
//        observeViewModel()
//        viewModel?.onCreate()
//    }
//    
//    override func loadView() {
//        super.loadView()
//        self.view = summaryListView
//    }
//    
//    private func createComponent() -> SummaryListComponent {
//        return SummaryListComponent(
//            //parentDI: Injector.shared.di
//        )
//    }
//    
//    deinit {
//        print("deinit \(self)")
//        self.viewModel?.clear()
//    }
//}
//
//extension SummaryListViewController {
//    private func observeViewModel() {
//        
//    }
//}
