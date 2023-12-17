(ns advent-of-code-2023.day17-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day17 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "2413432311323
3215453535623
3255245654254
3446585845452
4546657867536
1438598798454
4457876987766
3637877979653
4654967986887
4564679986453
1224686865563
2546548887735
4322674655533"))

(defonce ^:private example-input-2 (parse-input "111111111111
999999999991
999999999991
999999999991
999999999991"))


(def ^:private input (parse-input (tu/slurp-input "resources/day17.txt")))

(deftest day17-1-example-test
  (testing "day17-1 example"
    (is (= 102 (day17-1 example-input)))))

(deftest day17-1-test
  (testing "day17-1"
    (is (= 847 (day17-1 input)))))

(deftest day17-2-example-test
  (testing "day17-2 example"
    (is (= 94 (day17-2 example-input)))))

(deftest day17-2-example-2-test
  (testing "day17-2 example 2"
    (is (= 71 (day17-2 example-input-2)))))

(deftest day17-2-test
  (testing "day17-2"
    (is (= 997 (day17-2 input)))))
