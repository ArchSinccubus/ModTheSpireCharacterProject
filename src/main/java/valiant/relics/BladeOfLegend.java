package valiant.relics;

import valiant.ValiantMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BladeOfLegend extends CustomRelic {
    public static final String ID = "Blade Of Legend";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private boolean WasObtained;

    public static final Logger logger = LogManager.getLogger(BladeOfLegend.class.getName());


    public BladeOfLegend() {
        super(ID, new Texture(ValiantMod.BLADE_OF_LEGEND), RelicTier.BOSS, LandingSound.CLINK);
        logger.info("initialized");
        WasObtained = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.rarity == AbstractCard.CardRarity.RARE) {
            this.flash();
            action.exhaustCard = true;
        }

    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BladeOfLegend();
    }

}
