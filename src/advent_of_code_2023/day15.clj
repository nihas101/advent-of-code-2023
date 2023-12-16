(ns advent-of-code-2023.day15
  (:require
   [advent-of-code-2023.utils.string :as u]))

(defn parse-input [input]
  (mapv (fn [ascii]
          (let [op (re-find #"=|-" ascii)
                [label focal-length] (u/split-sections ascii #"=|-")]
            (if (= op "=")
              [ascii label :assign (Long/parseLong focal-length)]
              [ascii label :remove])))
        (u/split-sections input #",")))

(defonce ^:private ascii-hash
  (memoize (fn [ascii]
             (reduce (fn [^long cv ^long a] (-> cv (+ a) (* 17) (rem 256)))
                     0 (mapv long ascii)))))

(defn day15-1 [lenses]
  (transduce
   (comp
    (map first)
    (map ascii-hash))
   + lenses))

(defn- add-lens [{:keys [positions] :as b} label focal-length]
  (let [ah (ascii-hash label)
        relevant-box (positions ah)
        pos (or (get relevant-box label) (count relevant-box))]
    (-> b
        (assoc-in ,,, [:focal-lengths label] focal-length)
        (assoc-in ,,, [:positions ah label] pos))))

(defn- remove-lens [{:keys [positions] :as b} label]
  (let [ah (ascii-hash label)
        relevant-box (positions ah)]
    (if-let [pos (get relevant-box label)]
      (transduce
       (comp
        (filter (fn [[_ ^long p]] (< ^long pos p)))
        (map first))
       (fn
         ([box] box)
         ([box lbl]
          (update-in box [:positions ah lbl] dec)))
       (update-in b [:positions ah] dissoc label) relevant-box)
      b)))

(defn- focus-power [{:keys [focal-lengths positions]}]
  (transduce
   (comp
    (mapcat (fn [[^long box-nr order]]
              (mapv (fn [[lbl ^long pos]] (* (inc box-nr)
                                             (inc pos)
                                             ^long (focal-lengths lbl)))
                    order))))
   + positions))

(defn day15-2 [lenses]
  (focus-power
   (reduce (fn [boxes [_ label op focal-length]]
             (if (= op :assign)
               (add-lens boxes label focal-length)
               (remove-lens boxes label)))
           {:focal-lengths {}
            :positions {}}
           lenses)))