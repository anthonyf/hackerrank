(ns hackerrank.contacts
  (:require [clojure.string :as str])
  (:gen-class))

(defn make-jtrie
  []
  (java.util.HashMap.))

(defn add-to-jtrie
  [trie word]
  (loop [word word]
    (when-not (empty? word)
      (.put trie word
            (inc (or (.get trie word) 0)))
      (recur (.substring word 0 (dec (count word))))))
  nil)

(defn jtrie-prefix-count
  [trie prefix]
  (or (.get trie prefix)
      0))

(defn solve
  [in]
  (let [count (Integer/parseInt (read-line))
        trie (make-jtrie)]
    (dotimes [_ count]
      (let [line (read-line)
            args (str/split line #" ")
            [cmd word] args]
        (case cmd
          "add" (add-to-jtrie trie word)
          "find" (println (jtrie-prefix-count trie word)))))))

;;(solve (java.io.BufferedReader. *in*))

(defn solve-test
  []
  (binding [*in* (clojure.java.io/reader
              "resources/input02.txt"
              ;;"resources/test-input.txt"
              )]
    (solve *in*)))
