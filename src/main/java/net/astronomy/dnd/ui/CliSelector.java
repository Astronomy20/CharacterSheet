package net.astronomy.dnd.ui;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base CLI selector for scrollable lists using JLine.
 * Can be extended by session selectors, attribute selectors, or used directly for menus.
 */
public class CliSelector {

    protected Terminal terminal;

    public CliSelector() throws IOException {
        terminal = TerminalBuilder.builder().system(true).build();
        terminal.enterRawMode();
    }

    protected String selectOptionInternal(String title, List<String> items) throws IOException {
        if (items.isEmpty()) return null;

        int selected = 0;

        while (true) {
            render(title, items, selected);

            int key = terminal.reader().read();
            if (key == 27) { // arrow keys
                terminal.reader().read(); // skip '['
                int arrow = terminal.reader().read();
                if (arrow == 'A' && selected > 0) selected--; // Up
                if (arrow == 'B' && selected < items.size() - 1) selected++; // Down
            } else if (key == 13) { // Enter
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

    private void render(String title, List<String> items, int selected) {
        terminal.puts(InfoCmp.Capability.clear_screen);
        System.out.println(title + ":\n");
        for (int i = 0; i < items.size(); i++) {
            String prefix = (i == selected) ? "> " : "  ";
            System.out.println(prefix + items.get(i));
        }
        terminal.flush();
    }

    public void close() throws IOException {
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
        terminal.close();
    }

    /** Main menu options hardcoded */
    public String selectMainMenu() throws IOException {
        List<Option<String>> mainMenuOptions = List.of(
                new Option<>("Create New Character", "CREATE"),
                new Option<>("Load Saved Character", "LOAD")
        );

        return selectOption("Main Menu", mainMenuOptions);
    }

    /** Option Wrapper */
    public static class Option<T> {
        private final String displayName;
        private final T value;

        public Option(String displayName, T value) {
            this.displayName = displayName;
            this.value = value;
        }

        public String getDisplayName() {
            return displayName;
        }

        public T getValue() {
            return value;
        }
    }
}