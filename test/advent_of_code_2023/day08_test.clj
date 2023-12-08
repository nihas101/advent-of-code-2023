(ns advent-of-code-2023.day08-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day08 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(def ^:private example-input (parse-input "RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)"))

(def ^:private example-input-2 (parse-input "LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)"))

(def ^:private input (parse-input (tu/slurp-input "resources/day08.txt")))

(deftest day08-1-example-test
  (testing "day08-1 example 1"
    (is (= 2 (day08-1 example-input)))))

(deftest day08-1-example-2-test
  (testing "day08-1 example 2"
    (is (= 6 (day08-1 example-input-2)))))

(deftest day08-1-test
  (testing "day08-1"
    (is (= 13019 (day08-1 input)))))

(def ^:private example-input-3 (parse-input "LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)"))

(deftest day08-2-example-3-test
  (testing "day08-2 example 3"
    (is (= 6 (day08-2 example-input-3)))))

(deftest day08-2-test
  (testing "day08-2"
    (is (= 13524038372771 (day08-2 input)))))
