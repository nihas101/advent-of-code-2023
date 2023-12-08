(ns advent-of-code-2023.day08
  (:require
   [clojure.string :as string]
   [advent-of-code-2023.utils.math :as math]
   [advent-of-code-2023.utils.string :as u]))

(defn- parse-instructions [instr]
  (mapv {\L :left \R :right} instr))

(defn- parse-map [m]
  (transduce
   (map (fn [line]
          (let [[source left right] (re-seq #"[a-zA-Z0-9]{3}" line)]
            {source {:left left
                     :right right}})))
   merge (u/split-sections m u/line-endings)))

(defn parse-input [input]
  (when (seq input)
    (let [[instr m] (u/split-sections input)]
      {:instructions (parse-instructions instr)
       :graph (parse-map m)})))

(defn- steps-until-goal [instructions graph start goals]
  (reduce (fn [[^long step loc] instr]
            (let [next-loc ((graph loc) instr)]
              (if (contains? goals next-loc)
                (reduced (inc step))
                [(inc step) next-loc])))
          [0 start] (cycle instructions)))

(defn day08-1 [{:keys [instructions graph]}]
  (steps-until-goal instructions graph "AAA" #{"ZZZ"}))

(defn- filter-keys [graph s]
  (filterv (fn [source] (string/ends-with? source s))
           (keys graph)))

(defn day08-2 [{:keys [instructions graph]}]
  (let [starts (filter-keys graph "A")
        goals (set (filter-keys graph "Z"))]
    (transduce
     (map (fn [start] (steps-until-goal instructions graph start goals)))
     math/lcm starts)))