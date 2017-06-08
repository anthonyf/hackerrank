(ns hackerrank.contacts-test
  (:require [hackerrank.contacts :as c]
            [clojure.test :refer :all]))

(defn test-solve
  [input-file expected-output-file]
  (let [actual-file "resources/actual.txt"]
    (with-open [out (clojure.java.io/writer actual-file)]
      (binding [*out* out]
        (c/solve (clojure.java.io/reader input-file))))
    (= (-> actual-file
           slurp
           (clojure.string/replace "\r\n" "\n")
           clojure.string/trim)
       (-> expected-output-file
           slurp
           clojure.string/trim))))

(deftest solve-sample-input-test
  (testing "sample input"
    (is (test-solve "resources/test-input.txt"
                    "resources/test-output.txt"))))

(deftest solve-02-test
  (testing "solve-02"
    (is (test-solve "resources/input02.txt"
                    "resources/output02.txt"))))

(deftest solve-04-test
  (time (testing "solve-04"
          (is (test-solve "resources/input04.txt"
                          "resources/output04.txt")))))

