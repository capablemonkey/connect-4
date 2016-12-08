(ns connect-4.core-test
  (:require [clojure.test :refer :all]
            [connect-4.core :refer :all]))

(deftest new-board-test
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
      "board contains the correct number of columns")
    (is
      (=
        nil
        (first (first (new-board 6 7))))
      "board cell is initialized to nil")))

(deftest column-height-test
  (testing "column-height"
    (is
      (=
        2
        (column-height
          [
            [:a nil :a]
            [nil nil :a]
            [nil nil nil]]
          2))
      "column-height returns the correct height for the given column")))

(deftest set-cell-test
  (testing "set-cell"
    (is
      (=
        [
          [nil nil nil]
          [nil nil nil]
          [nil :a nil]]
        (set-cell
          [
            [nil nil nil]
            [nil nil nil]
            [nil nil nil]]
          2
          1
          :a))
      "set-cell sets the cell at column,height to the given value")))

(deftest drop-disc-test
  (testing "drop-disc"
    (is
      (=
        [
          [:a :b :a]
          [nil :a nil]
          [nil :b nil]]
        (drop-disc
          [
            [:a :b :a]
            [nil :a nil]
            [nil nil nil]]
          1
          :b))
      "drop-disc drops a disc in the correct cell")))
