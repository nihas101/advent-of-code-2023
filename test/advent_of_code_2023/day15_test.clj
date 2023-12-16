(ns advent-of-code-2023.day15-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day15 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-ascii "HASH")

(defonce ^:private example-input 
  (parse-input "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"))

(def ^:private input (parse-input (tu/slurp-input "resources/day15.txt")))

(deftest day15-1-example-ascii-test
  (testing "day15-1 example ascii"
    (is (= 52 (day15-1 [[example-ascii nil]])))))

(deftest day15-1-example-test
  (testing "day15-1 example"
    (is (= 1320 (day15-1 example-input)))))

(deftest day15-1-test
  (testing "day15-1"
    (is (= 504449 (day15-1 input)))))

(deftest day15-2-example-test
  (testing "day15-2 example"
    (is (= 145 (day15-2 example-input)))))

(deftest day15-2-test
  (testing "day15-2"
    (is (= 262044 (day15-2 input)))))
