package expression.exceptions;

import java.util.*;

public class Trie {
    static class Node {
        Map<Character, Node> nodes;
        private Node() {
            nodes = new TreeMap<>();
        }
    }
    private final Node root;
    private Node userNode;
    private Set<String> strings;
    public Trie(Set<String> strings) {
        this.strings = strings;
        root = new Node();
        for (String s : strings) {
            Node step = root;
            resetToRoot();
            for (char c : s.toCharArray()) {
                Node newNode = step.nodes.get(c);
                if (newNode == null) {
                    step.nodes.put(c, new Node());
                    step = step.nodes.get(c);
                } else {
                    step = newNode;
                }
            }
        }
    }
    public boolean checkString(String s) {
        Node step = root;
        for (char c : s.toCharArray()) {
            Node newNode = step.nodes.get(c);
            if (newNode == null) {
                return false;
            }
            step = newNode;
        }
        return true;
    }

    public boolean goTo(char c) {
        Node tryTo = userNode.nodes.get(c);
        if (tryTo == null) {
            return false;
        }
        userNode = tryTo;
        return true;
    }
    public void resetToRoot() {
        userNode = root;
    }
    public Set<String> getStrings() {
        return strings;
    }
}
