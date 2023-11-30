(ns advent-of-code-2023.day12-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day12 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day12.txt")))

(deftest day12-1-example-test
  (testing "day12-1 example"
    (is (= nil
           (day12-1 example-input)))))

(deftest day12-1-test
  (testing "day12-1"
    (is (= nil
           (day12-1 input)))))

(deftest day12-2-example-test
  (testing "day12-2 example"
    (is (= nil
           (day12-2 example-input)))))

(deftest day12-2-test
  (testing "day12-2"
    (is (= nil
           (day12-2 input)))))
