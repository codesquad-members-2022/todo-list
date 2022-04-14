import Foundation
import UIKit

extension UIScreen {
    var isPortrait : Bool {
        return self.bounds.width < self.bounds.height
    }
}
