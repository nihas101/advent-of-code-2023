(ns advent-of-code-2023.utils.graph
  (:require
   [clojure.string :as string]
   [advent-of-code-2023.utils.string :as us]))

(defn transitive-closure [initial-closure]
  (let [new-closure (reduce (fn [closure [node cls]]
                              (update closure node into (mapcat closure cls)))
                            initial-closure initial-closure)]
    (if (= new-closure initial-closure)
      initial-closure
      (recur new-closure))))

(defn pruning-bfs [start-state neighbours visited-state branch-score continue? path-fn]
  (loop [q (conj clojure.lang.PersistentQueue/EMPTY start-state)
         visited #{}
         best-branch-score (branch-score)
         path-val (path-fn)]
    (let [c (first q)
          q (pop q)]
      (cond
        (continue? c) (let [nz (neighbours c visited best-branch-score)]
                        (recur (into q nz)
                               (into visited (mapv visited-state nz))
                               ^long (reduce max best-branch-score (mapv branch-score nz))
                               (path-fn path-val c)))
        (seq q) (recur q
                       visited
                       best-branch-score
                       (path-fn path-val c))
        :else (path-fn path-val c)))))

(defn bfs [start-state continue? neighbours visited-state path-fn]
  (pruning-bfs start-state neighbours visited-state
               (constantly 0) continue? path-fn))

;;; Positional graphs

(defn graph->str [{:keys [positions height width] :as g}]
  (string/join \newline
               (mapv string/join
                     (partition-all width
                                    (for [y (range height)
                                          x (range width)]
                                      (positions [x y]))))))

(defn- vals->pos+val [values val-parser]
  (mapcat (fn [hs y] (mapv (fn [h x] [[x y] (val-parser h)])
                           hs (range)))
          values (range)))

(defn parse-positional-map
  ([vals-map] (parse-positional-map vals-map #(Long/parseLong (str %))))
  ([vals-map val-parser]
   (parse-positional-map vals-map val-parser us/line-endings))
  ([vals-map val-parser split-on]
   (let [vals-lines (string/split vals-map split-on)]
     {:width (reduce max (mapv count vals-lines))
      :height (count vals-lines)
      :positions (reduce conj {} (vals->pos+val vals-lines val-parser))})))