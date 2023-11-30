(ns advent-of-code-2023.day15-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day15 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day15.txt")))

(deftest day15-1-example-test
  (testing "day15-1 example"
    (is (= nil
           (day15-1 example-input)))))

(deftest day15-1-test
  (testing "day15-1"
    (is (= nil
           (day15-1 input)))))

(deftest day15-2-example-test
  (testing "day15-2 example"
    (is (= nil
           (day15-2 example-input)))))

(deftest day15-2-test
  (testing "day15-2"
    (is (= nil
           (day15-2 input)))))
