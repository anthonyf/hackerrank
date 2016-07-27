(ns hackerrank.core
  (:require [hackerrank.contacts :as contacts])
  (:gen-class))

(defn -main
  [& args]
  (contacts/solve-test))
