//
//  BaseVMHolder.swift
//  covid
//
//  Created by michael on 28.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import SwiftUI
import KotlinShared

class BaseVMHolder<VM: BaseViewModel, C: IosBaseComponent<VM>> {
    internal var viewModel: VM? = nil
    
    init() {
        let component = self.createComponent()
        self.viewModel = component.viewModel
        self.observeViewModel()
    }
    
    func onAppear() {
        self.viewModel?.onCreate()
    }
    
    func onDisappear() {
        self.viewModel?.clear()
    }
    
    func observeViewModel() {
        preconditionFailure("This method must be overridden")
    }
    
    func createComponent() -> C {
        preconditionFailure("This method must be overridden")
    }
    
    deinit {
        print("deinit \(self)")
        self.viewModel?.clear()
    }
}
