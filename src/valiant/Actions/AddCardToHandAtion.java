package valiant.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class AddCardToHandAtion extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean upgraded;

    public AddCardToHandAtion(int amount, boolean upgraded) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.upgraded = upgraded;
    }

    public void update() {
        AbstractCard card;
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
            Iterator var5 = this.p.drawPile.group.iterator();
            Iterator var6 = this.p.discardPile.group.iterator();
            Iterator var7 = this.p.exhaustPile.group.iterator();

            if (upgraded)
            {
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CollectorCurseEffect(p.drawX, p.drawY+ 35), 1.5F));
            }

            while(var5.hasNext()) {
                AbstractCard c = (AbstractCard)var5.next();
                    tmp.addToTop(c);

            }
            if (upgraded)
            {
                while(var6.hasNext()) {
                    AbstractCard c = (AbstractCard)var6.next();
                    tmp.addToTop(c);

                }
                while(var7.hasNext()) {
                    AbstractCard c = (AbstractCard)var7.next();
                    tmp.addToTop(c);

                }
            }

            if (tmp.size() == 0) {
                this.isDone = true;
            } else if (tmp.size() == 1) {
                card = tmp.getTopCard();
                if (this.p.hand.size() == 10) {
                    this.p.drawPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    if (this.p.drawPile.contains(card)) {
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        this.p.drawPile.removeCard(card);
                    }
                    if (this.p.discardPile.contains(card)) {
                        card.current_x = CardGroup.DISCARD_PILE_X;
                        card.current_y = CardGroup.DISCARD_PILE_Y;
                        this.p.discardPile.removeCard(card);
                    }
                    if (this.p.exhaustPile.contains(card)) {
                        card.current_x = CardGroup.DISCARD_PILE_X;
                        card.current_y = CardGroup.DISCARD_PILE_Y;
                        this.p.exhaustPile.removeCard(card);
                    }
                    AbstractDungeon.player.hand.addToTop(card);
                    AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, 3.0F, "#r@THE@ #r@PRICE@ #r@IS@ #r@PAID@", true));
                    AbstractCard THEPRICEISPAID = returnTrulyRandomCurse(CardType.CURSE , AbstractDungeon.cardRandomRng);
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(THEPRICEISPAID));
                    //AbstractDungeon.effectList.add(/*EL:199*/new ShowCardAndObtainEffect(THEPRICEISPAID.makeCopy(), Settings.WIDTH / 1.5f, Settings.HEIGHT / 2.0f));
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }

                this.isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    card = (AbstractCard)var1.next();
                    card.unhover();
                    if (this.p.hand.size() == 10) {
                        this.p.drawPile.moveToDiscardPile(card);
                        this.p.createHandIsFullDialog();
                    } else {
                        //this.p.drawPile.removeCard(card);
                        if (this.p.drawPile.contains(card))
                            this.p.drawPile.removeCard(card);
                        if (this.p.discardPile.contains(card))
                            this.p.discardPile.removeCard(card);
                        if (this.p.exhaustPile.contains(card))
                            this.p.exhaustPile.removeCard(card);
                        this.p.hand.addToTop(card);
                    }
                    AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, 3.0F, "#r@THE@ #r@PRICE@ #r@IS@ #r@PAID@", true));
                    AbstractCard THEPRICEISPAID = returnTrulyRandomCurse(CardType.CURSE , AbstractDungeon.cardRandomRng);
                    //AbstractDungeon.effectList.add(/*EL:199*/new ShowCardAndObtainEffect(THEPRICEISPAID.makeCopy(), Settings.WIDTH / 1.5f, Settings.HEIGHT / 2.0f));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(THEPRICEISPAID));
                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }

            this.tickDuration();
        }


    }

    public static AbstractCard returnTrulyRandomCurse(CardType type, Random rng) {
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator var3 = AbstractDungeon.srcCurseCardPool.group.iterator();

        AbstractCard c;
        while(var3.hasNext()) {
            c = (AbstractCard)var3.next();
            if (c.type == type) {
                list.add(c);
            }
        }

        return (AbstractCard)list.get(rng.random(list.size() - 1));
    }

    static {
        String[] TEXTS = new String[1];
        TEXTS[0] = "Choose a card to Add to Your Hand...";
        TEXT = TEXTS;
    }
}
