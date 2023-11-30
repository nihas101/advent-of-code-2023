(ns advent-of-code-2023.utils
  (:require
   [clojure.string :as string]))

;; Graph

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

(defn bfs [start-state end-val neighbours visited-state path-fn]
  (pruning-bfs start-state neighbours visited-state
               (constantly 0)
               (fn [[n v]] (and n (not= v end-val)))
               path-fn))

;; Math

(defn manhattan-distance ^long [[^long x1 ^long y1] [^long x2 ^long y2]]
  (+ (abs (- x1 x2)) (abs (- y1 y2))))

(defonce min+max (juxt #(apply min %) #(apply max %)))

(defn sign ^long [^long x]
  (if (pos? x) 1 -1))

(defn extended-gcd
  "https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm"
  [^long a ^long b]
  (loop [[o-r r] [(long a) (long b)]
         [o-s s] [(long 1) (long 0)]
         [o-t t] [(long 0) (long 1)]]
    (if (zero? r)
      {:x o-s :y o-t
       :gcd o-r
       :quots [t s]}
      (let [q (quot o-r r)]
        (recur [r (- ^long o-r (* ^long q ^long r))]
               [s (- ^long o-s (* ^long q ^long s))]
               [t (- ^long o-t (* ^long q ^long t))])))))

;; The unusual definition of the lcm is so that it can be used within a transducer
(defn lcm
  ([] nil)
  ([x] x)
  ([a b]
   (if (nil? a)
     b
     (quot (* ^long a ^long b) ^long (:gcd (extended-gcd a b))))))

(defn crt
  "https://brilliant.org/wiki/chinese-remainder-theorem/"
  [as+nz]
  (let [as (mapv first as+nz)
        nz (mapv second as+nz)
        N (reduce * nz)
        ys (mapv (partial / N) nz)
        zs (mapv :x (mapv extended-gcd ys nz))]
    (mod (abs (reduce + (mapv * as ys zs))) N)))

;; Strings

(defn split-sections
  ([s]
   (string/split s #"(\r?\n){2,}"))
  ([s re]
   (string/split s re)))

(def line-endings-without-trim #"\r?\n")
(def line-endings #"\s*\r?\n\s*")

(defn split-pairs
  ([s]
   (split-pairs s #"\s"))
  ([s del]
   (mapv #(string/split % del) (string/split s line-endings))))

(defn read-longs [s split-on]
  (mapv #(Long/parseLong %) (string/split (string/trim s) split-on)))

;; Input parsing

(defn- vals->pos+val [values val-parser]
  (mapcat (fn [hs y] (mapv (fn [h x] [[x y] (val-parser h)])
                           hs (range)))
          values (range)))

(defn parse-positional-map
  ([vals-map] (parse-positional-map vals-map #(Long/parseLong (str %))))
  ([vals-map val-parser]
   (parse-positional-map vals-map val-parser line-endings))
  ([vals-map val-parser split-on]
   (let [vals-lines (string/split vals-map split-on)]
     {:width (reduce max (mapv count vals-lines))
      :height (count vals-lines)
      :positions (reduce conj {} (vals->pos+val vals-lines val-parser))})))