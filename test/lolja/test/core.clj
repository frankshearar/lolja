(ns lolja.test.core
  (:use [lolja.core])
  (:use [clojure.test]))

;;; Parsing functions

(deftest test-command-turtle
  (let [t (new-turtle)]
    (is (= 270 (heading (command-turtle [:rt 90] t))))
    (is (= 90 (heading (command-turtle [:lt 90] t))))
    (is (= [10 0] (location (command-turtle [:fd 10] t))))
    (is (= [-10 0] (location (command-turtle [:bk 10] t))))))

(deftest test-read-command
  (is (= [:fd 100] (read-command "fd 100")))
  (is (= [:rt 100] (read-command "rt 100")))
  (is (= [:lt -10] (read-command "lt -10"))))

;;; Turtle functions

(deftest test-bk
  (is (= [-10 0] (location (bk (new-turtle) 10))))
  (is (= [10 0] (location (bk (new-turtle) (- 10)))))
  (let [t (new-turtle)]
    (is (= (heading t) (heading (fd t 10))))))

(deftest test-fd
  (is (= [10 0] (location (fd (new-turtle) 10))))
  (is (= [-10 0] (location (fd (new-turtle) (- 10)))))
  (let [t (new-turtle)]
    (is (= (heading t) (heading (fd t 10))))))

(deftest test-heading
  (is (= 270 (heading (rt (new-turtle) 90))))
  (is (= 90 (heading (rt (new-turtle) -90)))))

(deftest test-location
  (is (= [10 0] (location (fd (new-turtle) 10))))
  (is (= [0 10] (location (fd (lt (new-turtle) 90) 10)))))

(deftest test-lt
  (is (= 270 (heading (lt (new-turtle) 270))))
  (is (= 10 (heading (lt (lt (new-turtle) 350) 20))))
  (is (= 270 (heading (lt (new-turtle) -90))))
  (is (= 0 (heading (lt (new-turtle) 720)))))

(deftest test-new-turtle
  (let [t (new-turtle)]
    (is (= '(0 0) (location t)))
    (is (= 0 (heading t)))
    (is (= :pen-down (pen-state t)))))

(deftest test-pen-down
  (is (= :pen-down (pen-state (pen-down (new-turtle)))))
  (is (= :pen-down (pen-state (pen-down (pen-up (new-turtle))))))
  (is (= :pen-down (pen-state (pen-down (pen-erase (new-turtle)))))))

(deftest test-pen-erase
  (is (= :pen-erase (pen-state (pen-erase (new-turtle)))))
  (is (= :pen-erase (pen-state (pen-erase (pen-down (new-turtle))))))
  (is (= :pen-erase (pen-state (pen-erase (pen-up (new-turtle)))))))
  
(deftest test-pen-up
  (is (= :pen-up (pen-state (pen-up (new-turtle)))))
  (is (= :pen-up (pen-state (pen-up (pen-up (new-turtle))))))
  (is (= :pen-up (pen-state (pen-up (pen-erase (new-turtle)))))))

(deftest test-pen-state
  (is (= :pen-up (pen-state (pen-up (new-turtle)))))
  (is (= :pen-down (pen-state (pen-down (new-turtle)))))
  (is (= :pen-erase (pen-state (pen-erase (new-turtle))))))

(deftest test-rectilinear-movement
  (is (= [10 0] (location (fd (new-turtle) 10))))
  (is (= [0 10] (location (fd (lt (new-turtle) 90) 10))))
  (is (= [-10 0] (location (fd (rt (new-turtle) 180) 10))))
  (is (= [0 -10] (location (bk (lt (new-turtle) 90) 10)))))

(deftest test-rt
  (is (= 270 (heading (rt (new-turtle) 90))))
  (is (= 350 (heading (rt (rt (new-turtle) 350) 20))))
  (is (= 90 (heading (rt (new-turtle) -90))))
  (is (= 0 (heading (rt (new-turtle) 720)))))