(ns advent-of-code-2023.day03-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day03 :refer :all]))

(def ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day03.txt")))

(deftest day03-1-example-test
  (testing "day03-1 example"
    (is (= nil
           (day03-1 example-input)))))

(deftest day03-1-test
  (testing "day03-1"
    (is (= nil
           (day03-1 input)))))

(deftest day03-2-example-test
  (testing "day03-2 example"
    (is (= nil
           (day03-2 example-input)))))

(deftest day03-2-test
  (testing "day03-2"
    (is (= nil
           (day03-2 input)))))
