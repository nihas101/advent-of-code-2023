(ns advent-of-code-2023.day19
  (:require
   [advent-of-code-2023.utils.string :as u]))

(defn- parse-condition [condition]
  (let [[p fun comp] (re-seq #"\d+|[^<>=]+|[<>=]" condition)]
    [(cond
       fun (let [fun (resolve (read-string fun))
                 y (Long/parseLong comp)]
             (fn [part] (fun (part p) y)))
       (= p "A") (constantly "A")
       (= p "R") (constantly "R")
       :else (constantly p))
     fun p (if fun (Long/parseLong comp) comp)]))

(defn- parse-rule [rule]
  (let [[condition target] (u/split-sections rule #":")
        [cond-fun fun p comp] (parse-condition condition)]
    {:condition cond-fun
     :fun fun
     :p p
     :comp comp
     :target target}))

(defn- parse-rules [rules]
  (when rules
    (mapv parse-rule (u/split-sections rules #","))))

(defn- parse-parts [parts]
  (when parts
    (mapv (fn [part-ratings]
            (transduce
             (map (fn [[k v]] [k (Long/parseLong v)]))
             conj {} (partition-all 2 (re-seq #"[a-z]+|\d+" part-ratings))))
          (u/split-sections parts u/line-endings))))

(defn- parse-line [line]
  (let [[workflow rules]
        (u/split-sections line #"\{|\}")]
    {workflow (parse-rules rules)}))

(defn parse-input [input]
  (when input
    (let [[wf-input parts] (u/split-sections input)]
      {:workflows (transduce
                   (map parse-line)
                   merge (u/split-sections wf-input u/line-endings))
       :part-ratings (parse-parts parts)})))

(defn- run-workflow [workflow part-rating]
  (loop [[{:keys [condition target]} & wf] workflow]
    (let [result (condition part-rating)]
      (cond
        (string? result) result
        result target
        :else (recur wf)))))

(defn- workflow [workflows]
  (fn [part-rating workflow]
    (let [next-workflow (run-workflow (workflows workflow) part-rating)]
      (cond
        (#{"R" "A"} next-workflow) (= "A" next-workflow)
        (string? next-workflow) (recur part-rating next-workflow)
        (keyword? next-workflow) next-workflow))))

(defn day19-1 [{:keys [workflows part-ratings]}]
  (transduce
   (comp
    (filter #((workflow workflows) % "in"))
    (map (juxt #(get % "x") #(get % "m") #(get % "a") #(get % "s")))
    (map (partial reduce +)))
   + part-ratings))

(defn- shorten-range-left [[^long a ^long b] ^long c]
  [(max a c) b])

(defn- shorten-range-right [[^long a ^long b] ^long c]
  [a (min b c)])

(defn- split-range [part-rating-ranges fun p ^long comp]
  (condp = fun
    "<" [(update part-rating-ranges p shorten-range-left comp)
         (update part-rating-ranges p shorten-range-right (dec comp))]
    ">" [(update part-rating-ranges p shorten-range-right comp)
         (update part-rating-ranges p shorten-range-left (inc comp))]))

(defn combinations-count [ranges]
  (transduce
   (map (fn [[^long mi ^long ma]] (- (inc ma) mi)))
   * (vals ranges)))

(defn- valid-input-ranges [wf-name
                           [{:keys [fun p comp target]} & wfs]
                           workflows
                           part-rating-ranges]
  (cond
    (= wf-name "A") [part-rating-ranges]
    (= wf-name "R") []
    fun (let [[wf-false wf-true] (split-range part-rating-ranges fun p comp)]
          (into (valid-input-ranges wf-name wfs
                                    workflows wf-false)
                (valid-input-ranges target (workflows target)
                                    workflows wf-true)))
    p (recur p (workflows p) workflows part-rating-ranges)))

(defn day19-2 [{:keys [workflows]}]
  (transduce
   (map combinations-count)
   + (valid-input-ranges "in"
                         (workflows "in")
                         workflows
                         {"x" [1 4000]
                          "m" [1 4000]
                          "a" [1 4000]
                          "s" [1 4000]})))