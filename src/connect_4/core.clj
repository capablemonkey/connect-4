(ns connect-4.core
  (:gen-class))

(defn new-board [rows columns]
  "Returns a vector containing *rows* number of vectors, each of length *columns* with cells initialized to nil."
  (map
    (fn [row] (vec (repeat columns nil)))
    (vec (repeat rows nil))))

(defn drop-disc [board column color]
  "Returns a new board with a disc of the given color added to the top of the given column")

(defn won? [board]
  "Returns true if the board is in a win state")

(defn tie? [board]
  "Returns true if there are no more moves left in the board")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println (new-board 6 7)))
