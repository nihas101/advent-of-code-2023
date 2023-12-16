(ns advent-of-code-2023.day16-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day16 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input ".|...\\....
|.-.\\.....
.....|-...
........|.
..........
.........\\
..../.\\\\..
.-.-/..|..
.|....-|.\\
..//.|...."))

(def ^:private input (parse-input (tu/slurp-input "resources/day16.txt")))

(deftest day16-1-example-test
  (testing "day16-1 example"
    (is (= 46 (day16-1 example-input)))))

(deftest day16-1-test
  (testing "day16-1"
    (is (= 6855 (day16-1 input)))))

(deftest day16-2-example-test
  (testing "day16-2 example"
    (is (= 51 (day16-2 example-input)))))

(deftest day16-2-test
  (testing "day16-2"
    (is (= 7513 (day16-2 input)))))
