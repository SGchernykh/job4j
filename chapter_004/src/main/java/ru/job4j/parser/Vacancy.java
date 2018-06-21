package ru.job4j.parser;

/**
 * Vacancy.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.sql.Timestamp;

public class Vacancy {
    private final String title;
    private final String description;
    private final String author;
    private final Timestamp createDate;

    /**
     * Constructor.
     * @param title Job path.
     * @param description Description vacancy.
     * @param author Author vacancy.
     * @param createDate Create Date.
     */
    public Vacancy(String title, String description, String author, Timestamp createDate) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Vacancy{" + "description='" + description + '\'' + ", author='" + author + '\'' + '}';
    }
}
