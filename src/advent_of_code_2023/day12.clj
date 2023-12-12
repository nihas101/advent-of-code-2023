(ns advent-of-code-2023.day12
  (:require
   [clojure.string :as string]
   [advent-of-code-2023.utils.string :as u]))

(defn parse-input [input]
  (when (seq input)
    (mapv (fn [line]
            (let [[springs occ] (u/split-sections line #" ")]
              {:springs springs
               :occurrences (u/read-longs occ #",")}))
          (u/split-sections input u/line-endings))))

(def count-possibilities
  (memoize
   (fn
     ([{:keys [springs occurrences]}]
      (count-possibilities springs occurrences 0))
     ([[s & sp :as springs] [^long o & ocs :as occurrences] ^long curr-match]
      (cond
        ;; We correctly guessed the springs
        (and (empty? springs) (empty? occurrences)) 1
        (and (empty? springs) (= o curr-match)) (recur springs ocs 0)
        ;; We made the wrong decision in the past
        ;; and consumed the occurrences to quickly
        (and (empty? occurrences) (seq (drop-while #{\. \?} springs))) 0
        ;; Skip across . when not in a match
        (and (#{\.} s) (zero? curr-match))
        (count-possibilities sp occurrences 0)
        ;; We matched springs correctly so far
        (and (#{\.} s) (= curr-match o)) (count-possibilities sp ocs 0)
        ;; Found a spring, begin the match
        (and (#{\#} s) (zero? curr-match))
        (count-possibilities sp occurrences (inc curr-match))
        ;; Found a spring continue the match
        (and (#{\#} s) (< curr-match o)) (recur sp occurrences (inc curr-match))
        ;; We can either match at this point or not
        (and (#{\?} s) (zero? curr-match))
        (+ (count-possibilities (concat [\#] sp) occurrences curr-match)
           (count-possibilities (concat [\.] sp) occurrences curr-match))
        ;; We have to finish the match here, we have no other choice
        (and (#{\?} s) (= curr-match o)) (recur sp ocs 0)
        ;; We have to continue the match here, we have no other choice
        (and (#{\?} s) (< curr-match o)) (recur sp occurrences (inc curr-match))
        :else 0)))))

(defn day12-1 [spring-lines]
  (transduce
   (map count-possibilities)
   + spring-lines))

(defn day12-2 [spring-lines]
  (->> spring-lines
       (mapv (fn [sl]
               (-> sl
                   (update ,,, :occurrences #(apply concat (repeat 5 %)))
                   (update ,,, :springs #(string/join "?" (repeat 5 %))))))
       (pmap count-possibilities)
       (reduce +)))