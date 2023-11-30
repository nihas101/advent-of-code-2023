(ns advent-of-code-2023.day22-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day22 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day22.txt")))

(deftest day22-1-example-test
    (testing "day22-1 example"
      (is (= nil
             (day22-1 example-input)))))

(deftest day22-1-test
    (testing "day22-1"
      (is (= nil
             (day22-1 input)))))

(deftest day22-2-example-test
  (testing "day22-2 example"
    (is (= nil
           (day22-2 example-input)))))

(deftest day22-2-test
  (testing "day22-2"
    (is (= nil
           (day22-2 input)))))
