(ns advent-of-code-2023.day07-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day07 :refer :all]))

(def ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day07.txt")))

(deftest day07-1-example-test
  (testing "day07-1 example"
    (is (= nil
           (day07-1 example-input)))))

(deftest day07-1-test
  (testing "day07-1"
    (is (= nil
           (day07-1 input)))))

(deftest day07-2-example-test
  (testing "day07-2 example"
    (is (= nil
           (day07-2 example-input)))))

(deftest day07-2-test
  (testing "day07-2"
    (is (= nil
           (day07-2 input)))))
