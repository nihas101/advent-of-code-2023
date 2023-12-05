(ns advent-of-code-2023.day01-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day01 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(def ^:private example-input "1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet")

(def ^:private input (tu/slurp-input "resources/day01.txt"))

(deftest day01-1-example-test
  (testing "day01-1 example"
    (is (= 142 (day01-1 example-input)))))

(deftest day01-1-test
  (testing "day01-1"
    (is (= 56397 (day01-1 input)))))

(def ^:private example-input-2 "two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen")

(deftest day01-2-example-test
  (testing "day01-2 example"
    (is (= 281 (day01-2 example-input-2)))))

(deftest day01-2-test
  (testing "day01-2"
    (is (= 55701 (day01-2 input)))))
