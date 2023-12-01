(ns advent-of-code-2023.utils.string
  (:require
   [clojure.string :as string]))

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