(ns advent-of-code-2023.day20-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day20 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "broadcaster -> a, b, c
%a -> b
%b -> c
%c -> inv
&inv -> a"))

(defonce ^:private example-input-2 (parse-input "broadcaster -> a
%a -> inv, con
&inv -> b
%b -> con
&con -> output"))

(def ^:private input (parse-input (tu/slurp-input "resources/day20.txt")))

(deftest day20-1-example-test
  (testing "day20-1 example"
    (is (= 32000000 (day20-1 example-input)))))

(deftest day20-1-example-2-test
  (testing "day20-1 example 2"
    (is (= 11687500 (day20-1 example-input-2)))))

(deftest day20-1-test
  (testing "day20-1"
    (is (= 825896364 (day20-1 input)))))

(deftest day20-2-example-test
  (testing "day20-2 example 2"
    (is (= 1 (day20-2 example-input-2 "output")))))

(deftest day20-2-test
  (testing "day20-2"
    (is (= 243566897206981 (day20-2 input "rx")))))
