(ns advent-of-code-2023.day24-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day24 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (tu/slurp-input "resources/day24.txt")))

(deftest day24-1-example-test
  (testing "day24-1 example"
    (is (= nil
           (day24-1 example-input)))))

(deftest day24-1-test
  (testing "day24-1"
    (is (= nil
           (day24-1 input)))))

(deftest day24-2-example-test
  (testing "day24-2 example"
    (is (= nil
           (day24-2 example-input)))))

(deftest day24-2-test
  (testing "day24-2"
    (is (= nil
           (day24-2 input)))))