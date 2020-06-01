//
//  ContentView.swift
//  covid
//
//  Created by michael on 29.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import SwiftUI
import Combine
class AppState: ObservableObject {
    @Published var moveToDashboard: Bool = false
}

struct ContentView: View {
    @EnvironmentObject var appState: AppState
    @State var isView1Active: Bool = false
    
    var body: some View {
        NavigationView {
            NavigationLink(destination: DetailView(), isActive: $isView1Active) {
                Text("View 1")
                    .font(.headline)
                }.navigationBarHidden(true)
            .onReceive(self.appState.$moveToDashboard) { moveToDashboard in
                if moveToDashboard {
                    print("Move to dashboard: \(moveToDashboard)")
                    self.isView1Active = false
                    self.appState.moveToDashboard = false
                }
            }
        }
    }
}
