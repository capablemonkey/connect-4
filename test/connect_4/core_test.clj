(ns connect-4.core-test
  (:require [clojure.test :refer :all]
            [connect-4.core :refer :all]))

(deftest board-test
  (testing "new-board returns a valid board"
    (is
      (=
        6
        (count (new-board 6 7)))
      "board contains the correct number of rows")
    (is
      (=
        7
        (count (first (new-board 6 7))))
      "board contains the correct number of columns")))
