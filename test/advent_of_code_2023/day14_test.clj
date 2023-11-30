(ns advent-of-code-2023.day14-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day14 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day14.txt")))

(deftest day14-1-example-test
  (testing "day14-1 example"
    (is (= nil
           (day14-1 example-input)))))

(deftest day14-1-test
  (testing "day14-1"
    (is (= nil
           (day14-1 input)))))

(deftest day14-2-example-test
  (testing "day14-2 example"
    (is (= nil
           (day14-2 example-input)))))

(deftest day14-2-test
  (testing "day14-2"
    (is (= nil
           (day14-2 input)))))
