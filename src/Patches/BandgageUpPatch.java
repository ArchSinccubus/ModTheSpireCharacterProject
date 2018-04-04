package Patches;

import java.util.ArrayList;
import java.util.Iterator;

import MainMod.Fudgesickle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.colorless.BandageUp;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(cls="com.megacrit.cardcrawl.cards.colorless.BandageUp", method="applyPowers")
public class BandgageUpPatch {

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());
public static Locator locate = new Locator();
//int place = locate.Locate("applyPowers");


    @SpireInsertPatch(rloc = 56)
    public static void Insert(Object __obj_instance, Object tmpPoolObj) {

        logger.info("DID YOU EVEN GET HERE?");
        //@SuppressWarnings("unchecked")
        //((AbstractCard)__obj_instance).applyPowers();
            Iterator var2 = AbstractDungeon.player.powers.iterator();
            while (var2.hasNext()) {
                AbstractPower p = (AbstractPower) var2.next();
                if (p.name == "Spirit") {
                    ((AbstractCard)__obj_instance).magicNumber = ((AbstractCard)__obj_instance).baseMagicNumber + p.amount;
                    ((AbstractCard)__obj_instance).isMagicNumberModified = true;
                }
            }

    }


//        @SpireInsertPatch(rloc = 45)
//    public static void Replace(Object __obj_instance, Object tmpPoolObj) {
//
//        logger.info("DID YOU EVEN GET HERE?");
//        //@SuppressWarnings("unchecked")
//        //((AbstractCard)__obj_instance).applyPowers();
////            Iterator var2 = AbstractDungeon.player.powers.iterator();
////            while (var2.hasNext()) {
////                AbstractPower p = (AbstractPower) var2.next();
////                if (p.name == "Spirit") {
////                    ((AbstractCard)__obj_instance).magicNumber = ((AbstractCard)__obj_instance).baseMagicNumber + p.amount;
////                    ((AbstractCard)__obj_instance).isMagicNumberModified = true;
////                }
////            }
//            if (!((BandageUp)__obj_instance).upgraded) {
//                ((BandageUp)__obj_instance).baseMagicNumber = 100;
//            }
//    }


    public static class Locator extends SpireInsertLocator {

        // This is the abstract method from SpireInsertLocator that will be used to find the line
        // numbers you want this patch inserted at
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            logger.info("I'M HONESTLY NOT SURE HOW THIS WORKS");
            // finalMatcher is the line that we want to insert our patch before -
            // in this example we are using a `MethodCallMatcher` which is a type
            // of matcher that matches a method call based on the type of the calling
            // object and the name of the method being called. Here you can see that
            // we're expecting the `end` method to be called on a `SpireBatch`
            Matcher finalMatcher = new Matcher.MethodCallMatcher(
                    SpriteBatch.class.getName(), "upgrade");

            // the `new ArrayList<Matcher>()` specifies the prerequisites before the line can be matched -
            // the LineFinder will search for all the prerequisites in order before it will match the finalMatcher -
            // since we didn't specify any prerequisites here, the LineFinder will simply find the first expression
            // that matches the finalMatcher.
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }
}
