(ns advent-of-code-2023.day01
  (:require
   [advent-of-code-2023.utils :as u]
   [clojure.string :as string]))

(defonce ^:private ascii-numbers
  (set (mapv char (range (long \1) (inc (long \9))))))

(defn- parse-input [numbers-filter]
  (comp
   (map numbers-filter)
   (map (fn [[n :as nums]] (Long/parseLong (str n (peek nums)))))))

(defn day01 [first-number last-number input]
  (transduce
   (parse-input (fn [line] [(first-number line)
                            (last-number line)]))
   + (u/split-sections input u/line-endings)))

(defn- first-ascii-number [line]
  (first (filter ascii-numbers line)))

(defonce day01-1
  (partial day01 first-ascii-number (comp first-ascii-number reverse)))


(defonce ^:private convert-spelled-number
  {"1" \1
   "2" \2
   "3" \3
   "4" \4
   "5" \5
   "6" \6
   "7" \7
   "8" \8
   "9" \9
   "one" \1
   "two" \2
   "three" \3
   "four" \4
   "five" \5
   "six"  \6
   "seven" \7
   "eight" \8
   "nine" \9})

(defonce ^:private ascii+spelled (keys convert-spelled-number))

(defonce ^:private forward-pattern
  (re-pattern (string/join "|" ascii+spelled)))

(defonce ^:private reversed-pattern
  (re-pattern (string/join (reverse (string/join "|" ascii+spelled)))))

(defonce day01-2
  (partial day01
           (fn [line] (convert-spelled-number (re-find forward-pattern line)))
           (fn [line]
             (convert-spelled-number
              (string/reverse (re-find reversed-pattern
                                       (string/reverse line)))))))