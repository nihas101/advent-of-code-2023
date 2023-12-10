(ns advent-of-code-2023.day10-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day10 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input ".....
.S-7.
.|.|.
.L-J.
....."))

(defonce ^:private example-input-2 (parse-input "..F7.
.FJ|.
SJ.L7
|F--J
LJ..."))

(def ^:private input (parse-input (tu/slurp-input "resources/day10.txt")))

(deftest day10-1-example-test
  (testing "day10-1 example"
    (is (= 4 (day10-1 example-input)))))

(deftest day10-1-example-2-test
  (testing "day10-1 example 2"
    (is (= 8 (day10-1 example-input-2)))))

(deftest day10-1-test
  (testing "day10-1"
    (is (= 6968 (day10-1 input)))))

(deftest day10-2-example-test
  (testing "day10-2 example"
    (is (= 1 (day10-2 example-input)))))

(defonce ^:private example-input-3 (parse-input "...........
.S-------7.
.|F-----7|.
.||.....||.
.||.....||.
.|L-7.F-J|.
.|..|.|..|.
.L--J.L--J.
..........."))

(deftest day10-2-example-3-test
  (testing "day10-2 example 3"
    (is (= 4 (day10-2 example-input-3)))))

(defonce ^:private example-input-4 (parse-input "FF7FSF7F7F7F7F7F---7
L|LJ||||||||||||F--J
FL-7LJLJ||||||LJL-77
F--JF--7||LJLJ7F7FJ-
L---JF-JLJ.||-FJLJJ7
|F|F-JF---7F7-L7L|7|
|FFJF7L7F-JF7|JL---7
7-L-JL7||F7|L7F-7F7|
L.L7LFJ|||||FJL7||LJ
L7JLJL-JLJLJL--JLJ.L"))

(deftest day10-2-example-4-test
  (testing "day10-2 example 4"
    (is (= 10 (day10-2 example-input-4)))))

(deftest day10-2-test
  (testing "day10-2"
    (is (= 413 (day10-2 input)))))
