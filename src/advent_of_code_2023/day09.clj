(ns advent-of-code-2023.day09
  (:require
   [advent-of-code-2023.utils.string :as u]))

(defn parse-input [input]
  (when (seq input)
    (mapv u/read-longs
          (u/split-sections input u/line-endings))))

(defn- diffs [nums]
  (loop [nums nums
         res (list nums)]
    (if (every? zero? nums)
      res
      (let [num-diffs (mapv - (rest nums) nums)]
        (recur num-diffs (conj res num-diffs))))))

(defn- extrapolate-forwards [num-diffs]
  (reduce (fn [^long extra nd] (+ extra ^long (peek nd)))
          0 num-diffs))

(defn- day09 [extrapolate-fn parsed-input]
  (transduce
   (comp
    (map diffs)
    (map extrapolate-fn))
   + parsed-input))

(defn day09-1 [parsed-input]
  (day09 extrapolate-forwards parsed-input))

(defn- extrapolate-backwards [num-diffs]
  (reduce (fn [^long extra nd] (- ^long (first nd) extra))
          0 num-diffs))

(defn day09-2 [parsed-input]
  (day09 extrapolate-backwards parsed-input))