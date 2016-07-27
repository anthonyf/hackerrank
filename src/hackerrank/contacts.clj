(ns hackerrank.contacts)

(defn add-to-trie
  [trie word]
  (reduce (fn [trie keys]
            (update-in trie (conj (vec keys) :count) #((fnil inc 0) %)))
          trie
          (let [keys (sequence word)]
            (for [n (range (count keys))]
              (take (+ 1 n) keys)))))

(defn prefix-count
  [trie word]
  (or (:count (get-in trie (sequence word)))
      0))

(defn process-line
  [trie line]
  (let [args (read-string (str \( line \)))]
    (when (= 2 (count args))
      (let [[cmd word] args
            word (str word)]
        (case cmd
          add (do (swap! trie add-to-trie word)
                  nil)
          find (prefix-count @trie word))))))

(defn solve
  [in]
  (let [trie (atom {})
        process (comp
                 (map #(process-line trie %))
                 (filter #(not (nil? %))))
        col (sequence process (line-seq in))]
    (doseq [x col]
      (println x)
      (flush))))

;;(solve (java.io.BufferedReader. *in*))

(defn solve-test
  []
  (solve (clojure.java.io/reader "resources/input02.txt")))



