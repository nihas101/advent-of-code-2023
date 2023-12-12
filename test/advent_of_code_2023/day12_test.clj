(ns advent-of-code-2023.day12-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day12 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "???.### 1,1,3
.??..??...?##. 1,1,3
?#?#?#?#?#?#?#? 1,3,1,6
????.#...#... 4,1,1
????.######..#####. 1,6,5
?###???????? 3,2,1"))

(def ^:private input (parse-input (tu/slurp-input "resources/day12.txt")))

(deftest day12-1-example-test
  (testing "day12-1 example"
    (is (= 21 (day12-1 example-input)))))

(deftest day12-1-test
  (testing "day12-1"
    (is (= 7084 (day12-1 input)))))

(deftest day12-2-example-test
  (testing "day12-2 example"
    (is (= 525152 (day12-2 example-input)))))

(deftest day12-2-test
  (testing "day12-2"
    (is (= 8414003326821 (day12-2 input)))))
