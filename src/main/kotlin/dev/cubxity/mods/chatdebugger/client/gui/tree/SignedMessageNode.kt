package dev.cubxity.mods.chatdebugger.client.gui.tree

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.inspector.ArrowComponent
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.constrain
import net.minecraft.network.message.SignedMessage

class SignedMessageNode(private val message: SignedMessage) : TreeNode() {
    init {
        addChild(MessageHeaderNode(message.signedHeader))
        addChild(MessageSignatureNode(message.headerSignature))
        addChild(MessageBodyNode(message.signedBody))
        addChild(TextComponentNode(message.unsignedContent?.orElse(null), "unsignedContent"))
    }

    override fun getPrimaryComponent(): UIComponent {
        return UIText("SignedHeader").constrain {
            x = SiblingConstraint()
        }
    }

    override fun getArrowComponent(): TreeArrowComponent =
        ArrowComponent(false)
}