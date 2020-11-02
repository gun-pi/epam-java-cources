package com.epam.university.java.core.task039;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Task039Impl implements Task039 {
    private Node rootTemp;

    @Override
    public Map<Character, String> getEncoding(Map<Character, Integer> charFrequencies) {
        return getEncoding(charFrequencies, new NodeAnotherComparator());
    }

    /**
     * Overrided method with option to use comparator.
     * @param charFrequencies map of chars and their frequencies.
     * @param comparator comparator.
     * @return map of chars and their codes.
     */
    public Map<Character, String> getEncoding(Map<Character, Integer> charFrequencies,
                                              Comparator<Node> comparator) {
        Queue<Node> queue = new PriorityQueue<>(charFrequencies.size(), comparator);

        for (Map.Entry<Character, Integer> entry : charFrequencies.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            Node first = queue.poll();
            Node second = queue.poll();

            assert first != null;
            assert second != null;
            Node node = new Node(null, first.getValue() + second.getValue());
            node.setLeft(first);
            node.setRight(second);

            queue.add(node);
        }

        Node left = queue.poll();
        Node right = queue.poll();

        Node root;
        if (left != null && right != null) {
            root = new Node(null, left.getValue() + right.getValue());
            root.setLeft(left);
            root.setRight(right);
        } else if (left == null) {
            root = right;
        } else {
            root = left;
        }

        if (comparator instanceof NodeAnotherComparator) {
            rootTemp = root;
        }

        return getCodedCharacterMap(new ArrayList<>(charFrequencies.keySet()), root, "");
    }


    @Override
    public String getEncodedString(Map<Character, Integer> charFrequencies, String string) {
        Map<Character, String> stringMap = getEncoding(charFrequencies,
                new NodeAnotherComparator());
        char[] encoded = string.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : encoded) {
            sb.append(stringMap.get(c));
        }
        return sb.toString();
    }

    @Override
    public String getDecodedString(Map<Character, Integer> charFrequencies, String encodedString) {
        Map<Character, String> stringMap = getEncoding(charFrequencies,
                new NodeAnotherComparator());
        StringBuilder decoded = new StringBuilder();

        Node curr = rootTemp;

        for (int i = 0; i < encodedString.length(); i++) {
            if (encodedString.charAt(i) == '0') {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }

            Node left = curr.getLeft();
            Node right = curr.getRight();

            if (left == null && right == null) {
                decoded.append(curr.getValueChar());
                curr = rootTemp;
            }
        }

        return decoded.toString();
    }

    private Map<Character, String> getCodedCharacterMap(List<Character> characters,
                                                        Node root, String s) {
        StringBuilder code = new StringBuilder(s);
        Map<Character, String> resultMap = new HashMap<>();

        if (root.getLeft() == null && root.getRight() == null
                && characters.contains(root.getValueChar())) {
            resultMap.put(root.getValueChar(), code.toString());
            return resultMap;
        }

        resultMap.putAll(getCodedCharacterMap(characters, root.getLeft(), s + "0"));
        resultMap.putAll(getCodedCharacterMap(characters, root.getRight(), s + "1"));
        return resultMap;
    }

    public static class Node {
        private final int value;
        private final Character valueChar;
        private Node left;
        private Node right;

        /**
         * Constructor of node object.
         * @param valueChar character value.
         * @param value integer weight.
         */
        public Node(Character valueChar, int value) {
            this.value = value;
            this.valueChar = valueChar;
            this.left = null;
            this.right = null;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Character getValueChar() {
            return valueChar;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.getValue() == o2.getValue()
                    && o1.getValueChar() != null
                    && o2.getValueChar() != null) {
                return o2.getValueChar() - o1.getValueChar();
            } else if (o1.getValue() == o2.getValue() && o1.getValueChar() == null) {
                return -1;
            } else if (o1.getValue() == o2.getValue() && o2.getValueChar() == null) {
                return 1;
            } else {
                return o1.getValue() - o2.getValue();
            }
        }
    }

    public static class NodeAnotherComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.getValue() == o2.getValue()
                    && o1.getValueChar() != null
                    && o2.getValueChar() != null) {
                return o1.getValueChar() - o2.getValueChar();
            } else if (o1.getValue() == o2.getValue() && o1.getValueChar() == null) {
                return -1;
            } else if (o1.getValue() == o2.getValue() && o2.getValueChar() == null) {
                return 1;
            } else {
                return o1.getValue() - o2.getValue();
            }
        }
    }
}
