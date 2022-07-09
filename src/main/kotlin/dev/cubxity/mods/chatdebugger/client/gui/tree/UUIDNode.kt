package dev.cubxity.mods.chatdebugger.client.gui.tree

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.inspector.ArrowComponent
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.constrain
import java.util.*

class UUIDNode(
    private val uuid: UUID,
    private val label: String = "uuid"
) : TreeNode() {
    override fun getPrimaryComponent(): UIComponent {
        return UIText("$label: $uuid").constrain {
            x = SiblingConstraint()
        }
    }

    override fun getArrowComponent(): TreeArrowComponent =
        ArrowComponent(true)
}