package dev.cubxity.mods.chatdebugger.client.gui.tree

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.inspector.ArrowComponent
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.class_7608

class MessageBodyNode(private val body: class_7608) : TreeNode() {
    init {
        addChild(TextComponentNode(body.content, "content"))
        addChild(InstantNode(body.timeStamp, "timestamp"))
        addChild(LiteralNode(body.salt, "salt"))
    }

    override fun getPrimaryComponent(): UIComponent {
        return UIText("MessageBody").constrain {
            x = SiblingConstraint()
            y = 1.pixels
        }
    }

    override fun getArrowComponent(): TreeArrowComponent =
        ArrowComponent(false)
}