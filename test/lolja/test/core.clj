(ns lolja.test.core
  (:use [lolja.core])
  (:use [clojure.test]))

(deftest test-read-command
  (is (= [:fd 100] (read-command "fd 100")))
  (is (= [:rt 100] (read-command "rt 100"))))

(deftest test-new-turtle
  (let [t (new-turtle)]
    (is (= '(0 0) (:location t)))
    (is (= 0 (:heading t)))
    (is (= :pen-down (pen-state t)))))

(deftest test-pen-down
  (is (= :pen-down (pen-state (pen-down (new-turtle)))))
  (is (= :pen-down (pen-state (pen-down (pen-up (new-turtle)))))))

(deftest test-pen-erase
  (is (= :pen-erase (pen-state (pen-erase (new-turtle)))))
  (is (= :pen-erase (pen-state (pen-erase (pen-down (new-turtle))))))
  (is (= :pen-erase (pen-state (pen-erase (pen-up (new-turtle)))))))
  
(deftest test-pen-up
  (is (= :pen-up (pen-state (pen-up (new-turtle)))))
  (is (= :pen-up (pen-state (pen-up (pen-up (new-turtle)))))))

(deftest test-pen-state
  (is (= :pen-up (pen-state (pen-up (new-turtle)))))
  (is (= :pen-down (pen-state (pen-down (new-turtle)))))
  (is (= :pen-erase (pen-state (pen-erase (new-turtle))))))