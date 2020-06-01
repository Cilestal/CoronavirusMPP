//
//  Injector.swift
//  covid
//
//  Created by michael on 17.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import Foundation
import KotlinShared

class Injector {
    static let shared = Injector()
    let kodein: Kodein_di_coreKodein
    
    private init(){
        self.kodein = IosKodein(
            dateTimeProvider: DateTimeProviderImpl(),
            navigationProvider: NavigationProviderImpl()
        ).kodein
    }
    
    // provider
    private func createDateTimeProvider() -> DateTimeProvider {
        return DateTimeProviderImpl()
    }
}
