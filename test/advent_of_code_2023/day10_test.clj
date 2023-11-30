(ns advent-of-code-2023.day10-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day10 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day10.txt")))

(deftest day10-1-example-test
  (testing "day10-1 example"
    (is (= nil
           (day10-1 example-input)))))

(deftest day10-1-test
  (testing "day10-1"
    (is (= nil
           (day10-1 input)))))

(deftest day10-2-example-test
  (testing "day10-2 example"
    (is (= nil
           (day10-2 example-input)))))

(deftest day10-2-test
  (testing "day10-2"
    (is (= nil
           (day10-2 input)))))
