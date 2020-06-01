//
//  ViewController.swift
//  covid
//
//  Created by michael on 17.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import UIKit
import KotlinShared

class SplashViewController: UIViewController {
    
    //private let splashView = SplashView()
    private var viewModel: SplashViewModel? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let component = createComponent()
        self.viewModel = component.viewModel
        
        observeViewModel()
        viewModel?.onCreate()
    }
    
    override func loadView() {
        super.loadView()
       // self.view = splashView
    }
    
    private func createComponent() -> SplashComponent {
        return SplashComponent(
            parentKodein: Injector.shared.kodein
        )
    }
    
    deinit {
        print("deinit \(self)")
        self.viewModel?.clear()
    }
}

extension SplashViewController {
    private func observeViewModel() {
        self.viewModel?.startAnimationLiveData.observe {
            let duration = Double(truncating: $0) / 1_000
            
            UIView.animate(withDuration: duration, animations: {
               // self.splashView.splashLabel.alpha = 1.0
            },completion: { (finished: Bool) in
                //self.viewModel?.onAnimationEnd()
            })
        }
    }
}
