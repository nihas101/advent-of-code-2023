(ns advent-of-code-2023.day07-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day07 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(def ^:private example-input (parse-input "32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"))

(def ^:private input (parse-input (tu/slurp-input "resources/day07.txt")))

(deftest day07-1-example-test
  (testing "day07-1 example"
    (is (= 6440 (day07-1 example-input)))))

(deftest day07-1-test
  (testing "day07-1"
    (is (= 246163188 (day07-1 input)))))

(deftest day07-2-example-test
  (testing "day07-2 example"
    (is (= 5905 (day07-2 example-input)))))

(deftest day07-2-test
  (testing "day07-2"
    (is (= 245794069 (day07-2 input)))))
