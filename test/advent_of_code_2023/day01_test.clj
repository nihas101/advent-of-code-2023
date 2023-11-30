(ns advent-of-code-2023.day01-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day01 :refer :all]))

(def ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day01.txt")))

(deftest day01-1-example-test
  (testing "day01-1 example"
    (is (= nil
           (day01-1 example-input)))))

(deftest day01-1-test
  (testing "day01-1"
    (is (= nil
           (day01-1 input)))))


(deftest day01-2-example-test
  (testing "day01-2 example"
    (is (= nil 
           (day01-2 example-input)))))

(deftest day01-2-test
  (testing "day01-2"
    (is (= nil
           (day01-2 input)))))
