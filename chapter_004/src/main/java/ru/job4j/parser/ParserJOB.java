package ru.job4j.parser;

/**
 * ParserJOB.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ParserJOB {
    private final String urlSiteJob = "http://www.sql.ru/forum/job-offers";
    private final DataBaseJOB sql;
    private static final Logger LOGGER = Logger.getLogger(ParserJOB.class);
    private final SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm", new Locale("ru", "RU"));
    private String currentPageUrl = urlSiteJob;

    /**
     * Constructor.
     * @param sql Connect to database.
     */
    public ParserJOB(final DataBaseJOB sql) {
       this.sql = sql;
    }

    /**
     * Parser
     */
    public void parser() {
        Set<Vacancy> set = new HashSet<>();
        Timestamp lastVacancyDate = this.sql.getLastVacancyDate();
        if (lastVacancyDate == null) {
            lastVacancyDate = this.setDateToCurrentNewYear();
        }
        try {
            int pageNumber = 1;
            boolean isPrsingCoplete = false;
            String title = "";
            String description = "";
            String author = "";
            Timestamp createDate = null;
            do {
                if (pageNumber != 1) {
                    this.currentPageUrl = this.urlSiteJob + "/" + pageNumber;
                }
                Document doc = Jsoup.connect(this.currentPageUrl).get();
                Elements topics = doc.select("tr:has(.postslisttopic)");
                for (Element topic : topics) {
                    if (topic.text().toLowerCase().contains("java") && !topic.text().toLowerCase().contains("script") && !topic.text().toLowerCase().contains("закрыт")) {
                        Elements link = topic.select("td.postslisttopic > a[href]");
                        Elements nameData = topic.select("td.altCol");
                        title = link.attr("href");
                        description = link.get(0).text();
                        author = nameData.get(0).text();
                        createDate = parseDate(nameData.get(1).text());
                        if (createDate.before(lastVacancyDate)) {
                            isPrsingCoplete = true;
                            break;
                        }
                        set.add(new Vacancy(title, description, author, createDate));
                    }
                }
                pageNumber++;
            } while (!isPrsingCoplete);
            this.sql.addVacancy(set);
        } catch (IOException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Date To Current Year.
     * @return
     */
    private Timestamp setDateToCurrentNewYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(LocalDate.now().getYear(), 0, 1, 0, 0, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * Parse Date
     * @param date Date from site.
     * @return Data.
     */
    public Timestamp parseDate(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date.contains("сегодня")) {
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(9, 11)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(12, 14)));
        } else if (date.contains("вчера")) {
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(7, 9)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
        } else {
            try {
                calendar.setTime(format.parse(date));
            } catch (ParseException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return new Timestamp(calendar.getTimeInMillis());
    }
}
