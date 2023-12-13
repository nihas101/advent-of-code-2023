(ns advent-of-code-2023.day13-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day13 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "#.##..##.
..#.##.#.
##......#
##......#
..#.##.#.
..##..##.
#.#.##.#.

#...##..#
#....#..#
..##..###
#####.##.
#####.##.
..##..###
#....#..#"))

(def ^:private input (parse-input (tu/slurp-input "resources/day13.txt")))

(deftest day13-1-example-test
  (testing "day13-1 example"
    (is (= 405 (day13-1 example-input)))))

(deftest day13-1-test
  (testing "day13-1 example"
    (is (= 36041 (day13-1 input)))))

(deftest day13-2-example-test
  (testing "day13-2 example"
    (is (= 400 (day13-2 example-input)))))

(deftest day13-2-test
  (testing "day13-2 example"
    (is (= 35915 (day13-2 input)))))