(ns hackerrank.coin-change
  (require [clojure.string :as str]))

(def coin-change
  (memoize
   (fn    
     [m n]
     (->> m
          (map-indexed vector)
          (reduce (fn [acc [i c]]
                    (let [diff (- n c)]
                      (cond (zero? diff) (+ acc 1)
                            (> diff 0) (+ acc (coin-change (drop i m) diff))
                            :else acc)))
                  0)))))

#_(coin-change [1 2 3] 4)
;; => 4


(defn solve
  [in]
  (let [lines (line-seq in)]
    (doseq [s (->> lines
                   ;; split lines into groups of two
                   (reduce (fn [acc line]
                             (if (or (empty? acc)
                                     (= 2 (count (last acc))))
                               (conj acc [line])
                               (assoc acc (dec (count acc)) [(first (last acc)) line])))
                           [])
                   ;; extract values
                   (reduce (fn [acc [line1 line2]]
                             (let [[n m] (str/split line1 #"\s+")
                                   [n m] [(Integer/parseInt n) (Integer/parseInt m)]
                                   m (map (fn [n] (Integer/parseInt n))
                                          (str/split line2 #"\s+"))]
                               (conj acc [m n])))
                           [])
                   (map (fn [[m n]] (coin-change m n))))]
      
      (println s))
    (flush)
    nil))

#_ (with-in-str "4 3
1 2 3"
     (solve (java.io.BufferedReader. *in*)))

#_ (with-in-str "10 4
2 5 3 6"
     (solve (java.io.BufferedReader. *in*)))

;;(solve (java.io.BufferedReader. *in*))
