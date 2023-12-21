(ns advent-of-code-2023.day20
  (:require
   [advent-of-code-2023.utils.math :as m]
   [advent-of-code-2023.utils.string :as u]))

(defn- parse-module [modules]
  (let [[fun name & out] (re-seq #"[%&]|[^%&->\s]+" modules)]
    (cond
      (= "%" fun) {name {:out out :type :flip-flop :state false}}
      (= "&" fun) {name {:out out :type :conjunction :state {}}}
      (= "broadcaster" fun) {fun {:out (concat [name] out) :type :broadcast}})))

(defn parse-input [input]
  (when input
    (let [modules (reduce merge (mapv parse-module (u/split-sections input u/line-endings)))
          conjunctions (mapv first (filterv (comp #{:conjunction} :type second) modules))]
      (reduce (fn [mods [cnj inputs]]
                (reduce (fn [m i] (assoc-in m [cnj :state i] false)) mods inputs))
              modules
              (mapv (fn [[cnj mods]]
                      [cnj
                       (mapv first
                             (filterv (fn [[_ {:keys [out]}]] ((set out) cnj)) mods))])
                    (mapv vector conjunctions (repeat modules)))))))

(defn- process-flip-flip [{:keys [state] :as flip-flop} pulse]
  (when-not pulse
    [(assoc flip-flop :state (not state))
     (not state)]))

(defn- process-conjunction [conjunction input pulse]
  (let [{:keys [state] :as conjunction} (assoc-in conjunction [:state input] pulse)]
    [conjunction (not (every? true? (vals state)))]))

(defn- handle-signal [modules stack pulse-count goal]
  (loop [modules modules
         stack stack
         pulse-count pulse-count]
    (let [[source module-name pulse] (first stack)
          s (pop stack)
          {:keys [out type] :as module} (modules module-name)]
      (cond
        (and goal (not pulse) (= module-name goal)) [modules pulse-count true]
        (empty? stack) [modules pulse-count false]

        (= type :flip-flop) (if-let [[flip-flop new-pulse] (process-flip-flip module pulse)]
                              (recur (assoc modules module-name flip-flop)
                                     (reduce conj s (mapv vector (repeat module-name)
                                                          out  (repeat new-pulse)))
                                     (update pulse-count pulse inc))
                              (recur modules s (update pulse-count pulse inc)))

        (= type :conjunction) (let [[conjuction new-pulse] (process-conjunction module source pulse)]
                                (recur (assoc modules module-name conjuction)
                                       (reduce conj s (mapv vector (repeat module-name)
                                                            out (repeat new-pulse)))
                                       (update pulse-count pulse inc)))

        (= type :broadcast) (recur modules
                                   (reduce conj s (mapv vector (repeat module-name)
                                                        out (repeat pulse)))
                                   (update pulse-count pulse inc))

        :else (recur modules s (update pulse-count pulse inc))))))

(defn day20-1 [modules]
  (let [limit 1000
        stack (conj clojure.lang.PersistentQueue/EMPTY [:button "broadcaster" false])]
    (loop [button-presses 0
           modules modules
           {^long high true
            ^long low false
            :as pulse-count} {true 0 false 0}]
      (if (< button-presses limit)
        (let [[new-modules pulse-count] (handle-signal modules stack pulse-count nil)]
          (recur (inc button-presses) new-modules pulse-count))
        (* high low)))))

(defn- module-inputs [modules module-name]
  (transduce
   (comp
    (filter (comp (fn [o] (some (set o) module-name)) :out second))
    (map first))
   conj [] modules))

(defn- goal->button-presses [modules stack goal]
  (loop [button-presses 1
         modules modules]
    (let [[new-modules _ goal-reached] (handle-signal modules stack {true 0 false 0} goal)]
      (if goal-reached
        button-presses
        (recur (inc button-presses) new-modules)))))

(defn day20-2 [modules goal]
  (let [goal-conjunction (module-inputs modules [goal])
        goal-conjunction-inputs (module-inputs modules goal-conjunction)
        stack (conj clojure.lang.PersistentQueue/EMPTY [:button "broadcaster" false])]
    (transduce
     (map (fn [gci] (goal->button-presses modules stack gci)))
     m/lcm goal-conjunction-inputs)))