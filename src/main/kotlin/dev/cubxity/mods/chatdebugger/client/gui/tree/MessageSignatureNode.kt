package dev.cubxity.mods.chatdebugger.client.gui.tree

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.inspector.ArrowComponent
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.network.message.MessageSignature
import java.util.*

class MessageSignatureNode(
    private val signature: MessageSignature?,
    private val label: String = "signature"
) : TreeNode() {
    override fun getPrimaryComponent(): UIComponent {
        val text = signature?.let { Base64.getEncoder().encodeToString(signature.comp_925) } ?: "(empty)"
        return UIText("$label: $text").constrain {
            x = SiblingConstraint()
            y = 1.pixels
        }
    }

    override fun getArrowComponent(): TreeArrowComponent =
        ArrowComponent(true)
}