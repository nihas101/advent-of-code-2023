(ns advent-of-code-2023.day07
  (:require
   [clojure.string :as string]
   [advent-of-code-2023.utils.string :as u]))

(defn- hand-type [freqs]
  (condp = (sort (vals freqs))
    [5] :five-of-a-kind
    [1 4] :four-of-a-kind
    [2 3] :full-house
    [1 1 3] :three-of-a-kind
    [1 2 2] :two-pair
    [1 1 1 2] :one-pair
    :high-card))

(defn parse-input [input]
  (transduce
   (comp
    (remove #(some string/blank? %))
    (map (fn [[hand bid]]
           (let [freqs (frequencies hand)]
             {:hand hand
              :bid (Long/parseLong bid)
              :frequencies freqs
              :type (hand-type freqs)}))))
   conj [] (u/split-pairs input)))

(defonce ^:private hand-type-ranking
  {:five-of-a-kind 6
   :four-of-a-kind 5
   :full-house 4
   :three-of-a-kind 3
   :two-pair 2
   :one-pair 1
   :high-card 0})

(defonce ^:private card-ranking
  {\A 12
   \K 11
   \Q 10
   \J 9
   \T 8
   \9 7
   \8 6
   \7 5
   \6 4
   \5 3
   \4 2
   \3 1
   \2 0})

(defn- hand-comp [[^long t1 & h1 :as c1] [^long t2 & h2]]
  (cond
    (nil? c1) 0
    (< t1 t2) -1
    (> t1 t2) 1
    :else (recur h1 h2)))

(defn- day07 [hands sort-fn]
  (transduce
   (map-indexed (fn [^long rank {:keys [^long bid]}]
                  (* (inc rank) bid)))
   + (sort-by sort-fn hand-comp hands)))

(defn day07-1 [hands]
  (day07 hands
         (fn [{:keys [type hand]}]
           (conj (map card-ranking hand)
                 (hand-type-ranking type)))))

(defonce ^:private joker-card-ranking
  {\A 12
   \K 11
   \Q 10
   \T 9
   \9 8
   \8 7
   \7 6
   \6 5
   \5 4
   \4 3
   \3 2
   \2 1
   \J 0})

(defonce ^:private joker-hand
  {[:four-of-a-kind 4] :five-of-a-kind
   [:four-of-a-kind 1] :five-of-a-kind
   [:full-house 3] :five-of-a-kind
   [:full-house 2] :five-of-a-kind
   [:three-of-a-kind 3] :four-of-a-kind
   [:three-of-a-kind 1] :four-of-a-kind
   [:two-pair 2] :four-of-a-kind
   [:two-pair 1] :full-house
   [:one-pair 2] :three-of-a-kind
   [:one-pair 1] :three-of-a-kind
   [:high-card 1] :one-pair})

(defn day07-2 [hands]
  (day07 hands
         (fn [{:keys [type hand frequencies]}]
           (conj (map joker-card-ranking hand)
                 (hand-type-ranking (get joker-hand [type (frequencies \J)]
                                         type))))))