(ns advent-of-code-2023.day09-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day09 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day09.txt")))

(deftest day09-1-example-test
  (testing "day09-1 example"
    (is (= nil
           (day09-1 example-input)))))

(deftest day09-1-test
  (testing "day09-1"
    (is (= nil
           (day09-1 input)))))

(deftest day09-2-example-test
  (testing "day09-2 example"
    (is (= nil
           (day09-2 example-input)))))

(deftest day09-2-test
  (testing "day09-2"
    (is (= nil
           (day09-2 input)))))
