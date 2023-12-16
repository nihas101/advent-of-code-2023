(ns advent-of-code-2023.day16
  (:require
   [advent-of-code-2023.utils.graph :as g]))

;; For debug purposes
(defn- assoc-beams [{:keys [positions] :as c} [x y dir]]
  (if (#{\| \- \\ \/} (positions [x y]))
    c
    (assoc-in c [:positions [x y]]
              (condp = dir
                :north \^
                :east \>
                :south \v
                :west \<
                :?))))

;; For debug purposes
(defn- assoc-energized [c [x y]]
  (assoc-in c [:positions [x y]] \#))

(defn parse-input [input]
  (g/parse-positional-map input identity))

(defn- move-beam [[_ _ direction :as position]]
  (condp = direction
    :north (update position 1 dec)
    :east (update position 0 inc)
    :south (update position 1 inc)
    :west (update position 0 dec)))

(defn- split-beam [dir val [x y]]
  (condp = [dir val]
    [:east \|] [[x y :north] [x y :south]]
    [:west \|] [[x y :north] [x y :south]]
    [:north \-] [[x y :east] [x y :west]]
    [:south \-] [[x y :east] [x y :west]]
    nil))

(defn- trace-beam
  ([contraption start-pos]
   (trace-beam contraption start-pos #{}))
  ([{:keys [positions] :as contraption} beam-position beam-positions]
   (let [[x y dir :as np] (move-beam beam-position)
         val (positions (butlast np))]
     ;; It is not enough to currently be in the same position and same direction
     ;; for a cycle. We also need to have come from the same position
     (if (and (beam-positions beam-position) (beam-positions np))
       beam-positions
       (condp = val
         ;; We are outside of the grid -> stop
         nil (conj beam-positions beam-position)
         \. (recur contraption np (conj beam-positions beam-position))
         \| (if-let [[dir1 dir2] (split-beam dir val np)]
              (let [up-beam-positions
                    (trace-beam contraption dir1 (conj beam-positions beam-position))]
                (recur contraption dir2 up-beam-positions))
              ;; We simply pass through the mirror
              (recur contraption np (conj beam-positions beam-position)))
         \- (if-let [[dir1 dir2] (split-beam dir val np)]
              (let [left-beam-positions
                    (trace-beam contraption dir1 (conj beam-positions beam-position))]
                (trace-beam contraption dir2 left-beam-positions))
              ;; We simply pass through the mirror
              (recur contraption np (conj beam-positions beam-position)))
         \\ (recur contraption [x y (condp = dir
                                      :north :west
                                      :south :east
                                      :east :south
                                      :west :north)]
                   (conj beam-positions beam-position))
         \/ (recur contraption [x y (condp = dir
                                      :north :east
                                      :south :west
                                      :east :north
                                      :west :south)]
                   (conj beam-positions beam-position)))))))

(defn- energy [beam-positions]
  ;; We have to subtract one, because our result also contains the
  ;; start point otuside of the grid
  (dec (count (set (mapv butlast beam-positions)))))

(defn- day16 [contraption beam-start]
  (energy (trace-beam contraption beam-start)))

(defn day16-1 [contraption]
  (day16 contraption [-1 0 :east]))

(defn day16-2 [{:keys [height width] :as contraption}]
  (let [beam-starts (concat
                     (for [x (range width)] [x -1 :south])
                     (for [x (range width)] [x height :north])
                     (for [y (range height)] [-1 y :east])
                     (for [y (range height)] [width y :west]))]
    (->> beam-starts
         (pmap (partial day16 contraption))
         (reduce max))))