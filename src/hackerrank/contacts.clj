(ns hackerrank.contacts
  (:require [clojure.string :as str])
  (:gen-class))

(defn add-to-trie
  [trie word]
  (reduce (fn [trie prefix]
            (update-in trie [prefix :count] #((fnil inc 0) %)))
          trie
          (for [n (range (count word))]
            (.substring word 0 (inc n)))))

(defn prefix-count
  [trie word]
  (get-in trie [word :count] 0))

(defn solve
  [in]
  (let [count (Integer/parseInt (read-line))
        trie (atom {})]
    (dotimes [_ count]
      (let [line (read-line)
            args (str/split line #" ")
            [cmd word] args]
        (case cmd
          "add" (swap! trie add-to-trie word)
          "find" (println (prefix-count @trie word)))))))

;;(solve (java.io.BufferedReader. *in*))

(defn solve-test
  []
  (binding [*in* (clojure.java.io/reader
              "resources/input02.txt"
              ;;"resources/test-input.txt"
              )]
    (solve *in*)))
