package valiant.Patches;

import java.util.Iterator;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Cards.Starter.Strike_Valiant;

@SpirePatch(cls="com.megacrit.cardcrawl.events.thecity.Vampires", method="replaceAttacks")
public class VampirePatch {
    @SpireInsertPatch(rloc=8)
    public static void Insert(Object __obj_instance) {
        for (Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator(); i.hasNext();) {
            AbstractCard e = (AbstractCard) i.next();
            if (e instanceof Strike_Valiant) {
                i.remove();
            }
        }
    }
}