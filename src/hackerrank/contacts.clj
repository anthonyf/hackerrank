(ns hackerrank.contacts)

(def count-index 26)
(def word-index 27)

(defn make-jtrie
  []
  (let [a (make-array java.lang.Object 28)]
    (aset a count-index 0)
    a))

(defn char->index
  [c]
  (- (int c)
     (int \a)))

(defn jtrie-lookup-prefix
  [trie prefix]
  (if (or (empty? prefix)
          (nil? trie))
    trie
    (let [index (char->index (first prefix))]
      (recur (aget trie index)
             (rest prefix)))))

(defn jtrie-word?
  [trie word]
  (let [trie (jtrie-lookup-prefix trie word)]
    (and (not (nil? trie))
         (not (nil? (aget trie word-index))))))

(defn add-to-jtrie
  [trie word]
  (when-not (jtrie-word? trie word) 
    (loop [trie trie
           word word]
      (aset trie count-index (inc (aget trie count-index)))
      (when-not (empty? word)      
        (let [[c remaining] word
              index (char->index c)]
          (when (nil? (aget trie index))
            (aset trie index (make-jtrie)))
          (recur (aget trie index)
                 (rest word)))))
    (aset (jtrie-lookup-prefix trie word)
        word-index true)
    nil))

(defn jtrie-prefix-count
  [trie prefix]
  (or (when-not (nil? trie)
        (let [trie (jtrie-lookup-prefix trie prefix)]
          (when-not (nil? trie)
            (aget trie count-index))))
      0))

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


;; (defn add-to-trie
;;   [trie word]
;;   (let [word (sequence word)]
;;     (if (nil? (get-in trie (conj (vec word) :word) nil))
;;       (let [trie (reduce (fn [trie keys]
;;                            (update-in trie (conj (vec keys) :count) #((fnil inc 0) %)))
;;                          trie
;;                          (let [keys word]
;;                            (for [n (range (count keys))]
;;                              (take (+ 1 n) keys))))]
;;         (assoc-in trie word
;;                   (merge (get-in trie word)
;;                          {:word true})))
;;       trie)))

;; (defn prefix-count
;;   [trie word]
;;   (or (:count (get-in trie (sequence word)))
;;       0))


