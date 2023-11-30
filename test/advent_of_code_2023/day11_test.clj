(ns advent-of-code-2023.day11-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day11 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day11.txt")))

(deftest day11-1-example-test
  (testing "day11-1 example"
    (is (= nil
           (day11-1 example-input)))))

(deftest day11-1-test
  (testing "day11-1"
    (is (= nil
           (day11-1 input)))))

(deftest day11-2-example-test
  (testing "day11-2 example"
    (is (= nil
           (day11-2 example-input)))))

(deftest day11-2-test
  (testing "day11-2"
    (is (= nil
           (day11-2 input)))))
