//
//  SummaryNavigatorImpl.swift
//  covid
//
//  Created by michael on 24.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import UIKit
import KotlinShared

class SummaryNavigatorImpl: SummaryNavigator {
    weak var delegate: SummaryNavigatorDelegate? = nil
    
    init(delegate: SummaryNavigatorDelegate) {
        self.delegate = delegate
    }
    
    func goToSummaryScreen() {
        delegate?.openSummaryTab()
    }
}

protocol SummaryNavigatorDelegate : AnyObject {
    func openSummaryTab()
}
