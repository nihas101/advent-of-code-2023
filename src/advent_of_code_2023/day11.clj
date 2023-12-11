(ns advent-of-code-2023.day11
  (:require
   [advent-of-code-2023.utils.graph :as g]
   [advent-of-code-2023.utils.math :as math]))

(defn parse-input [input]
  (let [{:keys [positions width height] :as m} (g/parse-positional-map input identity)
        galaxy-positions (transduce (comp (filter (comp #{\#} second)) (map first))
                                    conj [] positions)]
    (-> m
        (assoc ,,, :galaxies galaxy-positions)
        (assoc ,,, :empty-cols (remove (transduce (map first)
                                                  conj #{} galaxy-positions)
                                       (range width)))
        (assoc ,,, :empty-rows (remove (transduce (map second)
                                                  conj #{} galaxy-positions)
                                       (range height))))))

(defn- galaxy-distances [galaxies]
  (for [[^long x ^long y :as g1] galaxies
        [^long xx ^long yy :as g2] galaxies
        :when (or (and (= y yy) (< x xx))
                  (and (not= g1 g2) (< y yy)))]
    {:a g1 :b g2
     :distance (math/manhattan-distance g1 g2)}))

(defn- in-between [^long a ^long b]
  (let [a' (min a b)
        b' (max a b)]
    (fn [^long x] (< a' x b'))))

(defn- expand-distance-fn [empty-cols empty-rows ^long distance-factor]
  (fn expand-distance [{[^long x ^long y] :a
                        [^long xx ^long yy] :b
                        :as galaxy-distance}]
    (let [x-exp (count (filterv (in-between x xx) empty-cols))
          y-exp (count (filterv (in-between y yy) empty-rows))]
      (update galaxy-distance :distance +
              (* distance-factor x-exp) (* distance-factor y-exp)))))

(defn day11
  ([galaxy-map] (day11 galaxy-map 2))
  ([{:keys [galaxies empty-cols empty-rows]} ^long distance-factor]
   (transduce
    (comp
     (map (expand-distance-fn empty-cols empty-rows (dec distance-factor)))
     (map :distance))
    + (galaxy-distances galaxies))))