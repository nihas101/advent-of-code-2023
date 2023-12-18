(ns advent-of-code-2023.day18-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day18 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "R 6 (#70c710)
D 5 (#0dc571)
L 2 (#5713f0)
D 2 (#d2c081)
R 2 (#59c680)
D 2 (#411b91)
L 5 (#8ceee2)
U 2 (#caa173)
L 1 (#1b58a2)
U 2 (#caa171)
R 2 (#7807d2)
U 3 (#a77fa3)
L 2 (#015232)
U 2 (#7a21e3)"))

(def ^:private input (parse-input (tu/slurp-input "resources/day18.txt")))

(deftest day18-1-example-test
  (testing "day18-1 example"
    (is (= 62 (day18-1 example-input)))))

(deftest day18-1-test
  (testing "day18-1"
    (is (= 47675 (day18-1 input)))))

(deftest day18-2-example-test
  (testing "day18-2 example"
    (is (= 952408144115 (day18-2 example-input)))))

(deftest day18-2-test
  (testing "day18-2"
    (is (= 122103860427465 (day18-2 input)))))
