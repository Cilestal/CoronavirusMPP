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
    func format(date: String, sourceFormat: String, dateFormat: String) -> String {
        return "1"
    }
    
    func getCurrentDateTime(format: String, timeZone: String?) -> String {
        return "1"
    }
    
    func getTimeMills() -> Int64 {
        return 123
    }
    
    
}
