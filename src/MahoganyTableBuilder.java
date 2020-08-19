import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.Locale;

import static org.dreambot.api.methods.Calculations.random;

@ScriptManifest(
        author = "Nico",
        description = "Builds mahogany tables",
        category = Category.CONSTRUCTION,
        version = 1.0,
        name = "Mahogany Table Builder"
)
public class MahoganyTableBuilder extends AbstractScript {

    private final int MAHOGANY_TABLE_ID = 13298;
    private final int TABLE_SPACE_ID = 15298;
    private final int DEMON_BUTLER_ID = 229;
    public Timer timer = new Timer();
    private int beginningXp;

    /**
     * Assure I have 4 placeholders in inventory including the saw and hammer.
     * Assure I already told my butler to fetch 24 mahogany planks and they are in my inventory.
     */
    @Override
    public void onStart() {
        super.onStart();
        getMouse().getMouseSettings().setWordsPerMinute(2000);
        beginningXp = getSkills().getExperience(Skill.CONSTRUCTION);
    }

    @Override
    public int onLoop() {
        sleepUntil(() -> getGameObjects().closest(TABLE_SPACE_ID) != null, timeoutTime());
        intervalSleep();
        getGameObjects().closest(TABLE_SPACE_ID).interact("Build");
        sleepUntil(() -> getWidgets().getWidgetChild(458, 1) != null && getWidgets().getWidgetChild(458, 1).isVisible(), timeoutTime());
        spamOneAndSix();
        sleepUntil(() -> getGameObjects().closest(MAHOGANY_TABLE_ID) != null, timeoutTime());
        intervalSleep();
        getGameObjects().closest(MAHOGANY_TABLE_ID).interact("Remove");
        intervalSleep();
        spamOneAndSix();
        sleepUntil(() -> getGameObjects().closest(TABLE_SPACE_ID) != null, timeoutTime());
        intervalSleep();
        getGameObjects().closest(TABLE_SPACE_ID).interact("Build");
        sleepUntil(() -> getWidgets().getWidgetChild(458, 1) != null && getWidgets().getWidgetChild(458, 1).isVisible(), timeoutTime());
        spamOneAndSix();
        sleepUntil(() -> getGameObjects().closest(MAHOGANY_TABLE_ID) != null, timeoutTime());
        intervalSleep();
        getGameObjects().closest(MAHOGANY_TABLE_ID).interact("Remove");
        intervalSleep();
        spamOneAndSix();
        sleepUntil(() -> getGameObjects().closest(TABLE_SPACE_ID) != null, timeoutTime());
        intervalSleep();
        getGameObjects().closest(TABLE_SPACE_ID).interact("Build");
        sleepUntil(() -> getWidgets().getWidgetChild(458, 1) != null && getWidgets().getWidgetChild(458, 1).isVisible(), timeoutTime());
        spamOneAndSix();
        sleepUntil(() -> getGameObjects().closest(MAHOGANY_TABLE_ID) != null, timeoutTime());
        intervalSleep();
        getGameObjects().closest(MAHOGANY_TABLE_ID).interact("Remove");
        intervalSleep();
        spamOneAndSix();
        sleepUntil(() -> getGameObjects().closest(TABLE_SPACE_ID) != null, timeoutTime());
        intervalSleep();
        getGameObjects().closest(TABLE_SPACE_ID).interact("Build");
        sleepUntil(() -> getWidgets().getWidgetChild(458, 1) != null && getWidgets().getWidgetChild(458, 1).isVisible(), timeoutTime());
        spamOneAndSix();
        sleepUntil(() -> getGameObjects().closest(MAHOGANY_TABLE_ID) != null, timeoutTime());
        getNpcs().closest(DEMON_BUTLER_ID).interact();
        intervalSleep();
        spamOneAndSix();
        intervalSleep();
        getGameObjects().closest(MAHOGANY_TABLE_ID).interact("Remove");
        intervalSleep();
        spamOneAndSix();
        getMouse().moveMouseOutsideScreen();
        sleepUntil(() -> getInventory().isFull(), random(9500, 10000));
        intervalSleep();
        return random(330, 360);
    }

    @Override
    public void onPaint(Graphics graphics) {
        super.onPaint(graphics);
        int currentXp = getSkills().getExperience(Skill.CONSTRUCTION);
        int xpGrained = currentXp - beginningXp;
        graphics.setColor(Color.BLACK);
        graphics.drawString("Mahogany Table Builder", 330, 365);
        graphics.drawString("Time running: " + timer.formatTime(), 330, 385);
        graphics.drawString("Total XP gained: " + formatInt(xpGrained), 330, 405);
        graphics.drawString("Hourly XP rate: " + formatInt(timer.getHourlyRate(xpGrained)), 330, 425);
    }

    private String formatInt(int num) {
        return NumberFormat.getNumberInstance(Locale.US).format(num);
    }

    private void intervalSleep() {
        sleep(230, 250);
    }

    private int timeoutTime() {
        return random(4500, 5000);
    }
    
    private void spamOneAndSix() {
        int randomLoops = random(3, 5);
        for (int i = 0; i < randomLoops; i++) {
            pressOneAndSix();
            sleep(235, 250);
        }
    }

    private void pressOneAndSix() {
        int randomOrder = random(2);
        String firstKey;
        String secondKey;
        if (randomOrder == 0) {
            firstKey = "1";
            secondKey = "6";
        } else {
            firstKey = "6";
            secondKey = "1";
        }
        getKeyboard().type(firstKey, false);
        sleep(2, 3);
        getKeyboard().type(secondKey, false);
    }
}
