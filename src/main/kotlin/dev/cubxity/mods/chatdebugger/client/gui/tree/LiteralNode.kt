package dev.cubxity.mods.chatdebugger.client.gui.tree

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.inspector.ArrowComponent
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.constrain
import net.minecraft.class_7608

class LiteralNode(private val value: Any?, private val label: String) : TreeNode() {
    override fun getPrimaryComponent(): UIComponent {
        return UIText("$label: ${value ?: "(empty)"}").constrain {
            x = SiblingConstraint()
        }
    }

    override fun getArrowComponent(): TreeArrowComponent =
        ArrowComponent(true)
}