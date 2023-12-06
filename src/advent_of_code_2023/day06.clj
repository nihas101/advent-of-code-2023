(ns advent-of-code-2023.day06
  (:require
   [clojure.math :as math]
   [clojure.string :as string]
   [advent-of-code-2023.utils.string :as u]))

(defn parse-input [input prep-fun]
  (let [[time distance] (mapv (fn [s] (re-seq #"\d+" (prep-fun s)))
                              (u/split-sections input u/line-endings))]
    (mapv (fn [t d] {:duration t
                     :record d})
          (mapv #(Long/parseLong %) time)
          (mapv #(Long/parseLong %) distance))))

(defn- error-margin-brute-force [{:keys [^long duration ^long record]}]
  (loop [button-hold-time 0
         ways-to-beat-the-record []]
    (let [boat-distance (* button-hold-time (- duration button-hold-time))]
      (cond
        ;; We have checked every amount of time possible
        (<= duration button-hold-time) ways-to-beat-the-record
        ;; We beat the record
        (< record (* button-hold-time (- duration button-hold-time)))
        (recur (inc button-hold-time)
               (conj ways-to-beat-the-record {:hold-time button-hold-time :distance boat-distance}))
        :else (recur (inc button-hold-time)
                     ways-to-beat-the-record)))))

(defn day06-1 [input]
  (transduce
   (comp
    (map error-margin-brute-force)
    (map count))
   * (parse-input input identity)))

(defn- error-margin [{:keys [^long duration ^long record]}]
  (let [d (math/sqrt (- (* duration duration) (* 4 (- 1) (- (inc record)))))]
    [(long (quot (+ (- duration) d) (* 2 (- 1))))
     (long (quot (- (- duration) d) (* 2 (- 1))))]))

(defn day06-2 [input]
  (let [[^long a ^long b] (error-margin
                           (first
                            (parse-input input #(string/replace % #"\s+" ""))))]
    (- b a)))