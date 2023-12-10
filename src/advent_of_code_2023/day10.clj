(ns advent-of-code-2023.day10
  (:require
   [clojure.set :as set]
   [advent-of-code-2023.utils.graph :as g]))

(defn parse-input [input]
  (let [{:keys [positions] :as m} (g/parse-positional-map input identity)]
    (assoc m :start
           (ffirst (filterv (comp #{\S} second) positions)))))

(defonce ^:private valid-state?
  #{[\| :north]
   [\| :south]
   [\- :east]
   [\- :west]
   [\L :west]
   [\L :south]
   [\J :east]
   [\J :south]
   [\7 :north]
   [\7 :east]
   [\F :west]
   [\F :north]
   [\S :north]
   [\S :east]
   [\S :south]
   [\S :west]})

(defn- next-position [[^long x ^long y] direction]
  (condp = direction
    :north [x (dec y)]
    :east [(inc x) y]
    :south [x (inc y)]
    :west [(dec x) y]))

(defn- next-direction [pipe direction]
  (condp = [pipe direction]
    [\| :north] :north
    [\| :south] :south
    [\- :east] :east
    [\- :west] :west
    [\L :west] :north
    [\L :south] :east
    [\J :east] :north
    [\J :south] :west
    [\7 :north] :west
    [\7 :east] :south
    [\F :west] :south
    [\F :north] :east
    [\S :north] :north
    [\S :east] :east
    [\S :south] :south
    [\S :west] :west))

(defn- next-pipe-pos-fn [{:keys [positions]}]
  (fn next-pipe-pos [[[^long x ^long y] _ direction] visited]
    (let [next-pos (next-position [x y] direction)
          next-pipe (positions next-pos)]
      (cond
        (visited next-pos) nil
        (valid-state? [next-pipe direction]) [next-pos next-pipe (next-direction next-pipe direction)]
        :else nil))))

(defn try-loop [start-state continue? next-pipe-pos]
  (loop [pipe-pos start-state
         visited #{}
         path []]
    (cond
      (continue? pipe-pos) (when-let [np (next-pipe-pos pipe-pos visited)]
                             (recur np
                                    (conj visited (first np))
                                    (conj path pipe-pos)))
      :else (conj path pipe-pos))))

(defn- continue?-fn [direction]
  (fn continue? [[n v d]]
    (or (and n (not= v \S))
        (= direction d))))

(defn- find-loop [{:keys [start] :as m}]
  (first
   (transduce
    (comp
     (map
      (fn [direction]
        (try-loop [start \S direction]
                  (continue?-fn direction)
                  (next-pipe-pos-fn m))))
     (remove nil?))
    conj []
    [:north :east :south :west])))


(defn day10-1 [m]
  (-> m
      find-loop
      count
      dec ; Subtract the start point that appears twice
      (quot ,,, 2)))

(defn- enclosed-candidates [loop]
  (let [loop-pos (set (mapv first loop))
        x-pos (mapv first loop-pos)
        y-pos (mapv second loop-pos)
        min-x (reduce min x-pos)
        min-y (reduce min y-pos)
        max-x (reduce max x-pos)
        max-y (reduce max y-pos)]
    (for [x (range min-x (inc ^long max-x))
          y (range min-y (inc ^long max-y))
          :when ((complement loop-pos) [x y])]
      [x y])))

(defonce ^:private valid-neighbouring-pipes
  {[\| :north] #{\| \7 \F}
   [\| :south] #{\| \L \J}
   [\- :east] #{\- \J \7}
   [\- :west] #{\- \L \F}
   [\L :west] #{\| \7 \F}
   [\L :south] #{\- \J \7}
   [\J :east] #{\| \7 \F}
   [\J :south] #{\- \L \F}
   [\7 :north] #{\- \L \F}
   [\7 :east] #{\| \L \J}
   [\F :west] #{\| \L \J}
   [\F :north] #{\- \J \7}
   [\S :north] #{\| \7 \F}
   [\S :east] #{\- \J \7}
   [\S :south] #{\| \L \J}
   [\S :west] #{\- \L \F}})

(defn- determine-separators [loop]
  (let [sec (rest (second loop))
        sec-last (rest (second (reverse  loop)))
        s (->> [sec sec-last]
               (mapv valid-neighbouring-pipes)
               (apply set/intersection)
               vec
               first)]
    (if (#{\L \J \|} s)
      #{\L \S \J \|}
      #{\L \J \|})))

(defn- loop-map [loop]
  (reduce (fn [m [pos v _]] (assoc m pos v))
          {}
          loop))

(defn- enclosed?-fn [loop]
  (let [sep (determine-separators loop)
        loop-m (loop-map loop)]
    (fn enclosed? [[x y]]
      ;; Rasterization: We count how many times we cross a pipe going west
      ;; from a possible enclosed point
      ;; If it is an odd amount -> enclosed
      ;; This can probably be done a lot smarter to avoid a lot of duplicate work for a given row
      (->> x
           range
           (transduce
            (comp
             (map (fn [xx] [xx y]))
             (map loop-m)
             (filter sep))
            conj [])
           count
           odd?))))

(defn day10-2 [pipe-map]
  (let [loop (find-loop pipe-map)
        enclosed-cand (enclosed-candidates loop)]
    (count (filterv (enclosed?-fn loop) enclosed-cand))))