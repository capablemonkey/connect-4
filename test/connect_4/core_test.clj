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

(deftest column-full-test
  (testing "column-full?"
    (is
      (column-full?
        [
          [nil :a nil]
          [nil :a nil]
          [nil :b nil]]
        1)
      "column-full? should return true for a column that is full")
    (is
      (not
        (column-full?
          [
            [nil :a nil]
            [nil :a nil]
            [nil nil nil]]
          1))
      "column-full? should return true for a column that is full")))

(deftest get-cell-test
  (testing "get-column"
    (is
      (=
        :a
        (get-cell
          [
            [nil nil nil]
            [nil nil nil]
            [nil :a nil]]
          2
          1))
      "get-cell returns the correct value at the given row and column")
    (is
      (=
        nil
        (get-cell
          [
            [:a :a :a]
            [:a :a :a]
            [:a :a :a]]
          5
          5))
      "get-cell returns nil when the given row and column is out of bounds")))

(deftest find-n-chain-test
  (testing "find-n-chain"
    (is
      (=
        false
        (find-n-chain
          [
            [:a :a :b :a]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]]
          3
          0
          0))
      "find-n-chain returns false when a chain does not exist at 0,0")
    (is
      (=
        true
        (find-n-chain
          [
            [:a :a :a :b]
            [nil nil nil nil]
            [nil nil nil nil]
            [nil nil nil nil]]
          3
          0
          0))
      "find-n-chain returns true when a horizontal chain exists at 0,0")
    (is
      (=
        true
        (find-n-chain
          [
            [:a :a :a :b]
            [:a nil nil nil]
            [:a nil nil nil]
            [:a nil nil nil]]
          3
          0
          0))
      "find-n-chain returns true when a vertical chain exists at 0,0")
    (is
      (=
        true
        (find-n-chain
          [
            [:a :a :a :b]
            [:b :a nil nil]
            [:a nil :a nil]
            [:a nil nil :b]]
          3
          0
          0))
      "find-n-chain returns true when a down-right chain exists at 0,0")
    (is
      (=
        true
        (find-n-chain
          [
            [:a :a :a :b]
            [:b :b :b nil]
            [:a :b :a nil]
            [:a nil nil :b]]
          3
          0
          3))
      "find-n-chain returns true when a down-left chain exists at 0,3")
    (is
      (=
        false
        (find-n-chain
          [
            [:a :a :a :b]
            [:b :a :b :a]
            [:a :b :a :b]
            [:a :b :a :b]]
          3
          1
          0))
      "find-n-chain returns true when no chain exists in a full board at 1,0")))

(deftest won-test
  (testing "won?"
    (is
      (=
        true
        (won?
          [
            [:a nil nil]
            [nil :a nil]
            [nil nil :a]])))))
