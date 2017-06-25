(ns hackerrank.ctci-find-the-running-median
  (:require [clojure.data.priority-map :as pm]))

(defn medians
  [ints]
  (let [min-q (pm/priority-map-by <)
        max-q (pm/priority-map-by >=)]
    (->> ints
         (reduce (fn [[[max-q min-q] medians :as acc] n]
                   (->> acc
                        ((fn [[[max-q min-q] medians]]
                           [[(assoc max-q n n) min-q] medians]))
                        ((fn [[[max-q min-q] medians]]
                           [(if (> (dec (count max-q))
                                   (count min-q))
                              (let [[n n] (peek max-q)]
                                [(pop max-q) (assoc min-q n n)])
                              [max-q min-q])
                            medians]))
                        ((fn [[[max-q min-q] medians]]
                           [[max-q min-q] (conj medians
                                                (float (if (> (count max-q)
                                                              (count min-q))
                                                         (first (peek max-q))
                                                         (/ (+ (first (peek max-q))
                                                               (first (peek min-q)))
                                                            2))))]))))
                 [[min-q max-q] []])
         second)))

(defn solve
  []
  (let [n (-> (read-line)
              read-string)]
    (doseq [n (->> (range n)
                   (map (fn [n]
                          (let [i (-> (read-line)
                                      read-string)]
                            i)))
                   medians)]
      (println n))))


#_ (with-out-str
     (with-in-str "6
12
4
5
3
8
7"
       (solve)))
;; => "12.0\r\n8.0\r\n5.0\r\n4.5\r\n5.0\r\n6.0\r\n"

#_ (solve)

