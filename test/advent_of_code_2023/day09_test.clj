(ns advent-of-code-2023.day09-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day09 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45"))

(def ^:private input (parse-input (tu/slurp-input "resources/day09.txt")))

(deftest day09-1-example-test
  (testing "day09-1 example"
    (is (= 114 (day09-1 example-input)))))

(deftest day09-1-test
  (testing "day09-1"
    (is (= 1782868781 (day09-1 input)))))

(deftest day09-2-example-test
  (testing "day09-2 example"
    (is (= 2 (day09-2 example-input)))))

(deftest day09-2-test
  (testing "day09-2"
    (is (= 1057 (day09-2 input)))))
