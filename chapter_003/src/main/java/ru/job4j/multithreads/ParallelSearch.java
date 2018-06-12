package ru.job4j.multithreads;
/**
 * Parallel Search.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    private volatile boolean finish = true;
    private Thread read;

    @GuardedBy("this")
    private final Queue<String> files = new LinkedList<>();

    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();

    /**
     * Constructor.
     * @param root Root folder.
     * @param text find text.
     * @param exts ends With.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Init.
     */
    public synchronized void init() {
        Thread search = new Thread(() -> {
            for (String ext : exts) {
                try {
                    Files.walkFileTree(Paths.get(root), new MyFileVisitorFiles(ext));
                    finish = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        search.start();
        read = new Thread(() -> {
            while (finish || !files.isEmpty()) {
                if (!files.isEmpty()) {
                    String file = files.poll();
                    try {
                        Files.walkFileTree(Paths.get(file), new MyFileVisitorPaths(text));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        read.start();
    }

    /**
     * @return
     */
    public List<String>  result() {
        try {
            this.read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.paths;
    }

    /**
     * MyFileVisitorPaths
     */
    private class MyFileVisitorPaths extends SimpleFileVisitor<Path> {
        String partOfContent;

        /**
         * Constructor.
         * @param partOfContent part Of Content.
         */
        public MyFileVisitorPaths(String partOfContent) {
            this.partOfContent = partOfContent;
        }

        @Override
        public  FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String content = new String(Files.readAllBytes(file));
            boolean containsContent = true;
            if (partOfContent != null && !content.contains(partOfContent)) {
                containsContent = false;
            }
            if (containsContent) {
                paths.add(file.toString());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    /**
     * MyFileVisitorFiles
     */
    private  class MyFileVisitorFiles extends SimpleFileVisitor<Path> {
        String partOfName;

        /**
         * Constructor.
         * @param partOfName ends With.
         */
        public MyFileVisitorFiles(String partOfName) {
            this.partOfName = partOfName;
        }

        @Override
        public  FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            boolean containsName = true;
            if (partOfName != null && !file.toString().endsWith(partOfName)) {
                containsName = false;
            }
            synchronized (files) {
                if (containsName) {
                    files.add(file.toString());
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }
}