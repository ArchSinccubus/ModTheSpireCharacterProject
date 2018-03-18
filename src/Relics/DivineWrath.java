package Relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class DivineWrath extends CustomRelic {
    private static final String ID = "Divine Wrath";
    private static final int StrengthAmount = 200;

    private static final String texturePath = "ASSETS/Relics/Wrath.png";

    public DivineWrath() {
        super(ID, getRelicTexture(), RelicTier.STARTER, LandingSound.MAGICAL);
    }

    private static Texture getRelicTexture() {
        return new Texture(texturePath);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new DivineWrath();
    }

    @Override
    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractPower power = null;
        power = new StrengthPower(p, StrengthAmount);

        if(power != null)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, power, 1));
    }
}
