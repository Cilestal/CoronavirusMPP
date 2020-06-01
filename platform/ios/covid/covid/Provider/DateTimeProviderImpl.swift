//
//  DateTimeProvider.swift
//  covid
//
//  Created by michael on 17.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import Foundation
import KotlinShared

class DateTimeProviderImpl: DateTimeProvider {
    func format(date: Int64, dateFormat: String) -> String {
        return "123"
    }
    
    func getCurrentDateTime(format: String, timeZone: String?) -> String {
        return "123"
    }
    
    func getTimeMills() -> Int64 {
        return 123
    }
    
    func parse(date: String, format: String) -> Int64 {
        return 123
    }
    
}
