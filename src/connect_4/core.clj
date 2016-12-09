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

(defn get-cell [board row column]
  "Returns the value at the given cell in the given board"
  (get
    (get board row)
    column))

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
;
(defn find-n-chain
  (
    [board n row column]
    (or
      (find-n-chain board n row (inc column) :right [(get-cell board row column)])
      (find-n-chain board n (inc row) column :down [(get-cell board row column)])
      (find-n-chain board n (inc row) (inc column) :down-right [(get-cell board row column)])
      (find-n-chain board n (inc row) (dec column) :down-left [(get-cell board row column)])))
  (
    [board n row column direction chain]
    (let [current-color (get-cell board row column)]
      (cond
        (not= (first chain) current-color)
        false
        (= n (inc (count chain)))
        true
        :else
        (cond
          (= direction :right)
          (find-n-chain board n row (inc column) direction (cons current-color chain))
          (= direction :down)
          (find-n-chain board n (inc row) column direction (cons current-color chain))
          (= direction :down-right)
          (find-n-chain board n (inc row) (inc column) direction (cons current-color chain))
          (= direction :down-left)
          (find-n-chain board n (inc row) (dec column) direction (cons current-color chain)))))))

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
