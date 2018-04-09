package valiant.Relics;

import valiant.MainMod.*;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class PrayerBeads extends CustomRelic {
    public static final String ID = "Prayer Beads";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private boolean WasObtained;

    private static final String texturePath = "valiant/Relics/CrossPendantPic.png";

    public static final Logger logger = LogManager.getLogger(DivineWrath.class.getName());


    public PrayerBeads() {
        super(ID, Fudgesickle.getTex(Fudgesickle.PRAYER_BEADS), RelicTier.UNCOMMON, LandingSound.FLAT);
        logger.info("initialized");
        WasObtained = false;
    }

    private static Texture getRelicTexture() {
        logger.info("getting texture");
        Texture tex = new Texture(texturePath);
        logger.info("got texture");
        return tex;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.cost >= 3 && !WasObtained)
        {
            this.flash();
            AbstractCard cad = returnTruly1Cost(1, AbstractDungeon.cardRandomRng);
            cad.cost = 0;
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cad));
            WasObtained = true;
        }
    }

    public void atTurnStart() {
        WasObtained = false;
    }

    public static AbstractCard returnTruly1Cost(int cost, Random rng) {
        ArrayList<AbstractCard> list = new ArrayList();

        Iterator var5 = AbstractDungeon.srcCommonCardPool.group.iterator();
        Iterator var6 =  AbstractDungeon.srcUncommonCardPool.group.iterator();
        Iterator var7 =  AbstractDungeon.srcUncommonCardPool.group.iterator();

        AbstractCard c;
        while(var5.hasNext()) {
            c = (AbstractCard)var5.next();
            if (c.cost == cost) {
                list.add(c);
            }
        }
        while(var6.hasNext()) {
            c = (AbstractCard)var6.next();
            if (c.cost == cost) {
                list.add(c);
            }
        }
        while(var7.hasNext()) {
            c = (AbstractCard)var7.next();
            if (c.cost == cost) {
                list.add(c);
            }
        }

        return (AbstractCard)list.get(rng.random(list.size() - 1));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PrayerBeads();
    }

}
