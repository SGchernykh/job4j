package ru.job4j.additionally;
/**
 * Prefix Tree.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.io.*;
import java.util.*;

public class PrefixTree {
    private int ind = -1;
    private TrieNode root = new TrieNode();

    /**
     * Put
     * @param word Word
     */
    public void put(String word) {
        ind++;
        TrieNode temp = root;
        for (char ch : word.toCharArray()) {
            if (!temp.children.containsKey(ch)) {
                temp.children.put(ch, new TrieNode());
                temp.state = true;
            }
            temp = temp.children.get(ch);
            temp.index.add(ind++);
        }
    }

    /**
     * Find index word.
     * @param searchWord Search Word.
     * @return Set index.
     */
 public Set<Integer> getIndexes4Word(String searchWord) {
        Queue<TrieNode> queue = new LinkedList<>();
        boolean result = false;
        Set<Integer> set = new HashSet<>();
        TrieNode temp = new TrieNode();
        temp.children.put(' ', root);
        queue.addAll(temp.children.values());
        while (!queue.isEmpty()) {
            temp = queue.poll();
            for (char ch : searchWord.toLowerCase().toCharArray()) {
                queue.addAll(temp.children.values());
                if (!temp.children.containsKey(ch)) {
                    result = false;
                    queue.addAll(temp.children.values());
                    break;
                } else {
                    temp = temp.children.get(ch);
                }
                result = true;
            }
            if (result) {
                for (int ind : temp.index) {
                    set.add(ind - (searchWord.length() - 1));
                }
            }
        }
    return set;
    }

    /**
     * Download string.
     * @param filename Location file.
     * @return List word.
     */
    public List<String> loadFile(String filename) {
        BufferedReader readFromFile = null;
        String line = "";
        List<String> list = new ArrayList<>();
        try {
            readFromFile = new BufferedReader(new FileReader(filename));
            while ((line = readFromFile.readLine()) != null) {
                for (String st : (line.split(" "))) {
                    list.add(st);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * Trie Node
     */
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        List<Integer> index = new LinkedList<>();
        boolean state = false;
    }
}