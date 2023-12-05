(ns advent-of-code-2023.day05-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day05 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(def ^:private example-input (parse-input "seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"))

(def ^:private input (parse-input (tu/slurp-input "resources/day05.txt")))

(deftest day05-1-example-test
  (testing "day05-1 example"
    (is (= 35 (day05-1 example-input)))))

(deftest day05-1-test
  (testing "day05-1"
    (is (= 510109797 (day05-1 input)))))

(deftest day05-2-example-test
  (testing "day05-2 example"
    (is (= 46 (day05-2 example-input)))))

(deftest day05-2-test
  (testing "day05-2"
    (is (= 9622622 (day05-2 input)))))
