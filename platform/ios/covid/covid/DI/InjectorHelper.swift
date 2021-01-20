//
//  InjectorHelper.swift
//  covid
//
//  Created by michael on 17.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import Foundation
import KotlinShared

class InjectorHelper {
    static func initialize() {
        Injector().setup()
    }
}
