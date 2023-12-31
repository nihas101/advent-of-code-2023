(ns advent-of-code-2023.day14-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day14 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "O....#....
O.OO#....#
.....##...
OO.#O....O
.O.....O#.
O.#..O.#.#
..O..#O..O
.......O..
#....###..
#OO..#...."))

(def ^:private input (parse-input (tu/slurp-input "resources/day14.txt")))

(deftest day14-1-example-test
  (testing "day14-1 example"
    (is (= 136 (day14-1 example-input)))))

(deftest day14-1-test
  (testing "day14-1"
    (is (= 110128 (day14-1 input)))))

(deftest day14-2-example-test
  (testing "day14-2 example"
    (is (= 64 (day14-2 example-input)))))

(deftest day14-2-test
  (testing "day14-2"
    (is (= 103861 (day14-2 input)))))
