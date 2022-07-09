package dev.cubxity.mods.chatdebugger.client.gui.tree

import com.mojang.authlib.GameProfile
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.inspector.ArrowComponent
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels

class GameProfileNode(private val gameProfile: GameProfile) : TreeNode() {
    init {
        addChild(LiteralNode(gameProfile.id, "id"))
        addChild(LiteralNode(gameProfile.name, "name"))
    }

    override fun getPrimaryComponent(): UIComponent {
        return UIText("GameProfile").constrain {
            x = SiblingConstraint()
            y = 1.pixels
        }
    }

    override fun getArrowComponent(): TreeArrowComponent =
        ArrowComponent(false)
}