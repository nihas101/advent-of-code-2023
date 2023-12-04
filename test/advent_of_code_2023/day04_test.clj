(ns advent-of-code-2023.day04-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day04 :refer :all]))

(def ^:private example-input (parse-input "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"))

(def ^:private input (parse-input (slurp "resources/day04.txt")))

(deftest day04-1-example-test
  (testing "day04-1 example"
    (is (= 13 (day04-1 example-input)))))

(deftest day04-1-test
  (testing "day04-1"
    (is (= 25231 (day04-1 input)))))

(deftest day04-2-example-test
  (testing "day04-2 example"
    (is (= 30 (day04-2 example-input)))))

(deftest day04-2-test
  (testing "day04-2"
    (is (= 9721255 (day04-2 input)))))
