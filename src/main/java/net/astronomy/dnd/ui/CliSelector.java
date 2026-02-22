package net.astronomy.dnd.ui;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base CLI selector for scrollable lists using JLine.
 */
public class CliSelector {

    protected Terminal terminal;
    private Attributes cookedAttributes;
    private LineReader reader;

    public CliSelector() throws IOException {
        // Open terminal — it starts in cooked mode
        terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        // Capture cooked attributes BEFORE touching raw mode
        cookedAttributes = terminal.getAttributes();

        // Build LineReader while still in cooked mode so it captures the right baseline
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        // Now enter raw mode for menu navigation
        terminal.enterRawMode();
    }

    public CliSelector(Terminal terminal, Attributes cookedAttributes, LineReader reader) {
        // Used by subclasses/callers that share an existing terminal
        this.terminal = terminal;
        this.cookedAttributes = cookedAttributes;
        this.reader = reader;
        terminal.enterRawMode();
    }

    // =========================
    // ACCESSORS FOR SHARING
    // =========================

    public Terminal getTerminal() {
        return terminal;
    }

    public Attributes getCookedAttributes() {
        return cookedAttributes;
    }

    public LineReader getReader() {
        return reader;
    }

    // =========================
    // RAW MODE CONTROL
    // =========================

    protected void enableRawMode() {
        terminal.enterRawMode();
    }

    protected void disableRawMode() {
        terminal.setAttributes(cookedAttributes);
    }

    /**
     * Prompts for a line of text input.
     * Switches to cooked mode, reads the line, then returns to raw mode.
     */
    public String promptLine(String prompt) {
        disableRawMode();
        System.out.print(prompt);
        System.out.flush();
        String line = reader.readLine("");
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
        enableRawMode();
        return line != null ? line : "";
    }

    /**
     * Prompts for an integer, re-asking on invalid input.
     */
    public int promptInt(String prompt) {
        while (true) {
            String raw = promptLine(prompt);
            try {
                return Integer.parseInt(raw.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // =========================
    // SELECTION
    // =========================

    protected String selectOptionInternal(String title, List<String> items) throws IOException {
        if (items.isEmpty()) return null;

        enableRawMode();
        int selected = 0;

        while (true) {
            render(title, items, selected);

            int key = terminal.reader().read();

            if (key == 27) {
                terminal.reader().read();           // skip '['
                int arrow = terminal.reader().read();
                if (arrow == 'A' && selected > 0)                selected--;
                if (arrow == 'B' && selected < items.size() - 1) selected++;
            } else if (key == 13) {
                return items.get(selected);
            }
        }
    }

    public <T> T selectOption(String title, List<Option<T>> options) throws IOException {
        List<String> displayNames = options.stream()
                .map(Option::getDisplayName)
                .collect(Collectors.toList());

        String selectedName = selectOptionInternal(title, displayNames);

        for (Option<T> opt : options) {
            if (opt.getDisplayName().equals(selectedName)) {
                return opt.getValue();
            }
        }
        return null;
    }

    // =========================
    // RENDERING
    // =========================

    private void render(String title, List<String> items, int selected) {
        terminal.puts(InfoCmp.Capability.clear_screen);
        System.out.println(title + ":\n");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i == selected ? "> " : "  ") + items.get(i));
        }
        terminal.flush();
    }

    // =========================
    // LIFECYCLE
    // =========================

    /**
     * Restores cooked mode and closes the terminal.
     * Only call this on the selector that originally opened the terminal.
     */
    public void close() throws IOException {
        disableRawMode();
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
        terminal.close();
    }

    // =========================
    // MENUS
    // =========================

    public String selectMainMenu() throws IOException {
        List<Option<String>> mainMenuOptions = List.of(
                new Option<>("Create New Character", "CREATE"),
                new Option<>("Load Saved Character", "LOAD"),
                new Option<>("Delete Saved Character", "DELETE"),
                new Option<>("Exit", "EXIT")
        );
        return selectOption("Main Menu", mainMenuOptions);
    }

    // =========================
    // OPTION WRAPPER
    // =========================

    public static class Option<T> {
        private final String displayName;
        private final T value;

        public Option(String displayName, T value) {
            this.displayName = displayName;
            this.value = value;
        }

        public String getDisplayName() { return displayName; }
        public T getValue() { return value; }
    }
}