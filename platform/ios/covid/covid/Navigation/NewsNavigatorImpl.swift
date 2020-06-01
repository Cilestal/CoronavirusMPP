//
//  NewsNavigatorImpl.swift
//  covid
//
//  Created by michael on 24.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import Foundation
import KotlinShared

class NewsNavigatorImpl: NewsNavigator {
    weak var delegate: NewsNavigatorDelegate? = nil
    
    init(delegate: NewsNavigatorDelegate) {
        self.delegate = delegate
    }
    
    func goToNewsScreen() {
        delegate?.openNewsTab()
    }
}

protocol NewsNavigatorDelegate : AnyObject {
    func openNewsTab()
}
