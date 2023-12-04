(ns advent-of-code-2023.day04
  (:require
   [clojure.math :as m]
   [advent-of-code-2023.utils.string :as u]))

(defn parse-input [input]
  (transduce
   (comp
    (map #(u/split-sections % #":\s+"))
    (map second)
    (remove nil?)
    (map (fn [numbers] (u/split-sections numbers #"\s+\|\s+")))
    (map-indexed (fn [idx [winning-numbers numbers]]
                   [idx
                    (set (u/read-longs winning-numbers #"\s+"))
                    (u/read-longs numbers #"\s+")])))
   conj [] (u/split-sections input u/line-endings)))

(defn day04-1 [scratch-cards]
  (transduce
   (comp
    (map rest)
    (map (fn [[winning-numbers numbers]] (filterv winning-numbers numbers)))
    (map (fn [numbers]
           (if (pos? (count numbers)) (long (m/pow 2 (dec (count numbers)))) 0))))
   + scratch-cards))


(defn- won-copy-indexes [scratch-cards]
  (fn [[^long idx & sc]]
    (let [sc-numbers (count ((fn [[winning-numbers numbers]]
                               (filterv winning-numbers numbers)) sc))
          start (inc idx)]
      (if (zero? sc-numbers)
        [idx []]
        [idx (range (inc idx) (min (+ start sc-numbers)
                                   (count scratch-cards)))]))))

(defn- tally-scratch-cards
  ([scc] scc)
  ([sc-count [idx r]]
   (reduce (fn [scc ^long i]
             (if (< (count scc) i)
               scc
               (update scc i + (sc-count idx))))
           sc-count r)))

(defn day04-2 [scratch-cards]
  (->> scratch-cards
       (transduce
        (map (won-copy-indexes scratch-cards))
        tally-scratch-cards
        (vec (repeat (count scratch-cards) 1)))
       (reduce +)))