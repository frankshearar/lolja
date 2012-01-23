(ns lolja.test.core
  (:use [lolja.core])
  (:use [clojure.test]))

(deftest test-heading
  (is (= 90 (heading (rt (new-turtle) 90))))
  (is (= 270 (heading (rt (new-turtle) -90)))))

(deftest test-new-turtle
  (let [t (new-turtle)]
    (is (= '(0 0) (:location t)))
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

(deftest test-read-command
  (is (= [:fd 100] (read-command "fd 100")))
  (is (= [:rt 100] (read-command "rt 100")))
  (is (= [:lt -10] (read-command "lt -10"))))

(deftest test-rt
  (is (= 90 (heading (rt (new-turtle) 90))))
  (is (= 10 (heading (rt (rt (new-turtle) 350) 20))))
  (is (= 270 (heading (rt (new-turtle) -90))))
  (is (= 0 (heading (rt (new-turtle) 720)))))