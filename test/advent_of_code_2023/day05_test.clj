(ns advent-of-code-2023.day05-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day05 :refer :all]))

(def ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day05.txt")))

(deftest day05-1-example-test
  (testing "day05-1 example"
    (is (= nil
           (day05-1 example-input)))))

(deftest day05-1-test
  (testing "day05-1"
    (is (= nil
           (day05-1 input)))))

(deftest day05-2-example-test
  (testing "day05-2 example"
    (is (= nil
           (day05-2 example-input)))))

(deftest day05-2-test
  (testing "day05-2"
    (is (= nil
           (day05-2 input)))))
