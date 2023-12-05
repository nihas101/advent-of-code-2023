(ns advent-of-code-2023.day02-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day02 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(def ^:private example-input (parse-input "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"))

(def ^:private input (parse-input (tu/slurp-input "resources/day02.txt")))

(deftest day02-1-example-test
  (testing "day02-1 example"
    (is (= 8 (day02-1 example-input)))))

(deftest day02-1-test
  (testing "day02-1"
    (is (= 2727 (day02-1 input)))))

(deftest day02-2-example-test
  (testing "day02-2 example"
    (is (= 2286 (day02-2 example-input)))))

(deftest day02-2-test
  (testing "day02-2"
    (is (= 56580 (day02-2 input)))))
