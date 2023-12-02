(ns advent-of-code-2023.day02
  (:require
   [advent-of-code-2023.utils.string :as u]))

(defn- parse-die [die]
  (let [[[num col]] (u/split-pairs die)]
    {col (Long/parseLong num)}))

(defn- parse-game [[game & dice]]
  [(Long/parseLong (re-find #"\d+" game))
   (transduce
    (comp
     (map #(u/split-sections % #"\s*,\s*"))
     (map (fn [d] (transduce (map parse-die) merge d))))
    conj [] dice)])

(defn parse-input [games]
  (transduce
   (comp
    (map #(u/split-sections % #"\s*:\s*|\s*;\s*"))
    (remove (comp empty? first))
    (map parse-game))
   conj [] (u/split-sections games u/line-endings)))

(defonce ^:private bag
  {"red" 12
   "green" 13
   "blue" 14})

(defn- max-dice [[game dice]]
  [game (apply merge-with max dice)])

(defn- possible-game-dice? [game-dice]
  (fn [[col ^long max-dice]] (<= ^long (game-dice col 0) max-dice)))

(defn day02-1 [parsed-input]
  (transduce
   (comp
    (map max-dice)
    (filter (comp #(every? (possible-game-dice? %) bag) second))
    (map first))
   + parsed-input))

(defn- dice-power [dice]
  (reduce * (vals dice)))

(defn day02-2 [parsed-input]
  (transduce
   (comp
    (map max-dice)
    (map second)
    (map dice-power))
   + parsed-input))