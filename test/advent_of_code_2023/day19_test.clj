(ns advent-of-code-2023.day19-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day19 :refer :all]))

(defonce ^:private example-input (parse-input ""))

(def ^:private input (parse-input (slurp "resources/day19.txt")))

(deftest day19-1-example-test
  (testing "day19-1 example"
    (is (= nil
           (day19-1 example-input)))))

(deftest day19-1-test
  (testing "day19-1"
    (is (= nil
           (day19-1 input)))))

(deftest day19-2-example-test
  (testing "day19-2 example"
    (is (= nil
           (day19-2 example-input)))))

(deftest day19-2-test
  (testing "day19-2"
    (is (= nil
           (day19-2 input)))))
