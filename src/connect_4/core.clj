(ns connect-4.core
  (:gen-class))

(defn new-board
  "Returns a vector containing *rows* number of vectors, each of length *columns* with cells initialized to nil."
  [rows columns]
  (vec
    (map
      (fn [row] (vec (repeat columns nil)))
      (vec (repeat rows nil)))))

(defn column-height
  "Returns the number of discs in a column in the given board"
  [board column]
  (count
    (filter
      (fn [row] (not= nil (get row column)))
      board)))

(defn set-cell
  "Returns a new board with the cell at the given row and column set to value"
  [board row column value]
  (assoc board row
    (assoc (get board row) column value)))

(defn get-cell
  "Returns the value at the given cell in the given board"
  [board row column]
  (get
    (get board row)
    column))

(defn drop-disc
  "Returns a new board with a disc of the given color added to the top of the given column"
  [board column color]
  (set-cell
    board
    (column-height board column)
    column
    color))

(defn column-full?
  "Returns true if the given column in the given board is full"
  [board column]
  (=
    (count (first board))
    (column-height board column)))

(defn print-board
  "Prints the board line by line, with rows in descending order"
  [board]
  (doseq [row (reverse board)]
    (println row)))
;
(defn find-n-chain
  "Returns true if there is a chain of length n starting from the given cell at (row,column)"
  (
    [board n row column]
    (and
      (not= nil (get-cell board row column))
      (or
        (find-n-chain board n row (inc column) :right [(get-cell board row column)])
        (find-n-chain board n (inc row) column :down [(get-cell board row column)])
        (find-n-chain board n (inc row) (inc column) :down-right [(get-cell board row column)])
        (find-n-chain board n (inc row) (dec column) :down-left [(get-cell board row column)]))))
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

(defn map-board
  "Applies func to every cell in the given board"
  [func board]
  (map-indexed
    (fn [row-index row]
      (map-indexed
        (fn [column-index cell] (apply func [board row-index column-index]))
        row))
    board))

(defn won?
  "Returns true if the board contains a chain of discs of length chain-length"
  [board chain-length]
  (some
    (fn [row] (some true? row))
    (map-board
      (fn [board row column] (find-n-chain board chain-length row column))
      board)))

(defn filled?
  "Returns true if there are no more moves left in the board"
  [board]
  (every?
    (fn [row]
      (=
        (count (remove nil? row))
        (count row)))
    board))

(defn play-game
  [rows columns chain-length]
  (loop [board (new-board rows columns) player :a]
    (print-board board)
    (cond
      (won? board chain-length)
      (println "won!")
      (filled? board)
      (println "tie!")
      :else
      (do
        (println "Drop disc at column: ")
        (recur
          (drop-disc board (read-string (read-line))
            (if (= player :a) :b :a))
          (if (= player :a) :b :a))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (play-game 6 7 4))
