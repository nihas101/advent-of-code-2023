(ns advent-of-code-2023.day05
  (:require
   [advent-of-code-2023.utils.string :as u]))

(defn- parse-seeds [seeds]
  (mapv #(Long/parseLong %) (remove empty? (re-seq #"\d*" seeds))))

(defn- parse-ranges [ranges]
  (->> (u/split-sections ranges u/line-endings)
       (mapv (fn [r]
               (let [[drs srs rl]
                     (mapv #(Long/parseLong %)
                           (remove empty? (re-seq #"\d*" r)))]
                 {:destination-range-start drs
                  :source-range-start srs
                  :range-length rl})) ,,,)
       (sort-by :destination-range-start ,,,)))

(defn- parse-maps [conversion-maps]
  (transduce
   (map (fn [m]
          (let [[name ranges] (u/split-sections m #"\s+map:\s+")
                [source destination] (u/split-sections name #"-to-")]
            {source {:mappings (parse-ranges ranges)
                     :destination destination}})))
   merge conversion-maps))

(defn parse-input [input]
  (let [[seeds & maps] (u/split-sections input)]
    {:seeds (parse-seeds seeds)
     :maps (parse-maps maps)}))

(defn- find-mapping [[^long val ^long rl] mappings]
  (reduce (fn [res {:keys [^long source-range-start
                           ^long destination-range-start
                           ^long range-length] :as mapping}]
            (cond
              ;; There is a mapping that applies,
              ;; but for the beginning of the range only identiy applies
              (< val source-range-start)
              {:source-range-start val
               :destination-range-start val
               :range-length (min rl destination-range-start)}
              ;; The mapping applies at least partially
              (<= source-range-start val (dec (+ source-range-start
                                                 range-length)))
              (reduced mapping)
              ;; The mappping is irrelevant for this range
              :else res))
          nil mappings))

(defn- source-range->destination-ranges [source-range mappings]
  (loop [[^long srs ^long srl :as sr] source-range
         dr []]
    (if-let [{:keys [^long source-range-start
                     ^long destination-range-start
                     ^long range-length]}
             (find-mapping sr mappings)]
      (cond
         ;; The current range fits into the mapping completely
        (<= (dec (+ srs srl)) (+ source-range-start range-length))
        (conj dr [(+ destination-range-start (- srs source-range-start))
                  srl])
         ;; We have to split the current range at the end
        :else
        (let [drs (+ destination-range-start (- srs source-range-start))
              new-range-length (- (+ source-range-start range-length) srs)]
          (recur [(+ source-range-start range-length)
                  (- srl new-range-length)]
                 (conj dr [drs new-range-length]))))
      ;; Only the identity mapping fits, so we apply it
      (conj dr sr))))

(defn- source-ranges->destination-ranges [source-ranges mappings]
  (mapcat (fn [sr]
            (source-range->destination-ranges sr mappings))
          source-ranges))

(defn day05 [source-ranges maps]
  (->> {:destination "seed"}
       (iterate (comp maps :destination) ,,,)
       (transduce
        (take-while identity)
        (fn
          ([dest-ranges] dest-ranges)
          ([source-ranges {:keys [mappings]}]
           (source-ranges->destination-ranges source-ranges mappings)))
        (partition 2 source-ranges) ,,,)
       (transduce
        (map first)
        min Long/MAX_VALUE ,,,)))

(defn day05-1 [{:keys [seeds maps]}]
  (day05 (interleave seeds (repeat 1)) maps))

(defn day05-2 [{:keys [seeds maps]}]
  (day05 seeds maps))