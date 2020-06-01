//
//  SceneDelegate.swift
//  test
//
//  Created by michael on 27.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import UIKit
import SwiftUI

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    
    var window: UIWindow?
    var rootVC: UIHostingController<AnyView>!
    
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        let contentView = SplashView()
        let appState = AppState()
        
        if let windowScene = scene as? UIWindowScene {
            let window = UIWindow(windowScene: windowScene)
            self.rootVC = UIHostingController(rootView: AnyView(contentView.environmentObject(appState)))
            window.rootViewController = self.rootVC
            self.window = window
            window.makeKeyAndVisible()
        }
    }
    
    func sceneDidDisconnect(_ scene: UIScene) {
        // Called as the scene is being released by the system.
        // This occurs shortly after the scene enters the background, or when its session is discarded.
        // Release any resources associated with this scene that can be re-created the next time the scene connects.
        // The scene may re-connect later, as its session was not neccessarily discarded (see `application:didDiscardSceneSessions` instead).
    }
    
    func sceneDidBecomeActive(_ scene: UIScene) {
        // Called when the scene has moved from an inactive state to an active state.
        // Use this method to restart any tasks that were paused (or not yet started) when the scene was inactive.
    }
    
    func sceneWillResignActive(_ scene: UIScene) {
        // Called when the scene will move from an active state to an inactive state.
        // This may occur due to temporary interruptions (ex. an incoming phone call).
    }
    
    func sceneWillEnterForeground(_ scene: UIScene) {
        // Called as the scene transitions from the background to the foreground.
        // Use this method to undo the changes made on entering the background.
    }
    
    func sceneDidEnterBackground(_ scene: UIScene) {
        // Called as the scene transitions from the foreground to the background.
        // Use this method to save data, release shared resources, and store enough scene-specific state information
        // to restore the scene back to its current state.
    }
}

extension SceneDelegate {
    func openAsRoot<Content>(contentView: Content) where Content : View {
        let appState = AppState()
        rootVC.rootView = AnyView(contentView.environmentObject(appState))
        //window?.rootViewController = UIHostingController(rootView: AnyView(SplashView()))
    }
}

struct DetailView: View {
    @EnvironmentObject var appState: AppState
    
    var body: some View {
        Group {
            Text("Detail view content goes here")
            
            Button(action: {
                self.appState.moveToDashboard = true
            }) {
                Text("Move to Dashboard")
                .font(.headline)
            }
        }
    }
    
    
}
