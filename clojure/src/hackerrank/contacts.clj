(ns hackerrank.contacts
  (:require [clojure.string :as str])
  (:gen-class))

(defn add-to-trie
  [trie word]
  (reduce (fn [trie prefix]
            (update-in trie [prefix :count] #((fnil inc 0) %)))
          trie
          (for [n (range 1 (inc (count word)))]
            (.substring #^String word 0 n))))

(defn prefix-count
  [trie word]
  (get-in trie [word :count] 0))

(defn solve
  [in]
  (let [lines (line-seq in)
        line-count (Integer/parseInt (first lines))
        lines (take line-count (rest lines))]
    (->> lines
         (reduce (fn [trie line]
                   (let [[cmd word] (str/split line #"\s+")]
                     (case cmd
                       "add" (add-to-trie trie word)
                       "find" (do (println (prefix-count trie word))
                                  trie))))
                 {}))
    (flush)
    nil))


;;(solve (java.io.BufferedReader. *in*))


