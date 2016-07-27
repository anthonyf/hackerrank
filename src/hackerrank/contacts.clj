(ns hackerrank.contacts)

(defn add-to-trie
  [trie word]
  (let []
    (if (nil? (get-in trie [word :word] nil))
      (let [trie (reduce (fn [trie prefix]
                           (update-in trie [prefix :count] #((fnil inc 0) %)))
                         trie
                         (for [n (range (count word))]
                             (.substring word 0 (inc n))))]
        (assoc trie word
               (merge (get trie word)
                      {:word word})))
      trie)))

(defn prefix-count
  [trie word]
  (get-in trie [word :count] 0))

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
      ;;(flush)
      )))

;;(solve (java.io.BufferedReader. *in*))

(defn solve-test
  []
  (solve (clojure.java.io/reader
          "resources/input02.txt"
          ;;"resources/test-input.txt"
          )))
