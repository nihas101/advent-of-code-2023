(ns advent-of-code-2023.day11-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day11 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#....."))

(def ^:private input (parse-input (tu/slurp-input "resources/day11.txt")))

(deftest day11-1-example-test
  (testing "day11-1 example"
    (is (= 374 (day11 example-input)))))

(deftest day11-1-test
  (testing "day11-1"
    (is (= 9556896 (day11 input)))))

(deftest day11-2-example-10-test
  (testing "day11-2 example 10"
    (is (= 1030 (day11 example-input 10)))))

(deftest day11-2-example-100-test
  (testing "day11-2 example 100"
    (is (= 8410 (day11 example-input 100)))))

(deftest day11-2-test
  (testing "day11-2"
    (is (= 685038186836 (day11 input 1000000)))))
