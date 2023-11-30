(ns advent-of-code-2023.day20-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day20 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day20.txt")))

(deftest day20-1-example-test
  (testing "day20-1 example"
    (is (= nil
           (day20-1 example-input)))))

(deftest day20-1-test
  (testing "day20-1"
    (is (= nil
           (day20-1 input)))))

(deftest day20-2-example-test
  (testing "day20-2 example"
    (is (= nil
           (day20-2 example-input)))))

(deftest day20-2-test
  (testing "day20-2"
    (is (= nil
           (day20-2 input)))))
