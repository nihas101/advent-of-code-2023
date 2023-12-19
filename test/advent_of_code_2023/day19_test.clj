(ns advent-of-code-2023.day19-test
  (:require
   [clojure.test :refer :all]
   [advent-of-code-2023.day19 :refer :all]
   [advent-of-code-2023.test-utils :as tu]))

(defonce ^:private example-input (parse-input "px{a<2006:qkq,m>2090:A,rfg}
pv{a>1716:R,A}
lnx{m>1548:A,A}
rfg{s<537:gd,x>2440:R,A}
qs{s>3448:A,lnx}
qkq{x<1416:A,crn}
crn{x>2662:A,R}
in{s<1351:px,qqz}
qqz{s>2770:qs,m<1801:hdj,R}
gd{a>3333:R,R}
hdj{m>838:A,pv}

{x=787,m=2655,a=1222,s=2876}
{x=1679,m=44,a=2067,s=496}
{x=2036,m=264,a=79,s=2244}
{x=2461,m=1339,a=466,s=291}
{x=2127,m=1623,a=2188,s=1013}"))

(def ^:private input (parse-input (tu/slurp-input "resources/day19.txt")))

(deftest day19-1-example-test
  (testing "day19-1 example"
    (is (= 19114 (day19-1 example-input)))))

(deftest day19-1-test
  (testing "day19-1"
    (is (= 397134 (day19-1 input)))))

(deftest day19-2-example-test
  (testing "day19-2 example"
    (is (= 167409079868000 (day19-2 example-input)))))

(deftest day19-2-test
  (testing "day19-2"
    (is (= 127517902575337 (day19-2 input)))))
