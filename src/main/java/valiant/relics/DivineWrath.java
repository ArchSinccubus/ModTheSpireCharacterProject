package valiant.relics;

import valiant.ValiantMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DivineWrath extends CustomRelic {
    public  static final String ID = "Divine Wrath";
    public static final String[] DESCRIPTIONS = new String[] {
            "You get 200 Dexterity. I hope you're happy"
    };
    private static final int StrengthAmount = 1;

    public static final Logger logger = LogManager.getLogger(DivineWrath.class.getName());


    public DivineWrath() {
        super(ID, new Texture(ValiantMod.DIVINE_WRATH), RelicTier.STARTER, LandingSound.MAGICAL);
        logger.info("initialized");
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new DivineWrath();
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, StrengthAmount), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower (p, StrengthAmount), 1));
        }
    }

