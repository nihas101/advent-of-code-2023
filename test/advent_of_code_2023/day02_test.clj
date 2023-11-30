(ns advent-of-code-2023.day02-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day02 :refer :all]))

(def ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day02.txt")))

(deftest day02-1-example-test
  (testing "day02-1 example"
    (is (= nil
           (day02-1 example-input)))))

(deftest day02-1-test
  (testing "day02-1"
    (is (= nil
           (day02-1 input)))))

(deftest day02-2-example-test
  (testing "day02-2 example"
    (is (= nil
           (day02-2 example-input)))))

(deftest day02-2-test
  (testing "day02-2"
    (is (= nil
           (day02-2 input)))))
