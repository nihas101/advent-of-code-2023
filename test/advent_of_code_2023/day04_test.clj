(ns advent-of-code-2023.day04-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day04 :refer :all]))

(def ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day04.txt")))

(deftest day04-1-example-test
  (testing "day04-1 example"
    (is (= nil
           (day04-1 example-input)))))

(deftest day04-1-test
  (testing "day04-1"
    (is (= nil
           (day04-1 input)))))

(deftest day04-2-example-test
  (testing "day04-2 example"
    (is (= nil
           (day04-2 example-input)))))

(deftest day04-2-test
  (testing "day04-2"
    (is (= nil
           (day04-2 input)))))
