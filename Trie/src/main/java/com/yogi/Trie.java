package com.yogi;

import java.util.HashSet;
import java.util.Set;

public class Trie {
    static class Node {
        public Node(String value) {
            this.value = value;
        }

        private String value;
        private Set<Node> childNode = new HashSet<>();

        public boolean isEndPoint() {
            return endPoint;
        }

        public void setEndPoint(boolean endPoint) {
            this.endPoint = endPoint;
        }

        private boolean endPoint;

        private Boolean isContains(Node root, String c) {
            return root.childNode.stream().anyMatch(e -> e.value.equals(c));
        }

        private Node addChild(Node node , String word){
            Node node1 = new Node(word);
            node.childNode.add(node1);
            System.out.println(" node value " + node1.value + " is insert.");
            return node1;
        }

        private Node getChild(Node node , String word){
            return node.childNode.stream().filter(e->e.value.equals(word)).findFirst().orElse(null);
        }
    }

    private Node root = new Node(" ");

    public void insert(String word) {
        Node temp = root;
        for (int i = 1; i <=word.length(); i++) {
            temp = insert(word.substring(0, i), temp);
        }
        temp.setEndPoint(true);
    }

    private Node insert(String word, Node node) {
        Node child = null;
        boolean flag=true;
        if (!word.isEmpty()) {
            if (!node.childNode.isEmpty()) {
                if (node.isContains(node, word)) {
                    for (Node cNode : node.childNode) {
                        if (word.equals(cNode.value)) {
                            child = cNode;
                            break;
                        }
                    }
                }else {
                    flag=false;
                }
            } else {
                child = node.addChild(node,word);
            }
        }
        if(!flag){
            child = node.addChild(node,word);
        }
        return child;
    }

    public void search(String word) {
        Node temp = root;
        boolean flag=true;
        for (int i=1; i <=word.length(); i++) {
            if(temp!=null) {
                temp = search(word.substring(0, i), temp);
            }else{
                flag=false;
                break;
            }
        }
        if (flag && temp!=null && temp.isEndPoint()) {
            System.out.println(word + " is Present.");
        } else {
            System.out.println(word+" is Not Present.");
        }
    }

    private Node search(String word, Node node) {
        Node child=null;
        if (!word.isEmpty()) {
            if(!node.childNode.isEmpty()) {
                if(node.isContains(node,word)) {
                    for (Node cNode : node.childNode) {
                        if (word.equals(cNode.value)) {
                            child=cNode;
                        }
                    }
                }
            }
        }
        return child;
    }

    public void nextPossible(String word){
        Node temp=root;
        Node internalNode;
        for (int i = 1; i <=word.length(); i++) {
            if(temp!=null) {
                temp = search(word.substring(0,i), temp);
            }
        }
        if(temp!=null && !temp.isEndPoint()) {
        Set<String> nextPossibleWord = new HashSet<>();
            internalNode = temp;
            for (Node node : internalNode.childNode) {
                nextPossibleWord.addAll(findWord(node));
            }

            nextPossibleWord.forEach(System.out::println);
        }else {
            if(temp.isEndPoint()){
                System.out.println(word+" has not next possible.");
            }else {
                System.out.println(word + " is not present.");
            }
        }

    }

    private Set<String> findWord(Node internalNode){
        Set<String> nextPossibleWord = new HashSet<>();
        if(internalNode.isEndPoint()){
            nextPossibleWord.add(internalNode.value);
        }
        for (Node node : internalNode.childNode) {
            nextPossibleWord.addAll(findWord(node));
        }
        return nextPossibleWord;
    }

}
