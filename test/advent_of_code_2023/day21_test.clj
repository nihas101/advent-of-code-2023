(ns advent-of-code-2023.day21-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day21 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (tu/slurp-input "resources/day21.txt")))

(deftest day21-1-example-test
  (testing "day21-1 example"
    (is (= nil
           (day21-1 example-input)))))

(deftest day21-1-test
  (testing "day21-1"
    (is (= nil
           (day21-1 input)))))

(deftest day21-2-example-test
  (testing "day21-2 example"
    (is (= nil
           (day21-2 example-input)))))

(deftest day21-2-test
  (testing "day21-2"
    (is (= nil
           (day21-2 input)))))
