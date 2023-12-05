(ns advent-of-code-2023.day08-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day08 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(def ^:private example-input (parse-input ""))

(def ^:private input (parse-input (tu/slurp-input "resources/day08.txt")))

(deftest day08-1-example-test
  (testing "day08-1 example"
    (is (= nil
           (day08-1 example-input)))))

(deftest day08-1-test
  (testing "day08-1"
    (is (= nil
           (day08-1 input)))))

(deftest day08-2-example-test
  (testing "day08-2 example"
    (is (= nil
           (day08-2 example-input)))))

(deftest day08-2-test
  (testing "day08-2"
    (is (= nil
           (day08-2 input)))))
