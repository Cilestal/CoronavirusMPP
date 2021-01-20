//
//  ContentView.swift
//  test
//
//  Created by michael on 27.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import SwiftUI
import KotlinShared
import Combine

struct SplashView: View {
    
    @EnvironmentObject var appState: AppState
    @ObservedObject var vmWrapper: SplashVMHolder = SplashVMHolder()
    @State private var titleOpacity = 0.0
    @State private var showHomeScreen = true
    
    var body: some View {
        ZStack {
            Text("Covid")
                .opacity(titleOpacity)
                .onAnimationCompleted(for: titleOpacity) {
                    self.vmWrapper.viewModel?.onAnimationEnd()
            }
            .onReceive(self.vmWrapper.animationDurationSubject) { duration in
                withAnimation(.linear(duration: duration)){
                    self.titleOpacity = 1
                }
            }
        }.onAppear {
            self.vmWrapper.onAppear()
        }.onDisappear() {
            self.vmWrapper.onDisappear()
        }.onReceive(self.vmWrapper.navigateToHomeScreenSubject) { (show) in
            self.appState.rootView = RootViewEnum.home
        }
        
    }
}

class SplashVMHolder: BaseVMHolder<SplashViewModel, SplashComponent>, ObservableObject, HomeNavigator {
    let animationDurationSubject = PassthroughSubject<Double, Never>()
    let navigateToHomeScreenSubject = PassthroughSubject<Bool, Never>()
    
    func goToHomeScreen() {
        self.navigateToHomeScreenSubject.send(true)
    }
    
    override func observeViewModel() {
        self.viewModel?.startAnimationLiveData.observe {
            self.animationDurationSubject.send(Double(truncating: $0) / 1_000)
        }
    }
    
    override func createComponent() -> SplashComponent {
        return SplashComponent(homeNavigator: self)
    }
    
    
}

#if DEBUG
struct SplashView_Previews: PreviewProvider {
    static var previews: some View {
        SplashView()
    }
}
#endif
