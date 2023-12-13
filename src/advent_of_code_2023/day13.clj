(ns advent-of-code-2023.day13
  (:require
   [advent-of-code-2023.utils.graph :as g]
   [advent-of-code-2023.utils.string :as u]))

(defn parse-input [input]
  (mapv #(g/parse-positional-map % identity)
        (u/split-sections input)))

(defn- mirrored?-fn [smudges]
  (fn [a b]
    (=
     (count (filterv (fn [[aa bb]] (not= aa bb))
                     (mapv vector (take-while (complement nil?) a)
                           (take-while (complement nil?) b))))
     smudges)))

(defn- vertical-mirror? [{:keys [positions ^long height ^long width]} 
                         ^long x mirrored?]
  (let [max-offset (min (inc x) (- width x))]
    (mirrored?
     (transduce
      (mapcat (fn [^long o] (mapv #(positions [(- x o) %]) (range height))))
      conj [] (range (inc max-offset)))
     (transduce
      (mapcat (fn [^long o] (mapv #(positions [(+ (inc x) o) %]) (range height))))
      conj [] (range (inc max-offset))))))

(defn- horizontal-mirror? [{:keys [positions ^long height ^long width]} 
                           ^long y mirrored?]
  (let [max-offset (min (inc y) (- height y))]
    (mirrored?
     (transduce
      (mapcat (fn [^long o] (mapv #(positions [% (- y o)]) (range width))))
      conj [] (range (inc max-offset)))
     (transduce
      (mapcat (fn [^long o] (mapv #(positions [% (+ (inc y) o)]) (range width))))
      conj [] (range (inc max-offset))))))

(defn- mirrors [{:keys [^long width ^long height] :as p} comp-fn]
  (let [vertical-candidates
        (filterv (fn [x] (vertical-mirror? p x comp-fn))
                 (range (dec width)))
        horizontal-candidates
        (filterv (fn [y] (horizontal-mirror? p y comp-fn))
                 (range (dec height)))]
    {:vertical-candidates vertical-candidates
     :horizontal-candidates horizontal-candidates}))

(defn- day13 [mirror-landscape mirrored?]
  (transduce
   (comp
    (map (fn [p] (mirrors p mirrored?)))
    (map (fn [{:keys [vertical-candidates horizontal-candidates]}]
           (+ (if-let [v (first vertical-candidates)] (inc ^long v) 0)
              (* 100 (if-let [h (first horizontal-candidates)] (inc ^long h) 0))))))
   + mirror-landscape))

(defn day13-1 [mirror-landscape]
  (day13 mirror-landscape (mirrored?-fn 0)))

(defn day13-2 [mirror-landscape]
  (day13 mirror-landscape (mirrored?-fn 1)))