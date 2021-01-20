//
//  ContentView.swift
//  covid
//
//  Created by michael on 29.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import SwiftUI
import Combine

class ContentViewState: ObservableObject {
    @Published var moveToDashboard: Bool = false
}

struct HomeView: View {
    @EnvironmentObject var state: ContentViewState
    @State var isView1Active: Bool = false
    @State var selectedTab: Int = 0
    
    var body: some View {
        NavigationView {
            TabView(selection: $selectedTab) {
                SummaryListView()
                    .tabItem {
                        Image(systemName: "phone.fill")
                        Text("First Tab")
                }
                .onTapGesture {
                    print("dsdsds");
                    self.selectedTab = 1
                } 
                .tag(0)
                
                Text("Tab 1")
                    .onTapGesture {
                        self.selectedTab = 0
                }
                .tabItem {
                    Image(systemName: "phone.fill")
                    Text("second tab")
                }
                    
                .tag(1)
                
                Text("Tab 1")
                    .onTapGesture {
                        self.selectedTab = 0
                }
                .tabItem {
                    Image(systemName: "star")
                    Text("One")
                }
                .tag(2)
            }
        }
    }
}


#if DEBUG
struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
#endif
