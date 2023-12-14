(ns advent-of-code-2023.day14
  (:require
   [advent-of-code-2023.utils.graph :as g]))

(defn parse-input [input]
  (let [{:keys [positions] :as r} (g/parse-positional-map input identity)]
    (assoc r :round-rocks (mapv first
                                (filterv (comp #{\O} second)
                                         positions)))))

(defn- direction->positions [{:keys [positions height width]} [^long x ^long y] direction]
  (condp = direction
    :north (mapv (fn [yy] [[x yy] (positions [x yy])])
                 (range y))
    :south (mapv (fn [yy] [[x yy] (positions [x yy])])
                 (reverse (range (inc y) height)))
    :east (mapv (fn [xx] [[xx y] (positions [xx y])])
                (reverse (range (inc x) width)))
    :west (mapv (fn [xx] [[xx y] (positions [xx y])])
                (range x))))

(defn- edge-position [{:keys [^long height ^long width]} [x y] direction]
  (condp = direction
    :north [x 0]
    :south [x (dec height)]
    :east [(dec width) y]
    :west [0 y]))

(defn- move-before [[^long x ^long y] direction]
  (condp = direction
    :north [x (inc y)]
    :south [x (dec y)]
    :east [(dec x) y]
    :west [(inc x) y]))

(defn- tumble-rock [direction rocks rock]
  (let [rock-in-way ((comp first last)
                     (filterv (comp #{\O \#} second)
                              (direction->positions rocks rock direction)))]
    (cond
      (nil? rock-in-way) (-> rocks
                             (assoc-in ,,, [:positions rock] \.)
                             (assoc-in ,,, [:positions (edge-position rocks rock direction)] \O))
      :else (-> rocks
                (assoc-in ,,, [:positions rock] \.)
                (assoc-in ,,, [:positions (move-before rock-in-way direction)] \O)))))

(defn- sort-round-rocks [round-rocks direction]
  (condp = direction
    :north (sort-by second round-rocks)
    :south (reverse (sort-by second round-rocks))
    :east (reverse (sort-by first round-rocks))
    :west (sort-by first round-rocks)))

(defn- tumble-rocks [{:keys [round-rocks] :as rocks} direction]
  (let [{:keys [positions] :as new-rocks} (reduce (partial tumble-rock direction) rocks
                                                  (sort-round-rocks round-rocks direction))]
    (assoc new-rocks :round-rocks
           (mapv first (filterv (comp #{\O} second) positions)))))

(defn- total-load [{:keys [positions ^long height]}]
  (transduce
   (comp
    (filter (fn [[_ v]] (#{\O} v)))
    (map first)
    (map second)
    (map #(- height ^long %)))
   + positions))

(defn day14-1 [rocks]
  (total-load (tumble-rocks rocks :north)))

(defn day14-2
  ([rocks] (day14-2 rocks 1000000000))
  ([rocks limit]
   (loop [rocks rocks
          it-count 0
          it->rocks (transient {})
          rocks->it (transient {})
          visited (transient #{})
          [d & ds] (take limit (repeat [:north :west :south :east]))]
     (cond
       (zero? (- limit it-count)) (total-load rocks)
       ;; We found a cycle and can deduce the final result
       (visited rocks) (let [cycle-start (rocks->it rocks)
                             cycle-length (- it-count ^long cycle-start)]
                         (total-load (it->rocks (+ ^long cycle-start
                                                   ^long (mod (- limit it-count)
                                                              cycle-length)))))
       ;; We need to continue
       :else (let [new-rocks (reduce tumble-rocks rocks d)]
               (recur new-rocks
                      (inc it-count)
                      (conj! it->rocks [it-count rocks])
                      (conj! rocks->it [rocks it-count])
                      (conj! visited rocks)
                      ds))))))