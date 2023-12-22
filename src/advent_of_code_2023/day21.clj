(ns advent-of-code-2023.day21
  (:require
   [advent-of-code-2023.utils.graph :as g]))

(defn parse-input [input]
  (let [{:keys [positions] :as garden} (g/parse-positional-map input identity)]
    (assoc garden :start
           (first (mapv first (filterv (fn [[p v]] (= v \S)) positions))))))

(defn- neighbours [p]
  [(update p 0 inc)
   (update p 0 dec)
   (update p 1 inc)
   (update p 1 dec)])

(defn- garden-positions-step-fn [positions adjusted-pos]
  (fn [garden-positions]
    (persistent!
     (transduce
      (comp
       (mapcat neighbours)
       (filter (comp #{\. \S} positions adjusted-pos))
       (distinct))
      conj! (transient []) garden-positions))))

(defn- day21 [{:keys [positions]} starts ^long steps adjusted-pos]
  (let [garden-positions-step (garden-positions-step-fn positions adjusted-pos)]
    (loop [steps ^long steps
           current-positions starts]
      (if (pos? steps)
        (let [new-positions (garden-positions-step current-positions)]
          (recur (dec steps)
                 new-positions))
        current-positions))))

(defn day21-1 [{:keys [start] :as garden} steps]
  (count (day21 garden [start] steps identity)))

(defn- adjust-infinity-fn [^long height ^long width]
  (fn [[^long x ^long y]]
    [(mod (if (<= 0 x) x (+ x width)) width)
     (mod (if (<= 0 y) y (+ y width)) height)]))

;; Monkey see, monkey do:
;; https://github.com/goggle/AdventOfCode2023.jl/blob/main/src/day21.jl#L44
(defn day21-2 [{:keys [^long height ^long width start] :as garden} ^long steps]
  (let [adjust-infinity (adjust-infinity-fn height width)
        w width
        w2 (quot width 2)
        pos1 (day21 garden [start] w2 adjust-infinity)
        pos2 (day21 garden pos1 w adjust-infinity)
        r1 (count pos1)
        r2 (count pos2)
        r3 (count (day21 garden pos2 w adjust-infinity))
        a ^long (quot (- (+ r3 r1) (* 2 r2)) 2)
        b ^long (quot (- (- (* 4 r2) (* 3 r1)) r3) 2)
        c r1
        x ^long (quot (- steps w2) w)]
    (+ (* a x x) (* b x) c)))