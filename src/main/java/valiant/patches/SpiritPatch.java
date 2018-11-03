package valiant.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.cards.AbstractCard",
        method = "applyPowers"
)
public class SpiritPatch {
    public static void Postfix (AbstractCard __instance) {
        if (__instance.rawDescription.toLowerCase().contains("heal ") && !(__instance.type == AbstractCard.CardType.POWER))
        {
            __instance.isMagicNumberModified = false;
            float tmp = (float)__instance.baseMagicNumber;
            Iterator var2 = AbstractDungeon.player.powers.iterator();

            while(var2.hasNext()) {
                AbstractPower p = (AbstractPower)var2.next();
                if (p.ID == "Spirit")
                    tmp += p.amount;
                if (__instance.baseMagicNumber != MathUtils.floor(tmp)) {
                    __instance.isMagicNumberModified = true;
                }
            }

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }

            __instance.magicNumber = MathUtils.floor(tmp);
        }
    }
}