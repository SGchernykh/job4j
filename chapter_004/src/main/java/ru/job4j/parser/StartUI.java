package ru.job4j.parser;

/**
 * StartUI.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Timer;
import java.util.TimerTask;

public class StartUI extends TimerTask {
    private final ParserJOB parserJOB;

    /**
     * Constructor.
     * @param prserJOB
     */
    public StartUI(final ParserJOB prserJOB) {
        this.parserJOB = prserJOB;
    }

    @Override
    public void run() {
        this.parserJOB.parser();
    }

    /**
     * Starts the program.
     * @param args console inputted arguments.
     */
    public static void main(String[] args) {
        String propertiesFile = "configJOB.properties";
        Settings settings = new Settings(propertiesFile);
        DataBaseJOB sql = new DataBaseJOB(propertiesFile);
        ParserJOB parserJOB = new ParserJOB(sql);
        Timer time = new Timer();
        StartUI startUI =  new StartUI(parserJOB);
        long period = Long.parseLong(settings.getValue("period"));
        time.schedule(startUI, 0, period);
    }
}