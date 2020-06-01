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
    @ObservedObject var vmWrapper: SplashVMHolder = SplashVMHolder()
    @State var titleOpacity = 0.0
    
    var body: some View {
        Group {
            Text("Covid")
                .opacity(titleOpacity)
                .onReceive(self.vmWrapper.animationDurationSubject) { duration in
                    //                    withAnimation(.easeIn(duration: duration)) {
                    //                        self.titleOpacity = 1
                    //                        self.vmWrapper.viewModel?.onAnimationEnd()
                    //                    }
                    
                    UIView.animate(withDuration: 5000, animations: {
                        self.titleOpacity = 1
                    },completion: { (finished: Bool) in
                        self.vmWrapper.viewModel?.onAnimationEnd()
                    })
            }
        }.onAppear {
            self.vmWrapper.onAppear()
        }
    }
}

class SplashVMHolder: BaseVMHolder<SplashViewModel, SplashComponent>, ObservableObject {
    let animationDurationSubject = PassthroughSubject<Double, Never>()
    
    override func observeViewModel() {
        self.viewModel?.startAnimationLiveData.observe {
            self.animationDurationSubject.send(Double(truncating: $0) / 1_000)
        }
    }
    
    override func createComponent() -> SplashComponent {
        return SplashComponent(
            parentKodein: Injector.shared.kodein
        )
    }
}

#if DEBUG
struct SplashView_Previews: PreviewProvider {
    static var previews: some View {
        SplashView()
    }
}
#endif
