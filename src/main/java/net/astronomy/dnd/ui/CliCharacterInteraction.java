package net.astronomy.dnd.ui;

import net.astronomy.dnd.model.Character;
import net.astronomy.dnd.model.enums.attributes.Alignment;
import net.astronomy.dnd.model.enums.attributes.Background;
import net.astronomy.dnd.model.enums.attributes.CharacterClass;
import net.astronomy.dnd.model.enums.attributes.Race;
import net.astronomy.dnd.model.enums.equipment.*;
import net.astronomy.dnd.util.CharacterPrinter;
import net.astronomy.dnd.util.Session;
import org.jline.reader.LineReader;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CliCharacterInteraction extends CliSelector {

    private final Character character;

    private static final String CAT_ARMORS       = "ARMORS";
    private static final String CAT_WEAPONS      = "WEAPONS";
    private static final String CAT_GEAR         = "GEAR";
    private static final String CAT_INSTRUMENTS  = "INSTRUMENTS";
    private static final String CAT_MISC         = "MISC";
    private static final String CAT_ALL          = "ALL";
    private static final String BACK_SENTINEL    = "__BACK__";

    public CliCharacterInteraction(Character character) throws IOException {
        super();
        this.character = character;
    }

    public CliCharacterInteraction(Terminal terminal, Attributes cookedAttributes, LineReader reader, Character character) {
        super(terminal, cookedAttributes, reader);
        this.character = character;
    }

    public void start() throws IOException {

        while (true) {
            terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.flush();
            enableRawMode();

            List<Option<String>> options = List.of(
                    new Option<>("View Character Sheet", "VIEW"),
                    new Option<>("Add Life",             "ADD_LIFE"),
                    new Option<>("Subtract Life",        "SUB_LIFE"),
                    new Option<>("Add XP",               "ADD_XP"),
                    new Option<>("Remove XP",            "REMOVE_XP"),
                    new Option<>("Set Level",            "SET_LEVEL"),
                    new Option<>("Add Item",             "ADD_ITEM"),
                    new Option<>("Remove Item",          "REMOVE_ITEM"),
                    new Option<>("Change Race",          "CHANGE_RACE"),
                    new Option<>("Change Class",         "CHANGE_CLASS"),
                    new Option<>("Change Background",    "CHANGE_BACKGROUND"),
                    new Option<>("Change Alignment",     "CHANGE_ALIGNMENT"),
                    new Option<>("Exit",                 "EXIT")
            );

            String action = selectOption("Character Interaction -- " + character.getName(), options);

            switch (action) {
                case "VIEW"              -> viewSheet();
                case "ADD_LIFE"         -> modifyLife(true);
                case "SUB_LIFE"         -> modifyLife(false);
                case "ADD_XP"           -> modifyXp(true);
                case "REMOVE_XP"        -> modifyXp(false);
                case "SET_LEVEL"        -> setLevel();
                case "ADD_ITEM"         -> manageInventory(true);
                case "REMOVE_ITEM"      -> manageInventory(false);
                case "CHANGE_RACE"      -> changeBaseAttribute("Race",       Race.values(),           character::setRace);
                case "CHANGE_CLASS"     -> changeBaseAttribute("Class",      CharacterClass.values(), character::setCharacterClass);
                case "CHANGE_BACKGROUND"-> changeBaseAttribute("Background", Background.values(),     character::setBackground);
                case "CHANGE_ALIGNMENT" -> changeBaseAttribute("Alignment",  Alignment.values(),      character::setAlignment);
                case "EXIT" -> {
                    Session.saveCharacter(character);
                    return;
                }
            }
        }
    }

    /** View character sheet */
    private void viewSheet() {
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
        disableRawMode();
        CharacterPrinter.print(character);
        promptLine("\nPress Enter to return to menu...");
    }

    /** Edit life points */
    private void modifyLife(boolean add) {
        int amount = promptInt("Enter life amount: ");
        if (add) character.getLife().addLifePoints(Math.abs(amount));
        else     character.getLife().removeLifePoints(Math.abs(amount));
    }

    /** Edit experience points */
    private void modifyXp(boolean add) {
        int amount = promptInt("Enter XP amount: ");
        if (add) character.getLevel().addExperiencePoints(Math.abs(amount), character.getLife());
        else     character.getLevel().removeExperiencePoints(Math.abs(amount), character.getLife());
    }

    /** Set character level */
    private void setLevel() {
        if (confirmManual(
                "WARNING: Manually setting level alters progression.\nType 'yes' to proceed: ",
                "yes")) {
            return;
        }

        int level = promptInt("Enter new level (1-20): ");
        character.getLevel().setLevel(level, character.getLife());
    }

    /** Edit base attributes */
    private <E extends Enum<E>> void changeBaseAttribute(
            String attributeName,
            E[] values,
            java.util.function.Consumer<E> setter) throws IOException {

        if (confirmManual(
                "WARNING: Modifying a base attribute affects derived stats.\nType 'confirm' to proceed: ",
                "confirm")) {
            return;
        }

        CliAttributesSelector selector = new CliAttributesSelector(
                getTerminal(), getCookedAttributes(), getReader()
        );
        E selected = selector.selectEnum("Select new " + attributeName, values);
        setter.accept(selected);
    }

    private void manageInventory(boolean add) throws IOException {
        String verb = add ? "Add" : "Remove";

        while (true) {
            List<Option<String>> categories = List.of(
                    new Option<>("Armors",         CAT_ARMORS),
                    new Option<>("Weapons",        CAT_WEAPONS),
                    new Option<>("Adventure Gear", CAT_GEAR),
                    new Option<>("Instruments",    CAT_INSTRUMENTS),
                    new Option<>("Miscellaneous",  CAT_MISC),
                    new Option<>("All Items",      CAT_ALL),
                    new Option<>("-- Back --",     BACK_SENTINEL)
            );

            String category = selectOption(verb + " Item -- Select Category", categories);
            if (category == null || category.equals(BACK_SENTINEL)) return;

            List<Option<String>> items = buildItemOptions(category);
            items.add(new Option<>("-- Back --", BACK_SENTINEL));

            String itemKey = selectOption(verb + " Item -- Select Item", items);
            if (itemKey == null || itemKey.equals(BACK_SENTINEL)) continue;

            int quantity = promptInt("Enter quantity: ");
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                promptLine("Press Enter to continue...");
                continue;
            }

            applyInventoryChange(itemKey, quantity, add);
            promptLine("Done. Press Enter to continue...");
            return;
        }
    }
    /**
     * Builds a flat list of item options for a given category key.
     * Each option's value is "<CATEGORY>:<ENUM_NAME>" so we can parse it back later.
     */
    private List<Option<String>> buildItemOptions(String category) {
        List<Option<String>> options = new ArrayList<>();

        if (category.equals(CAT_ARMORS) || category.equals(CAT_ALL)) {
            Arrays.stream(Armors.values())
                    .forEach(e -> options.add(new Option<>(e.getDisplayName(), CAT_ARMORS + ":" + e.name())));
        }
        if (category.equals(CAT_WEAPONS) || category.equals(CAT_ALL)) {
            Arrays.stream(Weapons.values())
                    .forEach(e -> options.add(new Option<>(e.getDisplayName(), CAT_WEAPONS + ":" + e.name())));
        }
        if (category.equals(CAT_GEAR) || category.equals(CAT_ALL)) {
            Arrays.stream(AdventureGears.values())
                    .forEach(e -> options.add(new Option<>(e.getDisplayName(), CAT_GEAR + ":" + e.name())));
        }
        if (category.equals(CAT_INSTRUMENTS) || category.equals(CAT_ALL)) {
            Arrays.stream(Instruments.values())
                    .forEach(e -> options.add(new Option<>(e.getDisplayName(), CAT_INSTRUMENTS + ":" + e.name())));
        }
        if (category.equals(CAT_MISC) || category.equals(CAT_ALL)) {
            Arrays.stream(Miscellaneous.values())
                    .forEach(e -> options.add(new Option<>(e.getDisplayName(), CAT_MISC + ":" + e.name())));
        }

        return options;
    }

    /**
     * Parses "<CATEGORY>:<ENUM_NAME>" and delegates to the correct inventory method.
     * When removing, checks available quantity first and prompts the user if
     * the requested amount exceeds what is in the inventory.
     */
    private void applyInventoryChange(String itemKey, int quantity, boolean add) {
        String[] parts = itemKey.split(":", 2);
        String cat  = parts[0];
        String name = parts[1];

        switch (cat) {
            case CAT_ARMORS -> {
                Armors item = Armors.valueOf(name);
                if (add) character.getInventory().addArmor(item, quantity);
                else {
                    int available = character.getInventory().getArmors().getOrDefault(item, 0);
                    int toRemove  = resolveRemoveQuantity(item.getDisplayName(), quantity, available);
                    if (toRemove > 0) character.getInventory().removeArmor(item, toRemove);
                }
            }
            case CAT_WEAPONS -> {
                Weapons item = Weapons.valueOf(name);
                if (add) character.getInventory().addWeapon(item, quantity);
                else {
                    int available = character.getInventory().getWeapons().getOrDefault(item, 0);
                    int toRemove  = resolveRemoveQuantity(item.getDisplayName(), quantity, available);
                    if (toRemove > 0) character.getInventory().removeWeapon(item, toRemove);
                }
            }
            case CAT_GEAR -> {
                AdventureGears item = AdventureGears.valueOf(name);
                if (add) character.getInventory().addAdventureGear(item, quantity);
                else {
                    int available = character.getInventory().getAdventureGear().getOrDefault(item, 0);
                    int toRemove  = resolveRemoveQuantity(item.getDisplayName(), quantity, available);
                    if (toRemove > 0) character.getInventory().removeAdventureGear(item, toRemove);
                }
            }
            case CAT_INSTRUMENTS -> {
                Instruments item = Instruments.valueOf(name);
                if (add) character.getInventory().addInstrument(item, quantity);
                else {
                    int available = character.getInventory().getInstruments().getOrDefault(item, 0);
                    int toRemove  = resolveRemoveQuantity(item.getDisplayName(), quantity, available);
                    if (toRemove > 0) character.getInventory().removeInstrument(item, toRemove);
                }
            }
            case CAT_MISC -> {
                Miscellaneous item = Miscellaneous.valueOf(name);
                if (add) character.getInventory().addMiscellaneous(item, quantity);
                else {
                    int available = character.getInventory().getMiscellaneous().getOrDefault(item, 0);
                    int toRemove  = resolveRemoveQuantity(item.getDisplayName(), quantity, available);
                    if (toRemove > 0) character.getInventory().removeMiscellaneous(item, toRemove);
                }
            }
        }
    }

    /**
     * If the requested removal quantity exceeds what is available, informs the user
     * and asks whether to remove all available instead.
     *
     * @return the quantity to actually remove (0 = canceled, available = remove all)
     */
    private int resolveRemoveQuantity(String itemName, int requested, int available) {
        if (available == 0) {
            System.out.println("You have no " + itemName + " in your inventory.");
            promptLine("Press Enter to continue...");
            return 0;
        }

        if (requested <= available) return requested;

        System.out.println("You only have " + available + "x " + itemName
                + " but tried to remove " + requested + ". Removing all " + available + ".");
        return available;
    }

    /** Manual confirm for important modifications */
    private boolean confirmManual(String warningText, String requiredWord) {
        String input = promptLine(warningText);
        return !input.trim().equalsIgnoreCase(requiredWord);
    }
}