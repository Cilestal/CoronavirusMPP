//
//  ViewController.swift
//  covid
//
//  Created by michael on 17.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import UIKit
import KotlinShared

class ViewController: UIViewController {
    
    @IBOutlet weak var titleLabel: UILabel!
    private var componennt: SplashComponent!
    private var viewModel: SplashContractViewModel? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.componennt = createComponent()
        self.viewModel = componennt.viewModel
        
        observeViewModel()
        viewModel?.start()
    }
    
    
    private func test() {
         self.navigationController?.popViewController(animated: false)
        self.dismiss(animated: true) {
                print("TEST: open")
            
            let signInStoryboard = UIStoryboard(name: "Main", bundle: nil)
                   if let appDelegate = UIApplication.shared.delegate as? AppDelegate {
                    let vc = signInStoryboard.instantiateViewController(withIdentifier: "ViewController2")
                       appDelegate.openAsRoot(viewController: vc)
               }
        }
       
    
       
    }
    
    private func observeViewModel() {
        self.viewModel?.startAnimationLiveData.observe {
            let duration = Double(truncating: $0) / 1_000
        
            UIView.animate(withDuration: duration, animations: {
                self.titleLabel.alpha = 1.0
            })
            
            self.test()
        }
    }
    
    deinit {
        print("TEST: DEINIT")
        self.viewModel?.startAnimationLiveData.removeObservers()
    }
    
    private func createComponent() -> SplashComponent {
        return SplashComponent(
            parentKodein: Injector.shared.kodein
        )
    }
}
