package dev.cubxity.mods.chatdebugger.client.gui.tree

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.inspector.ArrowComponent
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.client.report.ReceivedMessage
import net.minecraft.client.report.ReceivedMessage.ChatMessage
import net.minecraft.client.report.ReceivedMessage.GameMessage

class ReceivedMessageNode(private val message: ReceivedMessage) : TreeNode() {
    init {
        when (message) {
            is ChatMessage -> {
                addChild(SignedMessageNode(message.message))
            }
        }
    }

    override fun getPrimaryComponent(): UIComponent {
        val text = when (message) {
            is ChatMessage -> "ChatMessage"
            is GameMessage -> "GameMessage"
            else -> message::class.java.simpleName
        }
        return UIText("ReceivedMessage ($text)").constrain {
            x = SiblingConstraint()
            y = 1.pixels
        }
    }

    override fun getArrowComponent(): TreeArrowComponent =
        ArrowComponent(false)
}