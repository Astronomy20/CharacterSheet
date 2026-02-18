package net.astronomy.dnd.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Selector for character attributes like Race, Class, Background, Alignment.
 */
public class CliAttributesSelector extends CliSelector {

    public CliAttributesSelector() throws IOException {
        super();
    }

    /**
     * Generic enum selector returning the selected enum value.
     */
    public <E extends Enum<E>> E selectEnum(String title, E[] values) throws IOException {
        List<Option<E>> options = Arrays.stream(values)
                .map(e -> {
                    String display;
                    try {
                        display = (String) e.getClass().getMethod("getDisplayName").invoke(e);
                    } catch (Exception ex) {
                        display = e.name();
                    }
                    return new Option<>(display, e);
                })
                .collect(Collectors.toList());

        return selectOption(title, options);
    }
}