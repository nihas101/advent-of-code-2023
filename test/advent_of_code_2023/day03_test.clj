(ns advent-of-code-2023.day03-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day03 :refer :all]))

(def ^:private example-input (parse-input "467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598.."))

(def ^:private input (parse-input (slurp "resources/day03.txt")))

(deftest day03-1-example-test
  (testing "day03-1 example"
    (is (= 4361 (day03-1 example-input)))))

(deftest day03-1-test
  (testing "day03-1"
    (is (= 532331 (day03-1 input)))))

(deftest day03-2-example-test
  (testing "day03-2 example"
    (is (= 467835 (day03-2 example-input)))))

(deftest day03-2-test
  (testing "day03-2"
    (is (= 82301120 (day03-2 input)))))
