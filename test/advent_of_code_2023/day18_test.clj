(ns advent-of-code-2023.day18-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day18 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day18.txt")))

(deftest day18-1-example-test
  (testing "day18-1 example"
    (is (= nil
           (day18-1 example-input)))))

(deftest day18-1-test
  (testing "day18-1"
    (is (= nil
           (day18-1 input)))))

(deftest day18-2-example-test
  (testing "day18-2 example"
    (is (= nil
           (day18-2 example-input)))))

(deftest day18-2-test
  (testing "day18-2"
    (is (= nil
           (day18-2 input)))))
