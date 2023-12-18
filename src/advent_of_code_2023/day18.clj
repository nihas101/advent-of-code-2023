(ns advent-of-code-2023.day18
  (:require
   [clojure.string :as string]
   [advent-of-code-2023.utils.string :as u]))

(defonce ^:private col-dir->dir
  {\0 "R"
   \1 "D"
   \2 "L"
   \3 "U"})

(defn- parse-instructions [col]
  (let [col-meters (take 5 (rest col))
        col-dir (last col)]
    {:meters (Long/parseLong (string/join col-meters) 16)
     :direction (col-dir->dir col-dir)}))

(defn- parse-line [line]
  (let [[dir meters col] (mapv first
                               (re-seq #"((?<=\()\S+(?=\)))|[^()\s]+"
                                       line))]
    {:direction dir
     :meters (Long/parseLong meters)
     :instructions (parse-instructions col)}))

(defn parse-input [input]
  (when input
    (transduce
     (comp
      (remove empty?)
      (map parse-line))
     conj [] (u/split-sections input #"\n"))))

(defn- move [pos dir ^long meters]
  (let [move-step (condp = dir
                    "U" #(update % 1 - meters)
                    "L" #(update % 0 - meters)
                    "R" #(update % 0 + meters)
                    "D" #(update % 1 + meters))]
    (move-step pos)))

(defn- shoelace [[[^long x1 ^long y1]
                  [^long x2 ^long y2]]]
  (- (* x1 y2) (* x2 y1)))

(defn- shoelace-sum [edge-points ^long points]
  (-> (transduce
       (map shoelace)
       + (partition 2 1 edge-points))
      abs
      (+ points)
      (quot 2)
      inc))

(defn- next-edge [[edge-points ^long point-count position]
                  {:keys [direction ^long meters]}]
  (let [ep (move position direction meters)]
    [(conj edge-points ep)
     (+ point-count meters)
     ep]))

(defn- day18 [instructions]
  (let [[edge-points points] (reduce next-edge [[] 0 [0 0]] instructions)]
    (shoelace-sum edge-points points)))

(defonce day18-1 day18)

(defn day18-2 [instructions]
  (day18 (mapv :instructions instructions)))