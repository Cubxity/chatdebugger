package dev.cubxity.mods.chatdebugger.client.gui.tree

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.inspector.ArrowComponent
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.constrain
import net.minecraft.class_7614

class MessageHeaderNode(private val header: class_7614) : TreeNode() {
    init {
        addChild(MessageSignatureNode(header.previousSignature, "previousSignature"))
        addChild(UUIDNode(header.sender, "sender"))
    }

    override fun getPrimaryComponent(): UIComponent {
        return UIText("MessageHeader").constrain {
            x = SiblingConstraint()
        }
    }

    override fun getArrowComponent(): TreeArrowComponent =
        ArrowComponent(false)
}