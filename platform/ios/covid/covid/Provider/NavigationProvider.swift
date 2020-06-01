//
//  NavigationProvider.swift
//  covid
//
//  Created by michael on 24.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import Foundation
import KotlinShared

class NavigationProviderImpl : NavigationProvider {
    func createHomeNavigator() -> HomeNavigator {
        return HomeNavigatorImpl()
    }
    
}
