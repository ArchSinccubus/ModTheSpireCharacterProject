package valiant.Patches;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import valiant.Cards.Starter.Defend_Valiant;
import valiant.Cards.Starter.Strike_Valiant;

@SpirePatch(cls = "com.megacrit.cardcrawl.events.thecity.BackToBasics", method = "buttonEffect")
public class BackToBasicsPatch {

    @SpireInsertPatch(rloc = 30)
    public static void Insert(Object __obj_instance, int buttonPressed) {
        AbstractPlayer.PlayerClass selection = AbstractDungeon.player.chosenClass;
        if (!selection.toString().equals("IRONCLAD") && !selection.toString().equals("THE_SILENT")
                && !selection.toString().equals("CROWBOT")) {
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if ((c instanceof Strike_Valiant || c instanceof Defend_Valiant) && c.canUpgrade()) {
                    c.upgrade();
                    AbstractDungeon.player.bottledCardUpgradeCheck(c);
                    AbstractDungeon.effectList.add(
                            new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), MathUtils.random(0.1F, 0.9F) * Settings.WIDTH,
                                    MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT));
                }
            }
        }
    }

}
