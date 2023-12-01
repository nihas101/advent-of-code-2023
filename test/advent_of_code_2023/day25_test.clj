(ns advent-of-code-2023.day25-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day25 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day25.txt")))

(deftest day25-example-test
  (testing "day25 example"
    (is (= nil
           (day25 example-input)))))

(deftest day25-test
  (testing "day25"
    (is (= nil
           (day25 input)))))
