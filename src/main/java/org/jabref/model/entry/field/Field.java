package org.jabref.model.entry.field;

import java.util.EnumSet;
import java.util.Optional;

import org.jabref.model.strings.StringUtil;

public interface Field {

    /**
     * properties contains mappings to tell the EntryEditor to add a specific function to this field,
     * for instance a dropdown for selecting the month for the month field.
     * <p>
     * Note that this set needs to be mutable. This is required, because we allow standard fields to be modifiable via the UI.
     */
    EnumSet<FieldProperty> getProperties();

    /**
     * @return A version of the field name more suitable for display in the UI
     */
    default String getDisplayName() {
        return StringUtil.capitalizeFirst(getName());
    }

    /**
     * Name used for writing to .bib (or as XMP data)
     *
     * However, BibEntryWriter converts to lower case. See {@link org.jabref.logic.bibtex.BibEntryWriter#getFormattedFieldName}
     */
    String getName();

    boolean isStandardField();

    default boolean isDeprecated() {
        return false;
    }

    default Optional<Field> getAlias() {
        return Optional.empty();
    }

    default boolean isNumeric() {
        return getProperties().contains(FieldProperty.NUMERIC);
    }

    default boolean isMultiLineDefined() {
        return getProperties().contains(FieldProperty.MULTILINE_TEXT);
    }
}
