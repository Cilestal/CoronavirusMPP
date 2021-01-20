//
//  UIViewControllerExtensions.swift
//  covid
//
//  Created by michael on 17.05.2020.
//  Copyright © 2020 michael. All rights reserved.
//

import UIKit

extension UIViewController {
    
    /**
     Checks if controller is already in navigation stack and pops/unwinds if true
     */
    @discardableResult
    static func popTo(ofKind kind: AnyClass) -> UIViewController? {
        if let nav = UIViewController.topController() as? UINavigationController,
            let containedViewControlled = nav.viewControllers.last(where: { $0.isKind(of: kind) }) {
            nav.popToViewController(containedViewControlled, animated: true)
            return containedViewControlled
        }
        return nil
    }
    
    static var rootController: UIViewController? {
        return UIApplication.shared.delegate?.window??.rootViewController
    }

    static func topController() -> UIViewController? {
        if let rootController = UIApplication.shared.keyWindow?.rootViewController {
            return UIViewController.topController(for: rootController)
        }
        return nil
    }
    
    static func topController(for rootViewController: UIViewController?) -> UIViewController? {
        guard let rootViewController = rootViewController else {
            return nil
        }
        guard let presentedViewController = rootViewController.presentedViewController else {
            return rootViewController
        }
        switch presentedViewController {
        case is UINavigationController:
            let navigationController = presentedViewController as! UINavigationController
            return UIViewController.topController(for: navigationController.viewControllers.last)
        case is UITabBarController:
            let tabBarController = presentedViewController as! UITabBarController
            return UIViewController.topController(for: tabBarController.selectedViewController)
        default:
            return UIViewController.topController(for: presentedViewController)
        }
    }

}
