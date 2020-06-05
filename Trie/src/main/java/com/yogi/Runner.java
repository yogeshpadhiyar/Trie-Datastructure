package com.yogi;

public class Runner {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cat");
        System.out.println("---------------add new 1...");
        trie.insert("caw");
        System.out.println("---------------add new 2...");
        trie.insert("tea");
        System.out.println("---------------add new 3...");
        trie.insert("tot");
        System.out.println("---------------add new 4...");
        trie.insert("dadi");
        System.out.println("---------------add new 5...");
        trie.insert("dada");
        trie.insert("dadu");
        trie.insert("dade");

        trie.search("tea");
        System.out.println();
        trie.search("caw");
        System.out.println();
        trie.search("to");
        System.out.println();
        trie.search("tot");
        System.out.println();
        trie.search("dadi");

        trie.nextPossible("dadu");
        trie.nextPossible("t");
        trie.nextPossible("c");
    }
}
