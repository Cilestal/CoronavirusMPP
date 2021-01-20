//
//  AppRootView.swift
//  covid
//
//  Created by michael on 18.01.2021.
//  Copyright © 2021 michael. All rights reserved.
//

import SwiftUI
import Foundation

class AppState: ObservableObject {
    @Published var rootView: RootViewEnum = .splash
}

enum RootViewEnum {
    case splash
    case home
}

struct AppRootView: View {
    
    @EnvironmentObject private var appState: AppState
    
    var body: some View {
        Group {
            if(appState.rootView == RootViewEnum.splash) {
                SplashView()
            } else if(appState.rootView == RootViewEnum.home) {
                HomeView().environmentObject(ContentViewState())
            }
        }
    }
}
