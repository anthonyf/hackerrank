(ns hackerrank.contacts)

(defn make-jtrie
  []
  (java.util.HashMap.))

(defn jtrie-lookup-prefix
  [trie prefix]
  (.get trie prefix))

(defn jtrie-word?
  [trie word]
  (let [val (or (jtrie-lookup-prefix trie word)
                {})]
    (not (nil? (:word val)))))

(defn add-to-jtrie
  [trie word]
  (when-not (jtrie-word? trie word) 
    (loop [word word]
      (when-not (empty? word)
        (if (nil? (.get trie word))
          (.put trie word {:count 1})
          (let [node (.get trie word)]
            (.put trie word
                  (assoc node :count (inc (:count node))))))
        (recur (.substring word 0 (dec (count word))))))
    (.put trie word (assoc (.get trie word)
                           :word word))
    nil))

(defn jtrie-prefix-count
  [trie prefix]
  (get (or (jtrie-lookup-prefix trie prefix)
           {}) :count 0))

(defn process-line
  [trie line]
  (let [args (read-string (str \( line \)))]
    (when (= 2 (count args))
      (let [[cmd word] args
            word (str word)]
        (case cmd
          add (do (add-to-jtrie trie word)
                  nil)
          find (jtrie-prefix-count trie word))))))

(defn solve
  [in]
  (let [trie (make-jtrie)
        process (comp
                 (map #(process-line trie %))
                 (filter #(not (nil? %))))
        col (sequence process (line-seq in))
        *flush-on-newline* false]
    (doseq [x col]
      (println x))))

;;(solve (java.io.BufferedReader. *in*))

(defn solve-test
  []
  (solve (clojure.java.io/reader
          "resources/input02.txt"
          ;;"resources/test-input.txt"
          )))
