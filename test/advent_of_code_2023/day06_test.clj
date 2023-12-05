(ns advent-of-code-2023.day06-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day06 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(def ^:private example-input "")

(def ^:private input (tu/slurp-input "resources/day06.txt"))

(deftest day06-1-example-test
  (testing "day06-1 example"
    (is (= nil
           (day06-1 example-input)))))

(deftest day06-1-test
  (testing "day06-1"
    (is (= nil
           (day06-1 input)))))

(deftest day06-2-example-test
  (testing "day06-2 example"
    (is (= nil
           (day06-2 example-input)))))

(deftest day06-2-test
  (testing "day06-2"
    (is (= nil
           (day06-2 input)))))
