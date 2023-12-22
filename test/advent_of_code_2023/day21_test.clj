(ns advent-of-code-2023.day21-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day21 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "...........
.....###.#.
.###.##..#.
..#.#...#..
....#.#....
.##..S####.
.##..#...#.
.......##..
.##.#.####.
.##..##.##.
..........."))

(def ^:private input (parse-input (tu/slurp-input "resources/day21.txt")))

(deftest day21-1-example-test
  (testing "day21-1 example"
    (is (= 16 (day21-1 example-input 6)))))

(deftest day21-1-test
  (testing "day21-1"
    (is (= 3733 (day21-1 input 64)))))

(deftest day21-2-test
  (testing "day21-2"
    (is (= 617729401414635 (day21-2 input 26501365)))))
