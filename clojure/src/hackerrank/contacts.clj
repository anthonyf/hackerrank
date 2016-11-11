(ns hackerrank.contacts
  (:require [clojure.string :as str])
  (:gen-class))

(defn add-to-trie
  [trie word]
  (reduce (fn [trie prefix]
            (update-in trie [prefix :count] #((fnil inc 0) %)))
          trie
          (for [n (range 1 (inc (count word)))]
            (.substring word 0 n))))

(defn prefix-count
  [trie word]
  (get-in trie [word :count] 0))

(defn solve
  [in]
  (let [line-count (Integer/parseInt (read-line))]
    (reduce (fn [trie n]
              (let [line (read-line)
                    [cmd word] (str/split line #"\s+")]
                (case cmd
                  "add" (add-to-trie trie word)
                  "find" (do (println (prefix-count trie word))
                             trie))))
            {}
            (range line-count))
    nil))


;;(solve (java.io.BufferedReader. *in*))

(defn solve-test
  []
  (binding [*in* (clojure.java.io/reader
              "resources/input02.txt"
              ;;"resources/test-input.txt"
              )]
    (solve *in*)))
