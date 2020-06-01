//
//  HomeNavigatorImpl.swift
//  covid
//
//  Created by michael on 24.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import UIKit
import KotlinShared

class HomeNavigatorImpl: HomeNavigator {
    func goToHomeScreen() {
        let sceneDelegate = UIApplication.shared.connectedScenes.first!.delegate as! SceneDelegate
        sceneDelegate.openAsRoot(contentView: ContentView())
        
    }
}
