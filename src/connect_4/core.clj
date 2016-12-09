(ns connect-4.core
  (:gen-class))

(defn new-board [rows columns]
  "Returns a vector containing *rows* number of vectors, each of length *columns* with cells initialized to nil."
  (vec
    (map
      (fn [row] (vec (repeat columns nil)))
      (vec (repeat rows nil)))))

(defn column-height [board column]
  "Returns the number of discs in a column in the given board"
  (count
    (filter
      (fn [row] (not= nil (get row column)))
      board)))

(defn set-cell [board row column value]
  "Returns a new board with the cell at the given row and column set to value"
  (assoc board row
    (assoc (get board row) column value)))

(defn drop-disc [board column color]
  "Returns a new board with a disc of the given color added to the top of the given column"
  (set-cell
    board
    (column-height board column)
    column
    color))

(defn column-full? [board column]
  (=
    (count (first board))
    (column-height board column)))

(defn print-board [board]
  (doseq [row (reverse board)]
    (println row)))

(defn won? [board]
  "Returns true if the board is in a win state")

(defn tie? [board]
  "Returns true if there are no more moves left in the board")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (print-board
    (drop-disc
      (new-board 6 7)
      3
      :a)))
