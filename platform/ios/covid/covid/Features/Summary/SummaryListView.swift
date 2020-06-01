//
//  SummaryListView.swift
//  covid
//
//  Created by michael on 25.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import UIKit

class SummaryListView: UIView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        createSubviews()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        createSubviews()
    }
    
    func createSubviews() {
        backgroundColor = .red
    }
}
