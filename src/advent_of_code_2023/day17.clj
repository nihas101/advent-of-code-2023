(ns advent-of-code-2023.day17
  (:require
   [advent-of-code-2023.utils.graph :as g]))

(defn parse-input [input]
  (g/parse-positional-map input))

(defn- assoc-to-priority-map [q [_ _ _ _ heat-loss :as n]]
  (assoc q n heat-loss))

(defn- continue?-fn [{:keys [^long height ^long width]}]
  (let [goal [(dec height) (dec width)]]
    (fn [[x y]] (and x y (not= [x y] goal)))))

(defn- neighbours [^long x ^long y dir]
  (condp = dir
    :none [[(inc x) y]
           [x (inc y)]]
    :north [[x (dec y)]
            [(dec x) y]
            [(inc x) y]]
    :east [[x (dec y)]
           [(inc x) y]
           [x (inc y)]]
    :south [[(dec x) y]
            [(inc x) y]
            [x (inc y)]]
    :west [[x (dec y)]
           [(dec x) y]
           [x (inc y)]]))

(defn- direction [[^long x ^long y] [^long xx ^long yy]]
  (cond
    (= (inc x) xx) :east
    (= (dec x) xx) :west
    (= (inc y) yy) :south
    (= (dec y) yy) :north))

(defn- valid-crucible-move?-fn [[x y ^long straight-moves dir]
                                ^long min-straight-moves
                                ^long max-straight-moves]
  (fn [p]
    (if (and (not= dir :none) (< straight-moves min-straight-moves))
      (= (direction [x y] p) dir)
      (or (< straight-moves max-straight-moves)
          (not= (direction [x y] p) dir)))))

(defn- visited?-fn [[x y ^long straight-moves dir] visited]
  (fn [p]
    (let [d (direction [x y] p)]
      ((complement (visited p #{})) [(if (= d dir) (inc straight-moves) 0) d]))))

(defn- next-state-fn [[x y ^long straight-moves dir ^long heat-loss] positions]
  (fn [[xx yy :as p]]
    (let [d (direction [x y] p)]
      [xx yy (if (= d dir) (inc straight-moves) 0)
       d (+ heat-loss ^long (positions p))])))

(defn- neighbours-fn [{:keys [positions]}
                      ^long min-straight-moves
                      ^long max-straight-moves]
  (fn [[x y _ dir :as state] visited]
    (let [nz
          (transduce
           (comp
            (filter (valid-crucible-move?-fn state
                                             min-straight-moves
                                             max-straight-moves))
            (filter positions)
            (filter (visited?-fn state visited)))
           conj [] (neighbours x y dir))]
      (mapv (next-state-fn state positions) nz))))

(defn- visited-state [[x y sm dir heat-loss]]
  [[x y] [sm dir] heat-loss])

(defn- path-fn [{:keys [^long height ^long width]} ^long min-straight-moves]
  (let [goal [(dec width) (dec height)]]
    (fn ([] nil)
      ([heat-loss [x y sm _ hl]]
       (cond
         (and sm (< ^long sm min-straight-moves)) heat-loss
         (and heat-loss x y (= goal [x y])) (min ^long heat-loss ^long hl)
         (= goal [x y]) hl
         :else heat-loss)))))

(defn day17 [heat-loss-map min-straight-moves max-straight-moves]
  (let [start-state [0 0 0 :none 0]]
    (g/dijkstra start-state
                (continue?-fn heat-loss-map)
                (neighbours-fn heat-loss-map min-straight-moves max-straight-moves)
                visited-state
                (path-fn heat-loss-map min-straight-moves)
                {[0 0] #{}}
                assoc-to-priority-map)))

(defn day17-1 [heat-loss-map]
  (day17 heat-loss-map 0 2))

(defn day17-2 [heat-loss-map]
  (day17 heat-loss-map 3 9))