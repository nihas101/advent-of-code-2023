(ns advent-of-code-2023.test-utils)

(defn slurp-input [file]
  (try (slurp file)
       ;; We are in a context where the real input is not available
       ;; e.g. the CI/CD pipeline
       (catch java.io.FileNotFoundException _ "")))