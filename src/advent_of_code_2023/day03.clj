(ns advent-of-code-2023.day03
  (:require
   [advent-of-code-2023.utils.string :as u]))

(defn- match-line-elements [^java.util.regex.Matcher matcher graph-elements element-parser y]
  (if-let [match (re-find matcher)]
    (recur matcher
           (reduce (fn [r x]
                     ;; We need to identify duplicate gear parts later on,
                     ;; so we uniquely identify them via their starting position
                     (assoc r [x y] [[(.start matcher) y] (element-parser match)]))
                   graph-elements (range (.start matcher) (.end matcher)))
           element-parser
           y)
    graph-elements))

(defn- parse-line-elements-fn [regex element-parser]
  (fn [res [line y]]
    (match-line-elements (re-matcher regex line) res element-parser y)))

(defn parse-input [input]
  (let [indexed-lines (mapv vector
                            (u/split-sections input u/line-endings)
                            (range))]
    {:parts (reduce (parse-line-elements-fn #"\d+" #(Long/parseLong %)) {} indexed-lines)
     :symbols (reduce (parse-line-elements-fn #"[^\d\.]" identity) {} indexed-lines)}))

(defn- neighbours [[^long x ^long y]]
  (for [xx (range (dec x) (+ x 2))
        yy (range (dec y) (+ y 2))
        :when (or (not= xx x) (not= yy y))]
    [xx yy]))

(defn day03-1 [parsed-input]
  (transduce
   (comp
    (mapcat neighbours)
    (map (:parts parsed-input))
    (remove nil?)
    (distinct)
    (map second))
   + (keys (:symbols parsed-input))))

(defn- gear-ratio-fn [parts]
  (fn gear-ratio [ns]
    (let [[^long a ^long b :as adjacent-numbers] (transduce
                                                  (comp
                                                   (map parts)
                                                   (remove nil?)
                                                   (distinct)
                                                   (map second))
                                                  conj [] ns)]
      (if (= 2 (count adjacent-numbers))
        (* a b)
        0))))

(defn day03-2 [parsed-input]
  (transduce
   (comp
    (filter (comp #{"*"} second second))
    (map (comp first second))
    (map neighbours)
    (map (gear-ratio-fn (:parts parsed-input))))
   + (:symbols parsed-input)))