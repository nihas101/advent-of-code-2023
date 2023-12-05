(ns advent-of-code-2023.day23-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day23 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (tu/slurp-input "resources/day23.txt")))

(deftest day23-1-example-test
  (testing "day23-1 example"
    (is (= nil
           (day23-1 example-input)))))

(deftest day23-1-test
  (testing "day23-1"
    (is (= nil (day23-1 input)))))

(deftest day23-2-example-test
  (testing "day23-2 example"
    (is (= nil
           (day23-2 example-input)))))

(deftest day23-2-test
  (testing "day23-2"
    (is (= nil
           (day23-2 input)))))